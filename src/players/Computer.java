package players;

import common.GameField;

import java.util.Random;

public class Computer extends Player{
    private static final char COMPUTER = 'C';

    Random random = new Random();

    public Computer(char c){
        super(c, COMPUTER);
    }

    @Override
    public void stepComputer(GameField gameField){
        setY(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
        setX(random.nextInt(gameField.getFieldLength()) + GameField.RULER_MIN_VALUE);
    }

    //AI
   /* public void betterStepHorizontal(GameField gameField){

    }*/
}
