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

public class GameInternet extends Game{
    private static boolean win;
    private static String enterParams;
    private static String serverIP;
    private static int serverPort = 4444;

    public static void startGame(){
        win = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создать игровой сервер? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")) gameServer(scanner);
        else gameClient(scanner);

        System.out.println("Игра завершена.");
        System.out.println("Начать новую игру? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")) Main.newGame();
        else System.exit(0);
    }

    private static void gameServer(Scanner scanner){
        try {
            ServerSocket ss = new ServerSocket(serverPort); // создаем сокет сервера и привязываем его к вышеуказанному порту

            System.out.println("Сервер для игры Крестики-Нолики");

            GameField gameField = newGameField(scanner);

            System.out.println("Ожидание игроков...");
            Human playerX = new Human('X');
            System.out.println("Вы игрок " + playerX.getSymbol() + ". Ожидание второго игрокa...");

            Socket socketPlayer = ss.accept();
            System.out.println("Player O подключился");
            System.out.println();
            InternetPlayer playerO = new InternetPlayer('O');
            // Берем входной и выходной потоки сокета игрока O
            InputStream sin = socketPlayer.getInputStream();
            OutputStream sout = socketPlayer.getOutputStream();
            // Конвертируем потоки в другой тип
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            int clientStepX, clientStepY;

            out.writeUTF("Вы игрок " + playerO.getSymbol() + ".");
            out.flush();

            System.out.println(gameField.viewPlane());
            for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){

                System.out.println("Ваш ход:");
                playerX.step(gameField);
                win = gameField.checkWin(playerX);
                out.writeUTF(gameField.viewPlane().toString());
                out.writeBoolean(win);
                if(win) break;

                if(gameField.getHistorySteps() == gameField.getMaxSteps()) break;

                System.out.println("Ожидание хода игрока O...");
                clientStepX = in.readInt();
                clientStepY = in.readInt();
                playerO.setXY(clientStepX, clientStepY);
                playerO.step(gameField);
                win = gameField.checkWin(playerO);
                out.writeBoolean(win);
                System.out.println("Ход игрока O: ");
                out.writeUTF(gameField.viewPlane().toString());
                System.out.println(gameField.viewPlane());

                if(win) break;
            }
            endGame(gameField, scanner, win);
        } catch(Exception x) { //x.printStackTrace();
        }
    }


    private static void gameClient(Scanner scanner){
        System.out.println("Введите ip сервера для подключения или введите b для возврата в меню");
        enterParams = scanner.next();
        if(enterParams.contentEquals("b"))  Main.newGame();
        serverIP = enterParams;

        try {
            InetAddress ipAddress = InetAddress.getByName(serverIP); // создаем объект который отображает вышеописанный IP-адрес.
            //System.out.println("Подключение к компьютеру с IP: " + serverIP + " и портом: " + serverPort);
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Соединение установлено");
            System.out.println();

            // Берем входной и выходной потоки сокета
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            // Конвертируем потоки в другой тип
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            int x,y;
            win = false;
            String field = " ";
            String historySteps;

            System.out.println(in.readUTF());
            System.out.println();

            while (!win) {
                System.out.println("Ожидание хода игрока X...");
                System.out.println("Ход игрока X: \n" + (field = in.readUTF()));

                win = in.readBoolean();
                if(win){
                    System.out.println("Игрок X победил!");
                    break;
                }

                System.out.println("Ваш ход: ");
                System.out.print("y- ");
                y = scanner.nextInt();
                System.out.print("x- ");
                x = scanner.nextInt();
                out.writeInt(x);
                out.writeInt(y);
                win = in.readBoolean();
                field = in.readUTF();

                if(win){
                    System.out.println("Вы победили!");
                    break;
                }
            }

            historySteps = in.readUTF();
            System.out.println("Хотите увидеть историю ваших ходов? (y/n)");
            enterParams = scanner.next();
            if(enterParams.contentEquals("y")){
                System.out.println(field);
                System.out.println("История ходов.");
                System.out.println(historySteps);
            }

        } catch (Exception x) {
            //x.printStackTrace();
        }
    }
}
