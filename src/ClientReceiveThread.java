import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class ClientReceiveThread extends Thread {
    private Execclient execclient;
    private JTextArea chatArea;
    private BufferedReader in;

    public ClientReceiveThread(Execclient execclient, JTextArea chatArea) {
        this.chatArea = chatArea;
        this.in = execclient.in;

    }

    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                chatArea.append(message + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}