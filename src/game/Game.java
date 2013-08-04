package game;

import common.GameField;
import common.Main;
import players.Computer;
import players.Human;
import players.Player;

import java.util.Scanner;

public class Game {
    private static boolean win;
    private static String enterParams;
    private static boolean enableStepBack;

    public static void startGame(String params){
        win = false;
        Scanner scanner = new Scanner(System.in);
        Human playerX = new Human('X');
        Player playerO = null;

        if(params.contentEquals("pvp")){
            playerO = new Human('O');
        }
        else if(params.contentEquals("pvc")) {
            playerO = new Computer('O');
        }
        else {
            System.out.println("Ошибка!");
            Main.newGame();
        }

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

        if(playerO.getPlayerType() == 'C'){
            System.out.println("Включить отмену ходов? (y/n)");
            enterParams = scanner.next();
            enableStepBack = enterParams.contentEquals("y");

        }
        for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){
            System.out.println(gameField.viewPlane());

            if((playerO.getPlayerType() == 'C') && (gameField.getHistorySteps() > 1) && enableStepBack ){
                System.out.println("Хотите отменить ваш посл. ход? (y/n)");
                enterParams = scanner.next();
                if(enterParams.contentEquals("y")) gameField.stepBack();
                System.out.println(gameField.viewPlane());
            }

            System.out.println("Ход игрока " + playerX.getSymbol() + ": ");
            playerX.step(gameField);
            win = gameField.checkWin(playerX);

            if(gameField.getHistorySteps() == gameField.getMaxSteps()) break;
            if(win) break;

            System.out.println(gameField.viewPlane());
            System.out.println("Ход игрока " + playerO.getSymbol() + ": ");
            playerO.step(gameField);
            win = gameField.checkWin(playerO);

            if(win) break;
        }

        if(!win && (gameField.getHistorySteps() == gameField.getMaxSteps())) System.out.println("Ничья");

        System.out.println("Хотите увидеть историю ваших ходов? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")){
                System.out.println(gameField.viewPlane());
                System.out.println("История ходов.");
                gameField.getHistory();
        }

        System.out.println("Начать новую игру? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")) Main.newGame();
        else System.exit(0);
    }
}