import java.io.*;
import java.net.*;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Serveur serveur;
    private String pseudo;

    public Client(Socket socket, Serveur serveur) {
        this.socket = socket;
        this.serveur = serveur;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out =  new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            boolean continuefr = true; 
            while (continuefr) {
                String msg = in.readLine();
                System.out.println("Client : " + msg);
                serveur.renvoyerMessage(msg, this);
            }
            System.out.println("Client déconnecté");
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void renvoyerMessage(String msg){
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