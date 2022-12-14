import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeurRecevoir extends Thread {
    private String msg;
    final Socket clientSocket;
    final BufferedReader in;
    final PrintWriter out;

    public ServeurRecevoir(Socket socket) throws IOException{
        this.clientSocket = socket;
        this.out = new PrintWriter(this.clientSocket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
    }

    
    
    @Override
    public void run() {
        try {
            msg = in.readLine();
            System.out.println(msg);
            while(msg != null){
                System.out.println("Client : "+msg);
                msg = in.readLine();
            }
            System.out.println("Client déconnecté");
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client : "+msg);
    }
}
    
