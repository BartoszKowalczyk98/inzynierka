import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIAndLogic {
    public JButton wznow;
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
        wznow = new JButton("Wznów");
        wznow.addActionListener(actionEvent -> resumeMethod());
        jPanel.add(wznow);

        stop = new JButton("Stop");
        stop.addActionListener(actionEvent -> stopMethod());
        jPanel.add(stop);

        messageDisplay = new JLabel("Podaj nr wątku do wystartowania");


        borderLayout.addLayoutComponent(jPanel, BorderLayout.PAGE_START);

        frame.add(jPanel);
        frame.add(messageDisplay);
        frame.setVisible(true);
    }

    private void resumeMethod() {
        int idwatku = 0;
        try {
            idwatku = Integer.parseInt(id.getText());
            if (idwatku >= 1 && idwatku <= alphabetIteratorList.size()) {
                if (alphabetIteratorList.get(idwatku - 1).getState().equals(Thread.State.WAITING)) {
                    synchronized (alphabetIteratorList.get(idwatku - 1).lock) {
                        alphabetIteratorList.get(idwatku - 1).lock.notify();
                    }
                }
                messageDisplay.setText("");
            } else {
                messageDisplay.setText("Podałeś zły indeks wątku");
            }
            id.setText("");

        } catch (NumberFormatException e) {
            messageDisplay.setText("Błąd parsowania liczby");
        }

    }

    private void stopMethod() {
        int idwatku = 0;
        try {
            idwatku = Integer.parseInt(id.getText());
            if (idwatku >= 1 && idwatku <= alphabetIteratorList.size()) {
                if (alphabetIteratorList.get(idwatku - 1).toRunOrNotToRun) {
                    alphabetIteratorList.get(idwatku - 1).toRunOrNotToRun = false;
                }
                messageDisplay.setText("");
            } else {
                messageDisplay.setText("Podałeś zły indeks wątku");
            }
            id.setText("");
        } catch (NumberFormatException e) {
            messageDisplay.setText("Błąd parsowania liczby");
        }

    }

}
