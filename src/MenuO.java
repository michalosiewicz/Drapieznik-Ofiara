import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuO {
    JFrame menu2;
    JLabel nazwa,napis,nazwa2,nazwa3,nazwa4;
    JTextField pole,pole2,pole3,pole4,pole5;
    JButton next;
    Scena scena1;
    Obiekt ofiara;
    public MenuO(int scena,String dt,String t,Obiekt d){
        menu2 = new JFrame("Ofiara");
        menu2.setSize(400,300);
        menu2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        next= new JButton("DALEJ");
        napis = new JLabel("Ofiara",JLabel.CENTER);
        napis.setFont(new Font(napis.getName(),Font.PLAIN,20));
        JPanel p= new JPanel();
        p.setLayout(null);
        nazwa = new JLabel("Punkt Po");
        nazwa.setBounds(10,30,60,20);
        nazwa2 = new JLabel("x(m):");
        nazwa2.setBounds(80,30,40,20);
        nazwa3 = new JLabel("y(m):");
        nazwa3.setBounds(190,30,40,20);
        pole = new JTextField("0");
        pole.setBounds(120,31,60,20);
        pole2 = new JTextField("0");
        pole2.setBounds(230,31,60,20);

        nazwa4 = new JLabel("Prędkość Vo (m/s) :");
        nazwa4.setBounds(10,60,120,20);
        pole3 = new JTextField("0");
        pole3.setBounds(135,61,60,20);

        if(scena==1) {
            JLabel nazwa5 = new JLabel("Spoglądanie dt (s) :");
            nazwa5.setBounds(10, 90, 120, 20);
            JLabel nazwa6 = new JLabel(dt);
            nazwa6.setBounds(135, 91, 60, 20);
            p.add(nazwa5);
            p.add(nazwa6);
        }

        if(scena==2){
            JLabel nazwa5=new JLabel("Parametry prostej");
            nazwa5.setBounds(10, 90, 120, 20);
            JLabel nazwa6 = new JLabel("a:");
            nazwa6.setBounds(130,90,20,20);
            JLabel nazwa7 = new JLabel("b:");
            nazwa7.setBounds(220,90,20,20);
            pole4 = new JTextField("0");
            pole4.setBounds(150,91,60,20);
            pole5 = new JTextField("0");
            pole5.setBounds(240,91,60,20);
            p.add(nazwa5);
            p.add(nazwa6);
            p.add(nazwa7);
            p.add(pole4);
            p.add(pole5);
        }

        p.add(nazwa);
        p.add(nazwa2);
        p.add(pole);
        p.add(nazwa3);
        p.add(pole2);
        p.add(nazwa4);
        p.add(pole3);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(scena==1) {
                    try {
                        ofiara = new Obiekt(Double.valueOf(pole.getText()), Double.valueOf(pole2.getText())
                                , Double.valueOf(pole3.getText()), Integer.valueOf(dt), 0, 0,
                                Integer.valueOf(t));
                    }
                    catch (Exception e1){
                        Blad b=new Blad();
                        return;
                    }
                    if(Double.valueOf(pole3.getText())<=0){
                        Blad b=new Blad();
                        return;
                    }
                    if(d.x==Double.valueOf(pole.getText())&&d.y==Double.valueOf(pole2.getText())){
                        Blad b=new Blad();
                        return;
                    }
                    menu2.dispose();
                    scena1= new Scena(d,ofiara,scena);
                }
                if(scena==2){
                    try {
                        ofiara = new Obiekt(Double.valueOf(pole.getText()), Double.valueOf(pole2.getText())
                                , Double.valueOf(pole3.getText()), 0, Double.valueOf(pole4.getText())
                                , Double.valueOf(pole5.getText()), Integer.valueOf(t));
                    }
                    catch (Exception e1){
                        Blad b=new Blad();
                        return;
                    }
                    if(Double.valueOf(pole3.getText())<=0||Double.valueOf(pole2.getText())!=
                            Double.valueOf(pole4.getText())*Double.valueOf(pole.getText())+
                                    Double.valueOf(pole5.getText())){

                        Blad b=new Blad();
                        return;
                    }
                    menu2.dispose();
                    scena1= new Scena(d,ofiara,scena);
                }
            }
        });

        menu2.add(napis,BorderLayout.PAGE_START);
        menu2.add(p);
        menu2.add(next,BorderLayout.PAGE_END);
        menu2.setVisible(true);

    }
}
