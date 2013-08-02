package players;

import common.GameField;

public class Player {
    private char symbol;
    private char playerType = 'P';
    private int x,y;
    private static boolean work;

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

    public char getPlayerType(){
        return playerType;
    }

    public boolean checkNum(int num, GameField field){
        return (num <= field.getFieldLength() && num >= GameField.RULER_MIN_VALUE);
    }

    public static void setWork(Boolean bool){
        work = bool;
    }

    public void step(GameField gameField){
        work = true;
        while (work){
            if(playerType == 'H'){
                stepHuman(gameField);
            }
            else if(playerType == 'C'){
                stepComputer(gameField);
            }
            if(!checkNum(y,gameField)) return;
            if(!checkNum(x,gameField)) return;
            gameField.setPoint(this.getSymbol(), x - GameField.RULER_MIN_VALUE, y - GameField.RULER_MIN_VALUE);
        }
        gameField.addStep(x - GameField.RULER_MIN_VALUE, y - GameField.RULER_MIN_VALUE);   //Добавить в память ходов
        gameField.incHistory();
        gameField.checkWin(this);
    }

    public void stepHuman(GameField gameField){}
    public void stepComputer(GameField gameField){}
}
