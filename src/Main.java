public class Main {
    static boolean work;

    public static void main(String[] args) {
        int steps;



        //Scanner scanner = new Scanner(System.in);
        Player playerO = new Player('O');
        Player playerX = new Player('X');
        GameField gameField = new GameField();
        gameField.eraseField();

        System.out.println("Игра крестики-нолики");

        for(steps = 0; steps < gameField.getMaxSteps(); ){
            work = true;
            gameField.viewPlane();
            while (work){
                playerX.step();
            }
            steps++;
            if(steps == gameField.getMaxSteps() )break;
            gameField.viewPlane();
            work = true;
            while (work){
                playerO.step();
            }
            steps++;
        }

    }


}
