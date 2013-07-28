package players;

import game.GameField;

import java.util.Scanner;

public class Player {
    private char symbol;
    private Scanner scanner = new Scanner(System.in);
    private int x,y;

    private boolean checkNum(int num, GameField field){
        return (num <= field.getFieldLength());
    }

    public Player(char c){
        symbol = c;
    }

    public char getSymbol(){
        return symbol;
    }

    public int getX(){
        return x-1; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public int getY(){
        return y-1; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public void step(GameField gameField){
        System.out.println("Ход игрока " + symbol + ": ");
        System.out.print("y- ");
        y = scanner.nextInt();
        if(!checkNum(y,gameField)) return;
        System.out.print("x- ");
        x =  scanner.nextInt();
        if(!checkNum(x,gameField)) return;
        gameField.setPoint(this);
        gameField.checkWin(this);
    }
}
