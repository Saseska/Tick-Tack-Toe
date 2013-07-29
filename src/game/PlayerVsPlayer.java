package game;

import common.GameField;
import players.Human;

import java.util.Scanner;

public class PlayerVsPlayer extends Game{


    public static void game(){
        int steps;
        Scanner scanner = new Scanner(System.in);

        Human playerO = new Human('O');
        Human playerX = new Human('X');

        System.out.println("Введите размер игрового поля: ");
        GameField gameField = new GameField(scanner.nextInt());
        gameField.eraseField();

        setWin(false);

        for(steps = 0; steps < gameField.getMaxSteps(); ){
            setWork(true);
            gameField.viewPlane();
            System.out.println("Ход игрока " + playerX.getSymbol() + ": ");
            while (getWork()){
                playerX.step(gameField);
            }
            steps++;

            if(steps == gameField.getMaxSteps()) break;
            if(getWin()) break;

            gameField.viewPlane();
            setWork(true);
            System.out.println("Ход игрока " + playerO.getSymbol() + ": ");
            while (getWork()){
                playerO.step(gameField);
            }
            steps++;

            if(getWin()) break;
        }

        if(!getWin() && (steps == gameField.getMaxSteps())) System.out.println("Ничья");

        gameField.viewPlane();
    }
}
