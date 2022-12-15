import java.net.*;
import java.io.*;

public class Execclient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    
    public Execclient() {
        try {
            this.socket = new Socket("localhost", 4444);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void lancerClient() {
        Thread t1 = new Thread(new Clientrecevoir(this));
        Thread t2 = new Thread(new Clientsend(this));
        t1.start();
        t2.start();
    }
    

    public static void main(String[] args) {
        Execclient execclient = new Execclient();
        execclient.lancerClient();
    }
}