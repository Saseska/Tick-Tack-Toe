package players;

import common.GameField;

public class Player {
    private char symbol;
    private char playerType;
    private int x,y;

    public Player(char c, char t){
        symbol = c;
        playerType = t;
    }

    public char getSymbol(){
        return symbol;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x-1; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public int getY(){
        return y-1; // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
    }

    public boolean checkNum(int num, GameField field){
        return (num <= field.getFieldLength() && num > 0);
    }

    public void step(GameField gameField){

        if(playerType == 'H'){
            stepHuman(gameField);
        }
        else if(playerType == 'C'){
            stepComputer(gameField);
        }
        if(!checkNum(y,gameField)) return;
        if(!checkNum(x,gameField)) return;
        gameField.setPoint(this);
        gameField.checkWin(this);
    }

    public void stepHuman(GameField gameField){}
    public void stepComputer(GameField gameField){}
}
