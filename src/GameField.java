public class GameField {

    private final static int FIELD_SIZE = 3;

    private int maxSteps = FIELD_SIZE * FIELD_SIZE;
    private static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    public int getMaxSteps(){
        return maxSteps;
    }

    public void eraseField(){
        for(int k = 0; k < field.length; k++){
            for(int j = 0; j < field[k].length; j++){
                field[k][j] = ' ';
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
        if(field[player.getY()][player.getX()] == ' ') {
            field[player.getY()][player.getX()] = player.getSymbol();
            Main.work = false;
        }
    }

}
