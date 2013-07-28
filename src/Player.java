import java.util.Scanner;

public class Player {
    private char symbol;
    private Scanner scanner = new Scanner(System.in);
    private int x,y;

    public Player(char c){
        symbol = c;
    }

    public char getSymbol(){
        return symbol;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void step(GameField field){
        System.out.println("Ход игрока " + symbol + ": ");
        System.out.print("y- ");
        // - 1 т.к. нумерация в массиве начинается не с 1, а с 0.
        y = scanner.nextInt() - 1;
        System.out.print("x- ");
        x =  scanner.nextInt() - 1 ;
        field.checkPoint(this);

    }

}
