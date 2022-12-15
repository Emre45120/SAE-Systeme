import java.io.*;
import java.net.*;
import java.util.*;

public class Clientsend implements Runnable {
    Execclient execclient;
    Scanner sc ;
    PrintWriter out;


    public Clientsend(Execclient execclient) {
        this.execclient = execclient;
        this.sc = new Scanner(System.in);
        this.out = execclient.out;
    }

    public void run() {
        while(true){
            String msg = sc.nextLine();
            out.println(msg);
            out.flush();
        }
    }
    
}