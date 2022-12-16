import java.io.*;
import java.net.*;

public class Clientrecevoir implements Runnable {
    Execclient execclient;
    BufferedReader in;
    PrintWriter out;

    public Clientrecevoir(Execclient execclient) {
        this.execclient = execclient;
        this.in = execclient.in;
        this.out = execclient.out;
    }

    public void run() {
        while(true){
            try {
                String msg = in.readLine();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}