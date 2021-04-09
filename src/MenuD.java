import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuD  {
    JFrame menu2;
    JLabel nazwa,napis,nazwa2,nazwa3,nazwa4,nazwa5,nazwa6;
    JTextField pole,pole2,pole3,pole4,pole5;
    JButton next;
    MenuO menu3;
    Obiekt drapieznik;
    public MenuD(int sc){
        menu2 = new JFrame("Drapieżnik");
        menu2.setSize(400,300);
        menu2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        next= new JButton("DALEJ");
        napis = new JLabel("Drapieżnik",JLabel.CENTER);
        napis.setFont(new Font(napis.getName(),Font.PLAIN,20));
        JPanel p= new JPanel();
        p.setLayout(null);
        nazwa = new JLabel("Punkt Pd");
        nazwa.setBounds(10,30,60,20);
        nazwa2 = new JLabel("x(m):");
        nazwa2.setBounds(80,30,40,20);
        nazwa3 = new JLabel("y(m):");
        nazwa3.setBounds(190,30,40,20);
        pole = new JTextField("0");
        pole.setBounds(120,31,60,20);
        pole2 = new JTextField("0");
        pole2.setBounds(230,31,60,20);

        nazwa4 = new JLabel("Prędkość Vd (m/s) :");
        nazwa4.setBounds(10,60,120,20);
        pole3 = new JTextField("0");
        pole3.setBounds(135,61,60,20);

        nazwa5 = new JLabel("Spoglądanie dt (s) :");
        nazwa5.setBounds(10,90,120,20);
        pole4 = new JTextField("0");
        pole4.setBounds(135,91,60,20);

        nazwa6 = new JLabel("Zmęczenie drapeżnika t (s) :");
        nazwa6.setBounds(10,120,180,20);
        pole5 = new JTextField("0");
        pole5.setBounds(185,121,60,20);

        p.add(nazwa);
        p.add(nazwa2);
        p.add(pole);
        p.add(nazwa3);
        p.add(pole2);
        p.add(nazwa4);
        p.add(pole3);
        p.add(nazwa5);
        p.add(pole4);
        p.add(nazwa6);
        p.add(pole5);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    drapieznik = new Obiekt(Double.valueOf(pole.getText()), Double.valueOf(pole2.getText())
                            , Double.valueOf(pole3.getText()), Integer.valueOf(pole4.getText()), 0, 0,
                            Integer.valueOf(pole5.getText()));
                } catch (Exception e2){
                    Blad b=new Blad();
                    return;
                }
                if(Double.valueOf(pole3.getText())<=0||Integer.valueOf(pole4.getText())<=0||
                        Integer.valueOf(pole5.getText())<=0){
                    Blad b=new Blad();
                    return;
                }
                menu2.dispose();
                menu3 = new MenuO(sc,pole4.getText(),pole5.getText(),drapieznik);
            }
        });

        menu2.add(napis,BorderLayout.PAGE_START);
        menu2.add(p);
        menu2.add(next,BorderLayout.PAGE_END);
        menu2.setVisible(true);
    }
}
