package common;

import game.GameLocal;
import game.GameInternet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Игра крестики-нолики");
        newGame();
    }

    public static void newGame(){
        Scanner scanner = new Scanner(System.in);
        String params;
        StringBuilder newGameString = new StringBuilder();
        newGameString.append( "Для начала игры введите тип игры: \n" + "Player vs Player - pvp \n" + "Player vs InternetPlayer - pvi \n" + "Player vs Computer - pvc");
        System.out.println(newGameString);
        params = scanner.nextLine();
        if(params.contentEquals("pvi")) GameInternet.startGame();
        else GameLocal.startGame(params);
        System.exit(0);
    }
}
