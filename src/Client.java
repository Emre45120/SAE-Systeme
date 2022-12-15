import java.io.*;
import java.net.*;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Serveur2 serveur;
    private String pseudo;

    public Client(Socket socket, Serveur2 serveur) {
        this.socket = socket;
        this.serveur = serveur;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyer(String msg) {
        out.println(msg);
        out.flush();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}