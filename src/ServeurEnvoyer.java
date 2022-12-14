import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServeurEnvoyer {
    private String msg;
    private final Scanner sc;
    private final PrintWriter out;

    public ServeurEnvoyer(Socket clientSocket) throws IOException{
        sc = new Scanner(System.in);
        out = new PrintWriter(clientSocket.getOutputStream());
    }

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
    
}
