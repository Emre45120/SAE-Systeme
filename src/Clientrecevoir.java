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
        try {
            String msg = in.readLine();
            while (msg != null) {
                System.out.println("Client : " + msg);
                msg = in.readLine();
            }
            System.out.println("Client déconnecté");
            out.close();
            execclient.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}