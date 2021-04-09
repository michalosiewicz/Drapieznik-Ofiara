import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    JFrame menu;
    JLabel napis;
    JButton tak,nie;
    MenuD md;
    public Menu(){
        menu=new JFrame("Drapieżnik-Ofiara");
        menu.setSize(400,150);
        menu.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        napis= new JLabel("Czy ofiara ma korygować trasę ucieczki?");
        napis.setFont(new Font(napis.getName(),Font.PLAIN,20));
        tak=new JButton("TAK");
        tak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                md = new MenuD(1);
            }
        });
        nie=new JButton("NIE");
        nie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                md = new MenuD(2);
            }
        });

        menu.add(napis);
        menu.add(tak);
        menu.add(nie);
        menu.setVisible(true);
    }
}
