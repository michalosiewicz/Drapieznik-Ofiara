import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Blad {
    JFrame blad;
    JLabel tekst;
    JButton ok;
    public Blad(){
        blad= new JFrame("Błąd");
        blad.setSize(400,110);
        tekst = new JLabel("Niepoprawne dane",JLabel.CENTER);
        tekst.setFont(new Font(tekst.getName(),Font.PLAIN,20));
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blad.dispose();
            }
        });
        blad.add(tekst,BorderLayout.PAGE_START);
        blad.add(ok,BorderLayout.PAGE_END);
        blad.setVisible(true);
    }
}
