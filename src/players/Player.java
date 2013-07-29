package players;

import common.GameField;

public class Player {
    private char symbol;
    private int x,y;

    public boolean checkNum(int num, GameField field){
        return (num <= field.getFieldLength() && num > 0);
    }

    public Player(char c){
        symbol = c;
    }

    public char getSymbol(){
        return symbol;
    }

    public int getX(){
        return x; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public void setY(int y){
        this.y = y;
    }

    public void step(GameField gameField){
        System.out.println("Ход игрока " + symbol + ": ");

    }
}
