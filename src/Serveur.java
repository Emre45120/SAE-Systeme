import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
    ArrayList<Client> listeClients ;
    ArrayList<Client> salon1;
    ArrayList<Client> salon2;

    public Serveur() {
        this.listeClients = new ArrayList<Client>();
        this.salon1 = new ArrayList<Client>();
        this.salon2 = new ArrayList<Client>();
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
        if (listeClients.contains(client)) {
            renvoyerMessageGlobal(msg, client);
        }
        else if (salon1.contains(client)) {
            renvoyerMessageSalon1(msg, client);
        }
        else if (salon2.contains(client)) {
            renvoyerMessageSalon2(msg, client);
        }
    }

    public void renvoyerMessageGlobal(String msg, Client client){
        for (Client c : listeClients) {
            if (c != client) {
                c.renvoyerMessage(msg);
            }
        }
    }

    public void renvoyerMessageSalon1(String msg, Client client){
        for (Client c : salon1) {
            if (c != client) {
                c.renvoyerMessage(msg);
            }
        }
    }

    public void renvoyerMessageSalon2(String msg, Client client){
        for (Client c : salon2) {
            if (c != client) {
                c.renvoyerMessage(msg);
            }
        }
    }

    public void redirigerToSalon(ArrayList<Client> salon, Client client){
        listeClients.remove(client);
        salon.add(client);
    
    }

    public void renvoyerMessageMP(String msg, Client client, String pseudo){
        ArrayList<Client> listeClientstemp =    new ArrayList<Client>();
        listeClientstemp.addAll(salon1);
        listeClientstemp.addAll(salon2);
        for (Client c : listeClientstemp) {
            if (c.getPseudo().equals(pseudo)) {
                c.renvoyerMessage(msg);
            }
        }

    }


    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.lancerServeur();
    }

}   
    