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
        setY(random.nextInt(gameField.getFieldLength()+1) + 1);
        setX(random.nextInt(gameField.getFieldLength()+1) + 1);
    }
}
