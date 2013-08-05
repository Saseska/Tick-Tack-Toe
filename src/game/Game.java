package game;

import common.GameField;
import common.Main;

import java.util.Scanner;

public class Game {

    public static GameField newGameField(Scanner scanner){
        GameField gameField;
        System.out.println("Введите размер игрового поля: ");
        int size = scanner.nextInt();
        if(size > 3){
            System.out.println("Введите длину линии для победы: ");
            int numToWin = scanner.nextInt();
            if(numToWin < 3) {
                gameField = new GameField(size, 3);
            } else gameField = new GameField(size, numToWin);
        } else {
            gameField = new GameField(3, 3);
        }
        gameField.eraseField();
        return gameField;
    }

    public static void endGame(GameField gameField, Scanner scanner, boolean win){
        if(!win && (gameField.getHistorySteps() == gameField.getMaxSteps())) System.out.println("Ничья");

        String enterParams;

        System.out.println("Хотите увидеть историю ваших ходов? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")){
            System.out.println(gameField.viewPlane());
            System.out.println("История ходов.");
            System.out.println(gameField.getHistory());
        }

        System.out.println("Начать новую игру? (y/n)");
        enterParams = scanner.next();
        if(enterParams.contentEquals("y")) Main.newGame();
        else System.exit(0);
    }
}
