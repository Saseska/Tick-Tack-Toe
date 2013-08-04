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

public class GameInternet {
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

            GameField gameField = null;

            System.out.println("Введите размер игрового поля: ");
            int size = scanner.nextInt();
            if(size > 3){
                System.out.println("Введите длину линии для победы: ");
                int numToWin = scanner.nextInt();
                if(numToWin < 3) {
                    gameField = new GameField(size, 3);
                }
            } else {
                gameField = new GameField(3, 3);
            }
            gameField.eraseField();

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

            out.writeUTF("Вы игрок " + playerO.getSymbol() + ".");
            out.flush();

            int clientX, clientY;

            for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){

                System.out.println(gameField.viewPlane());
                System.out.println("Ваш ход:");
                playerX.step(gameField);
                win = gameField.checkWin(playerX);
                out.writeUTF(gameField.viewPlane().toString());
                out.flush();
                if(win) break;

                if(gameField.getHistorySteps() == gameField.getMaxSteps()) break;
                System.out.println("Ожидание хода игрока O...");
                out.writeBoolean(true);
                clientX = in.readInt();
                clientY = in.readInt();
                playerO.setXY(clientX, clientY);
                playerO.step(gameField);
                win = gameField.checkWin(playerO);
                System.out.println("Ход игрока O: ");
                if(win) break;
            }
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
            System.out.println("Подключение к компьютеру с IP: " + serverIP + " и портом: " + serverPort);
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Соединение установлено");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            boolean canStep = false;
            int x,y;

            System.out.println(in.readUTF());
            System.out.println();

            while (!win) {
                System.out.println("Ожидание хода игрока X...");
                System.out.println("Ход игрока X: \n" + in.readUTF());

                if(win) break;
                canStep = in.readBoolean();
                if(canStep) {
                    System.out.println("Ваш ход: ");
                    System.out.print("y- ");
                    y = scanner.nextInt();
                    System.out.print("x- ");
                    x = scanner.nextInt();
                    out.writeInt(x);
                    out.writeInt(y);
                    out.flush();
                    canStep = false;
                }
            }
        } catch (Exception x) {
            //x.printStackTrace();
        }
    }
}
