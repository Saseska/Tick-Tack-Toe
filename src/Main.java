import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. It's a game tick-tack-toe.");
        System.out.println("Please type what symbol you want: 1 - X, 2 - O");
        Player playerO = new Player("O");
        Player playerX = new Player("X");
        switch (scanner.nextInt()){
            case 0:

        }

    }
}
