import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Execclient execclient;

    public ClientGUI() {
        super("Chat Client");
        this.execclient = new Execclient();


         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        ClientReceiveThread receiveThread = new ClientReceiveThread(execclient, chatArea);
        receiveThread.start();
        inputField = new JTextField();
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        sendButton = new JButton("Send");
        ClientSendThread sendThread = new ClientSendThread(execclient, inputField, sendButton);
        sendThread.start();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                execclient.out.println(message);
                execclient.out.flush();
                inputField.setText("");
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                execclient.out.println("/quit");
                System.exit(0);
            }
        });

        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ClientGUI();
        

    }
}