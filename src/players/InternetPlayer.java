package players;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InternetPlayer extends Player{
    private static final char INTERNET_PLAYER = 'I';
    private int x,y;
    private DataInputStream in;
    private DataOutputStream out;

    public InternetPlayer(char c){
        super(c, INTERNET_PLAYER);
    }

    public void setInOutStreams(DataInputStream in, DataOutputStream out){
        this.in = in;
        this.out = out;
    }

    public void setXY() throws IOException {
        y = in.readInt();
        x = in.readInt();
    }

    @Override
    public void stepInternetPlayer() throws IOException {
        out.writeBoolean(getWork());
        if(!getWork()) return;
        setXY();

        setX(x);
        setY(y);

    }

}
