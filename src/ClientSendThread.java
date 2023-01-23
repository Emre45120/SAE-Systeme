import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


public class ClientSendThread extends Thread {
    private Execclient execclient;
    private JTextField inputField;
    private JButton sendButton;

    public ClientSendThread(Execclient execclient, JTextField inputField, JButton sendButton) {
        this.execclient = execclient;
        this.inputField = inputField;
        this.sendButton = sendButton;
    }

    public void run() {
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (!message.isEmpty()) {
                    execclient.out.println(message);
                    execclient.out.flush();
                    inputField.setText("");
                }
            }
        });
    }
}

