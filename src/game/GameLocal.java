package game;

import common.GameField;
import common.Main;
import players.Computer;
import players.Human;
import players.Player;

import java.util.Scanner;

public class GameLocal extends Game{
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

        GameField gameField = newGameField(scanner);

        if(playerO.getPlayerType() == 'C'){
            System.out.println("Включить отмену ходов? (y/n)");
            enterParams = scanner.next();
            if(enterParams.contentEquals("y") || enterParams.contentEquals("н")) {
                enableStepBack = true;
            }
        }

        for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){

            if((playerO.getPlayerType() == 'C') && (gameField.getHistorySteps() > 1) && enableStepBack ){
                System.out.println("Хотите отменить ваш посл. ход? (y/n)");
                enterParams = scanner.next();
                if(enterParams.contentEquals("y") || enterParams.contentEquals("н")) {
                    gameField.stepBack();
                }
                System.out.println(gameField.viewPlane());
            }

            playerX.step(gameField);
            win = gameField.checkWin(playerX);

            if(gameField.getHistorySteps() == gameField.getMaxSteps()) break;
            if(win) break;

            playerO.step(gameField);
            win = gameField.checkWin(playerO);

            if(win) break;
        }

        endGame(gameField, scanner, win);
    }
}