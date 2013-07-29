package common;

import game.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Игра крестики-нолики");
        newGame();
    }

    public static void newGame(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Для начала игры введите тип игры:");
        System.out.println("Player vs Player - pvp");
        System.out.println("Player vs Computer - pvc");
        Game.startGame(scanner.nextLine());
    }

}
