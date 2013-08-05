package game;

import common.GameField;
import common.Main;
import players.Human;
import players.InternetPlayer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.*;

public class GameInternet extends Game{
    private static boolean win;
    private static String enterParams;
    private static String serverIP;
    private static int serverPort = 4444;

    public static void startGame(){
        win = false;
        Scanner scanner = new Scanner(in);
        out.println("Создать игровой сервер? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y") || enterParams.contentEquals("н")) gameServer(scanner);
        else gameClient(scanner);

        out.println("Игра завершена.");
        out.println("Начать новую игру? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y") || enterParams.contentEquals("н")) Main.newGame();
        else exit(0);
    }

    private static void gameServer(Scanner scanner){
        try {
            ServerSocket ss = new ServerSocket(serverPort); // создаем сокет сервера и привязываем его к вышеуказанному порту

            out.println("Сервер для игры Крестики-Нолики");

            GameField gameField = newGameField(scanner);

            Human playerX = new Human('X');
            out.println("Вы игрок " + playerX.getSymbol() + ". Ожидание второго игрокa...");

            Socket socketPlayer = ss.accept();
            out.println("Player O подключился");
            out.println();
            InternetPlayer playerO = new InternetPlayer('O');
            // Берем входной и выходной потоки сокета игрока O
            InputStream sin = socketPlayer.getInputStream();
            OutputStream sout = socketPlayer.getOutputStream();
            // Конвертируем потоки в другой тип
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            playerO.setInOutStreams(in, out);

            out.writeInt(gameField.getMaxSteps());
            out.writeUTF("Вы игрок " + playerO.getSymbol() + ".");

            for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){

                System.out.println("Ваш ход:");
                playerX.step(gameField);
                win = gameField.checkWin(playerX);
                out.writeUTF(gameField.viewPlane().toString());
                out.writeBoolean(win);
                if(win) break;

                if(gameField.getHistorySteps() == gameField.getMaxSteps()) break;

                System.out.println("Ожидание хода игрока O...");
                playerO.step(gameField);
                out.writeBoolean(playerO.getWork());
                win = gameField.checkWin(playerO);
                out.writeBoolean(win);
                System.out.println("Ход игрока O: ");
                out.writeUTF(gameField.viewPlane().toString());
                System.out.println(gameField.viewPlane());

                if(win) break;
            }
            out.writeUTF(gameField.getHistory().toString());
            out.flush();
            endGame(gameField, scanner, win);
        } catch(Exception x) { //x.printStackTrace();
        }
    }


    private static void gameClient(Scanner scanner){
        out.println("Введите ip сервера для подключения или введите b для возврата в меню");
        enterParams = scanner.next();
        if(enterParams.contentEquals("b"))  Main.newGame();
        serverIP = enterParams;

        try {
            InetAddress ipAddress = InetAddress.getByName(serverIP); // создаем объект который отображает вышеописанный IP-адрес.
            out.println("Подключение к компьютеру с IP: " + serverIP + " и портом: " + serverPort);
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            out.println("Соединение установлено");
            out.println();

            // Берем входной и выходной потоки сокета
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            // Конвертируем потоки в другой тип
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            win = false;
            boolean work;
            int maxSteps;
            String field = " ";
            String historySteps;

            maxSteps = in.readInt();
            System.out.println(in.readUTF());
            System.out.println();

            for(int steps = 0; steps < maxSteps; ){

                System.out.println("Ожидание хода игрока X...");
                System.out.println("Ход игрока X: \n" + (field = in.readUTF()));

                steps++;
                win = in.readBoolean();

                if(!win && (steps == maxSteps)){
                    System.out.println("Ничья");
                    break;
                }
                if(win){
                    System.out.println("Игрок X победил!");
                    break;
                }

                System.out.println("Ваш ход: ");
                work = in.readBoolean();
                while (work){
                    System.out.print("y- ");
                    out.writeInt(scanner.nextInt());
                    System.out.print("x- ");
                    out.writeInt(scanner.nextInt());
                    work = in.readBoolean();
                }
                steps++;
                win = in.readBoolean();
                field = in.readUTF();

                if(!win && (steps == maxSteps)){
                    System.out.println("Ничья");
                    break;
                }

                if(win){
                    System.out.println("Вы победили!");
                    break;
                }
            }

            historySteps = in.readUTF();
            System.out.println("Хотите увидеть историю ваших ходов? (y/n)");
            enterParams = scanner.next();
            if(enterParams.contentEquals("y") || enterParams.contentEquals("н")){
                System.out.println(field);
                System.out.println("История ходов.");
                System.out.println(historySteps);
            }
        } catch (Exception x) {
            //x.printStackTrace();
        }
    }
}
