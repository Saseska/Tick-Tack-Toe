package game;

import players.Player;

import java.util.Scanner;

public class PlayerVsPlayer {
    static boolean work;
    static boolean win;
    public static void game(){
        int steps;
        Scanner scanner = new Scanner(System.in);

        Player playerO = new Player('O');
        Player playerX = new Player('X');

        System.out.println("Введите размер игрового поля: ");
        GameField gameField = new GameField(scanner.nextInt());
        gameField.eraseField();

        win = false;

        for(steps = 0; steps < gameField.getMaxSteps(); ){
            work = true;
            gameField.viewPlane();
            while (work){
                playerX.step(gameField);
            }
            steps++;

            if(steps == gameField.getMaxSteps()) break;
            if(win) break;

            gameField.viewPlane();
            work = true;
            while (work){
                playerO.step(gameField);
            }
            steps++;

            if(win) break;
        }

        if(!win && (steps == gameField.getMaxSteps())) System.out.println("Ничья");

        gameField.viewPlane();
    }
}
