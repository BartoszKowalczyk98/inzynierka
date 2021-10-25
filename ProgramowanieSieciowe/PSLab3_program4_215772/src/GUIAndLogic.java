import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Future;

public class GUIAndLogic {
    public JButton stop;
    public JTextArea id;
    public JLabel messageDisplay;

    private List<AlphabetIterator> alphabetIteratorList;

    public GUIAndLogic(List<AlphabetIterator> alphabetIteratorList) {
        this.alphabetIteratorList = alphabetIteratorList;
        JFrame frame = new JFrame("Thread shower!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        JPanel jPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 4);
        jPanel.setLayout(gridLayout);

        BorderLayout borderLayout = new BorderLayout();
        frame.setLayout(borderLayout);

        JLabel idLabel = new JLabel("Podaj id wątku:");
        jPanel.add(idLabel);
        id = new JTextArea();
        jPanel.add(id);

        stop = new JButton("Usuń");
        stop.addActionListener(actionEvent -> stopMethod());
        jPanel.add(stop);

        messageDisplay = new JLabel("Podaj nr wątku do Usunięcia");


        borderLayout.addLayoutComponent(jPanel, BorderLayout.PAGE_START);

        frame.add(jPanel);
        frame.add(messageDisplay);
        frame.setVisible(true);
    }


    private void stopMethod() {
        int idwatkuDoUsuniecia;
        try {
            idwatkuDoUsuniecia = Integer.parseInt(id.getText());
            if (idwatkuDoUsuniecia >= 1 && idwatkuDoUsuniecia <= alphabetIteratorList.size()) {
                alphabetIteratorList.get(idwatkuDoUsuniecia-1).interrupt();
                messageDisplay.setText("");
            } else {
                messageDisplay.setText("Podałeś zły indeks wątku");
            }


        } catch (NumberFormatException e) {
            messageDisplay.setText("Blad Parsowania wpisanego tekstu!");
        }

    }
}
