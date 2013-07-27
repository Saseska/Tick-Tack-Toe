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

    public void step(){
        System.out.println("Ход игрока " + symbol + ": ");
        System.out.print("x- ");
        x =  scanner.nextInt();
        System.out.print("y- ");
        y = scanner.nextInt();
        GameField.checkPoint(this);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
