import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickGUI {
    private JTextField portInput;
    private JButton start;
    private JTextArea display;
    private JPanel jPanel;
    private JLabel liczbaKlientow;
    private JLabel podajPort;
    private JFrame jFrame;
    public QuickGUI(int maxClients) {
        QuickGUI handle = this;
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int port = Integer.parseInt(portInput.getText());
                    displayMessage("nasluchuje na porcie: "+String.valueOf(port));
                    start.setEnabled(false);
                    new ListenThread(maxClients,port,handle).start();
                } catch (NumberFormatException ex) {
                    displayMessage("Podales bledny nr portu");
                }
            }
        });

        jFrame = new JFrame("app");
        jFrame.setContentPane(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setSize(600,400);
        jFrame.setVisible(true);

    }

    synchronized public void displayMessage(String message){
        display.setText(display.getText()+"\n"+message);
    }
    synchronized public void displayNumberOfClients(int numberOfClients){
        liczbaKlientow.setText(String.valueOf(numberOfClients));
    }
}
