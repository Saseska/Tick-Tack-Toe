package players;

import common.GameField;

import java.util.Random;

public class Computer extends Player{

    Random random = new Random();

    public Computer(char c){
        super(c);
    }

    @Override
    public void step(GameField gameField){
        setY(random.nextInt(gameField.getFieldLength()+1) + 1);
        if(!checkNum(getY(),gameField)) return;
        setX(random.nextInt(gameField.getFieldLength()+1) + 1);
        if(!checkNum(getX(),gameField)) return;
        gameField.setPoint(this);
        gameField.checkWin(this);
    }
}
