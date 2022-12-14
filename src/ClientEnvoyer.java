
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEnvoyer extends Thread {
    private final PrintWriter out;
    Socket socket;
    private final Scanner sc = new Scanner(System.in);
    private String msg;

    public ClientEnvoyer() throws IOException{
        this.socket = new Socket("127.0.0.1", 4444);
        this.out = new PrintWriter(socket.getOutputStream());
    }
    
    @Override
    public void run() {
        while(true){
            msg = sc.nextLine();
            out.println(msg);
            out.flush();
            }
        }
}
