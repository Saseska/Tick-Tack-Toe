public class GameField {

    private final static int FIELD_SIZE = 3;
    private final static int NUM_TO_WIN = FIELD_SIZE;
    private final static char DEFAULT_SYMBOL = ' ';

    private static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private int maxSteps = FIELD_SIZE * FIELD_SIZE;


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
        for(int i = 0; i< FIELD_SIZE; i++)
            System.out.print(i + " | ");
        System.out.println();

        //Вывод поля в консоль + боковая линейка
        for(int i = 0; i < field.length; i++){
            System.out.print(i  + "| ");
            for(int j = 0; j < field[i].length; j++){
                System.out.print(field[i][j] + " | ");
            }
            System.out.println();
        }
    }


    public static void checkPoint(Player player){
        if(field[player.getY()][player.getX()] == DEFAULT_SYMBOL) {
            field[player.getY()][player.getX()] = player.getSymbol();
            Main.work = false;
        }
    }

    // Методы проверки победы
    public void checkWin(Player player){
        if(checkWinHorizontal(player) | checkWinVertical(player) | checkWinDiagonal(player)){
            Main.win = true;
            System.out.println("Player " + player.getSymbol() + " win!");
        }
    }

    private boolean checkWinHorizontal(Player player){
        int numSymb;    //Кол-во символов
        for(int row = 0; row < field.length; row++){
            numSymb = 0;
            for(int col = 0; col < field[row].length; col++){
                if(field[row][col] == player.getSymbol()) numSymb++;
            }
            if (numSymb == NUM_TO_WIN) return true;
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
            if (numSymb == NUM_TO_WIN) return true;
        }
        return false;
    }

    private boolean checkWinDiagonal(Player player){
        int numSymb = 0;
        for(int row = 0; row < field.length; row++){
            for(int col = 0; col < field[row].length; col++){
                if((row==col) & (field[row][col] == player.getSymbol())) numSymb++;
            }
            if (numSymb == NUM_TO_WIN) return true;
        }

        numSymb = 0;
        for(int row = field.length - 1; row >= 0; row--){

            for(int col = 0; col < field[row].length; col++){
                if(field[row][col] == player.getSymbol()){
                    numSymb++;
                    break;
                }
            }
            if (numSymb == NUM_TO_WIN) return true;
        }

        return false;
        }

}
