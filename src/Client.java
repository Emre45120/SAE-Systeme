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
        this.pseudo = "Anonyme";
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out =  new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            out.println("Bienvenue sur le serveur");
            out.println("/help pour afficher la liste des commandes");
            out.flush();

            boolean continuefr = true; 
            while (continuefr) {
                String msg = in.readLine();
                if (msg.equals("/lsroom")) {
                    out.println("Salon 1 : " + serveur.salon1.size() + " personnes");
                    out.println("Salon 2 : " + serveur.salon2.size() + " personnes");
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.equals("/joinroom1")) {
                    serveur.redirigerToSalon(serveur.salon1, this);
                    out.println("Vous avez rejoint le salon 1");
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.equals("/joinroom2")) {
                    serveur.redirigerToSalon(serveur.salon2, this);
                    out.println("Vous avez rejoint le salon 2");
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.equals("/joinglobal")) {
                    serveur.redirigerToSalon(serveur.listeClients, this);
                    out.println("Vous avez rejoint le salon global");
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.startsWith("/changename")) {
                    String[] parts = msg.split(" ");
                    this.pseudo = parts[1];
                    out.println("Votre pseudo est maintenant : " + this.pseudo);
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                    
                }
                else if (msg.equals("/whoshere")){
                    out.println("Liste des personnes connectées : ");
                    if (serveur.listeClients.contains(this)) {
                        out.println("Salon global : ");
                        for (Client c : serveur.listeClients) {
                            out.println(c.pseudo);
                        }
                    }
                    else if (serveur.salon1.contains(this)) {
                        out.println("Salon 1 : ");
                        for (Client c : serveur.salon1) {
                            out.println(c.pseudo);
                        }
                    }
                    else if (serveur.salon2.contains(this)) {
                        out.println("Salon 2 : ");
                        for (Client c : serveur.salon2) {
                            out.println(c.pseudo);
                        }
                    }
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }

                
                else if (msg.startsWith("/mp")){
                    String[] parts = msg.split(" ");
                    String pseudo = parts[1];
                    String message = parts[2];
                    serveur.renvoyerMessageMP(message, this, pseudo);
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.equals("/help")){
                    out.println("Liste des commandes : ");
                    out.println("/lsroom : liste les salons et le nombre de personnes");
                    out.println("/joinroom1 : rejoindre le salon 1");
                    out.println("/joinroom2 : rejoindre le salon 2");
                    out.println("/joinglobal : rejoindre le salon global");
                    out.println("/changename : changer de pseudo");
                    out.println("/mp : envoyer un message privé");
                    out.println("/whoshere : liste les personnes connectées");
                    out.println("/help : afficher la liste des commandes");
                    out.println("/quit : quitter le serveur");
                    out.flush();
                    System.out.println(this.pseudo+" : " + msg);
                }
                else if (msg.equals("/quit")) {
                    continuefr = false;
                    System.out.println(this.pseudo+" : " + msg);
                }
                else {
                    System.out.println(this.pseudo+" : " + msg);
                    serveur.renvoyerMessage(msg, this);
                }
                
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