public class Main {
    static boolean work;
    static boolean win;

    public static void main(String[] args) {
        int steps;

        //Scanner scanner = new Scanner(System.in);
        Player playerO = new Player('O');
        Player playerX = new Player('X');
        GameField gameField = new GameField();
        gameField.eraseField();

        System.out.println("Игра крестики-нолики");
        win = false;

        for(steps = 0; steps < gameField.getMaxSteps(); ){
            work = true;
            gameField.viewPlane();
            while (work){
                playerX.step(gameField);
            }
            steps++;
            gameField.checkWin(playerX);

            if(steps == gameField.getMaxSteps()) break;
            if(win) break;

            gameField.viewPlane();
            work = true;
            while (work){
                playerO.step(gameField);
            }
            steps++;
            gameField.checkWin(playerO);

            if(win) break;
        }

        if(!win && (steps == gameField.getMaxSteps())) System.out.println("Ничья");

        gameField.viewPlane();

    }


}
