package common;

import game.Game;
import players.Player;

public class GameField {

    private final static int DEFAULT_FIELD_SIZE = 3;
    private final static char DEFAULT_SYMBOL = ' ';
    public final static int RULER_MIN_VALUE = 0;     //Мин. значение боковых линеек

    private int fieldSize = DEFAULT_FIELD_SIZE;
    private int numToWin = fieldSize;
    private int maxSteps = fieldSize * fieldSize;
    private char[][] field;

    public GameField(int size){
        if (size > 1) {
            fieldSize = size;
            field = new char[fieldSize][fieldSize];
        } else {
            Main.newGame();
        }
    }

    public int getFieldLength(){
        return field.length;
    }

    public int getMaxSteps(){
        return maxSteps;
    }

    public void eraseField(){
        for(int k = 0; k < field.length; k++){
            for(int j = 0; j < field[k].length; j++){
                field[k][j] = DEFAULT_SYMBOL;
            }
        }
    }

    public void viewPlane(){
        //Создание верхней линейки
        System.out.print(" | ");
        for(int i = 0; i < fieldSize; i++)
            System.out.print((i + RULER_MIN_VALUE) + " | ");  //i+RULE_MIN_VALUE, чтобы линейка начиналась не с 0, а с Мин. значения линейки
        System.out.println();

        //Вывод поля в консоль + боковая линейка
        for(int i = 0; i < field.length; i++){
            System.out.print((i + RULER_MIN_VALUE)  + "| "); //i+RULE_MIN_VALUE, чтобы линейка начиналась не с 0, а с Мин. значения линейки
            for(int j = 0; j < field[i].length; j++){
                System.out.print(field[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public void setPoint(Player player){
        if(field[player.getY()][player.getX()] == DEFAULT_SYMBOL) {
            field[player.getY()][player.getX()] = player.getSymbol();
            Game.setWork(false);
        }
    }

    // Методы проверки победы
    public void checkWin(Player player){
        if(checkWinHorizontal(player) || checkWinVertical(player) || checkWinDiagonal(player)){
            Game.setWin(true);
            System.out.println("Игрок " + player.getSymbol() + " победил!");
        }
    }

    private boolean checkWinHorizontal(Player player){
        int numSymb;    //Кол-во символов
        for(int row = 0; row < field.length; row++){
            numSymb = 0;
            for(int col = 0; col < field[row].length; col++){
                if(field[row][col] == player.getSymbol()) numSymb++;
            }
            if (numSymb == numToWin) return true;
        }
        return false;
    }

    private boolean checkWinVertical(Player player){
        int numSymb;
        for(int col = 0; col < field.length; col++){
            numSymb = 0;
            for(int row = 0; row < field.length; row++){
                if(field[row][col] == player.getSymbol()) numSymb++;
            }
            if (numSymb == numToWin) return true;
        }
        return false;
    }

    private boolean checkWinDiagonal(Player player){
        int numSymb = 0;

        for(int i = 0; i < field.length; i++){
            if(field[i][i] == player.getSymbol()) numSymb++;
            if (numSymb == numToWin) return true;
        }

        numSymb = 0;
        int col=0; //Столбец
        for(int row = field.length - 1; row >= 0; row--){
            if(field[row][col] == player.getSymbol()) numSymb++;
            col++;
            if (numSymb == numToWin) return true;
        }

        return false;
        }
}
