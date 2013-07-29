package game;

import common.Main;

public abstract class Game {
    private static boolean work;
    private static boolean win;

    public Game(){

    }

    public static void game(){
        System.out.println("Game");
    }

    public static void startGame(String params){
        if(params.contentEquals("pvp")) PlayerVsPlayer.game();
        else if(params.contentEquals("pvc")) PlayerVsComputer.game();
        else {
            System.out.println("Ошибка!");
            Main.newGame();
        }
    }

    public static void setWork(Boolean bool){
        work = bool;
    }

    public static void setWin(Boolean bool){
        win = bool;
    }

    public static boolean getWork(){
        return work;
    }

    public static boolean getWin(){
        return win;
    }
}
