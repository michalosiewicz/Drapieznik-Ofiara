import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Punkt extends JPanel{
    ArrayList<Line2D>linieD= new ArrayList<Line2D>();
    ArrayList<Line2D>linieO= new ArrayList<Line2D>();
    ArrayList<Ellipse2D>punktD= new ArrayList<Ellipse2D>();
    ArrayList<Ellipse2D>punktO= new ArrayList<Ellipse2D>();
    public void dodaj_linie_D(double x,double y,double x2,double y2){
        linieD.add(new Line2D.Double(x,y,x2,y2));
    }
    public void dodaj_linie_O(double x,double y,double x2,double y2){
        linieO.add(new Line2D.Double(x,y,x2,y2));
    }
    public void dodaj_punkt_D(double x,double y){
        punktD.add(new Ellipse2D.Double(x-1,y-1,2,2));
    }
    public void dodaj_punkt_O(double x,double y){
        punktO.add(new Ellipse2D.Double(x-1,y-1,2,2));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        for(int i=0;i<punktD.size();i++){
            g2.fill(punktD.get(i));
            g2.draw(punktD.get(i));
        }
        for(int i=0;i<linieD.size();i++){
            g2.draw(linieD.get(i));
        }
        g2.setPaint(Color.red);
        for(int i=0;i<punktO.size();i++){
            g2.fill(punktO.get(i));
            g2.draw(punktO.get(i));
        }
        for(int i=0;i<linieO.size();i++){
            g2.draw(linieO.get(i));
        }
    }
}
