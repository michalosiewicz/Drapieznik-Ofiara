import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Koniec {
    JFrame koniec;
    JLabel tekst;
    JButton zakoncz;
    public Koniec(String s){
        koniec= new JFrame("Wynik");
        koniec.setSize(400,110);
        tekst = new JLabel(s,JLabel.CENTER);
        tekst.setFont(new Font(tekst.getName(),Font.PLAIN,20));
        zakoncz = new JButton("Zakończ");
        zakoncz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                koniec.dispose();
            }
        });
        koniec.add(tekst,BorderLayout.PAGE_START);
        koniec.add(zakoncz,BorderLayout.PAGE_END);
        koniec.setVisible(true);
    }
}
