package game;

import common.GameField;
import common.Main;
import players.Computer;
import players.Human;
import players.Player;

import java.util.Scanner;

public class Game {
    private static boolean win = false;
    static String enterParams;

    public static void setWin(){
        win = true;
    }

    public static void startGame(String params){
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

        System.out.println("Введите размер игрового поля: ");
        GameField gameField = new GameField(scanner.nextInt());
        gameField.eraseField();

        for(gameField.getHistorySteps(); gameField.getHistorySteps() < gameField.getMaxSteps(); ){
            gameField.viewPlane();

            if(playerO.getPlayerType() == 'C' && gameField.getHistorySteps() > 1){
                System.out.println("Хотите отменить ваш посл. ход? (y/n)");
                enterParams = scanner.next();
                if(enterParams.contentEquals("y")) gameField.stepBack();
                gameField.viewPlane();
            }

            System.out.println("Ход игрока " + playerX.getSymbol() + ": ");
            playerX.step(gameField);

            if(gameField.getHistorySteps() == gameField.getMaxSteps()+1) break;
            if(win) break;

            gameField.viewPlane();
            System.out.println("Ход игрока " + playerO.getSymbol() + ": ");
            playerO.step(gameField);

            if(win) break;
        }

        if(!win && (gameField.getHistorySteps() == gameField.getMaxSteps()+1)) System.out.println("Ничья");

        System.out.println("Хотите увидеть историю ваших ходов? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")){
                gameField.viewPlane();
                System.out.println("История ходов.");
                gameField.getHistory();
        }

        System.out.println("Начать новую игру? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")) Main.newGame();
        else System.exit(0);
    }
}
