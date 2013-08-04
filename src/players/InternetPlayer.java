package players;

public class InternetPlayer extends Player{
    private static final char INTERNET_PLAYER = 'I';
    private int x,y;

    public InternetPlayer(char c){
        super(c, INTERNET_PLAYER);
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void stepInternetPlayer(){
        setX(x);
        setY(y);
    }
}
