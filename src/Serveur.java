import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur{

    public static void main(String[] args){
        ServerSocket socketServer;
        Socket socketDuServeur;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try {
            socketServer = new ServerSocket(4444);
            socketDuServeur = socketServer.accept();
            out = new PrintWriter(socketDuServeur.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketDuServeur.getInputStream()));

            Thread envoyer = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true){
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });

            envoyer.start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}