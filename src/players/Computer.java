package players;

import common.GameField;

import java.util.Random;

public class Computer extends Player{
    private static final int RANDOM_INCREASE = 1; //Для увеличения мин. значения random. Значение 0 для [0...X], 1 для [1...X]
    private static final char COMPUTER = 'C';

    Random random = new Random();

    public Computer(char c){
        super(c, COMPUTER);
    }

    @Override
    public void stepComputer(GameField gameField){
        setY(random.nextInt(gameField.getFieldLength()) + RANDOM_INCREASE);
        setX(random.nextInt(gameField.getFieldLength()) + RANDOM_INCREASE);
    }
}
