import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
    ArrayList<Client> listeClients ;

    public Serveur() {
        this.listeClients = new ArrayList<Client>();
    }

    public void lancerServeur() {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Serveur lancé");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté");
                Client client = new Client(socket, this);
                listeClients.add(client);
                Thread t = new Thread(client);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void renvoyerMessage(String msg, Client client){
        for (Client c : listeClients) {
            if (c != client) {
                c.renvoyerMessage(msg);
            }
        }
    }

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.lancerServeur();
    }
    
}