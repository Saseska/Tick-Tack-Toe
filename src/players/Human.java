package players;


import common.GameField;

import java.util.Scanner;

public class Human extends Player{
    private Scanner scanner = new Scanner(System.in);

    public Human(char c){
        super(c);
    }

    @Override
    public void step(GameField gameField){
        System.out.print("y- ");
        setY(scanner.nextInt());
        if(!checkNum(getY(),gameField)) return;
        System.out.print("x- ");
        setX(scanner.nextInt());
        if(!checkNum(getX(),gameField)) return;
        gameField.setPoint(this);
        gameField.checkWin(this);
    }
}
