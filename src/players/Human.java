package players;

import common.GameField;

import java.util.Scanner;

public class Human extends Player{
    private static final char HUMAN = 'H';
    private Scanner scanner = new Scanner(System.in);

    public Human(char c){
        super(c, HUMAN);
    }

    public void stepHuman(GameField gameField){
        System.out.print("y- ");
        setY(scanner.nextInt());
        System.out.print("x- ");
        setX(scanner.nextInt());
    }
}
