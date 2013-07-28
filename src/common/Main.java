package common;

import game.PlayerVsComputer;
import game.PlayerVsPlayer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Игра крестики-нолики");
        newGame();
    }

    public static void newGame(){

        String params;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Для начала игры введите тип игры:");
        System.out.println("Player vs Player - pvp");
        System.out.println("Player vs Computer - pvc");
        params = scanner.nextLine();

        if(params.contentEquals("pvp")) PlayerVsPlayer.game();
        else if(params.contentEquals("pvc")) PlayerVsComputer.game();
        else {
            System.out.println("Ошибка!");
            newGame();
        }
    }

}
