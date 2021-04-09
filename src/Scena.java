import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Scena {
    Obiekt d, o;
    JFrame scena;
    JLabel czas;
    Punkt p;
    Timer time;
    Koniec k;
    int x, y, t, sc, reakcja, prosta, kierunek;
    int pomoc=0;
    int wygrana = 0;
    double dsx, dsy, osx, osy;

    public Scena(Obiekt d2, Obiekt o2, int s) {
        d = d2;
        o = o2;
        t = d.t;
        sc = s;
        scena = new JFrame("Drapieżnik-Ofiara");
        scena.setExtendedState(scena.MAXIMIZED_BOTH);
        scena.setVisible(true);
        scena.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        y = scena.getSize().height;
        x = scena.getSize().width;
        czas = new JLabel("Czas : " + String.valueOf(t) + " s");
        czas.setBounds(x - 150, 20, 150, 20);
        czas.setFont(new Font(czas.getName(), Font.PLAIN, 20));
        scena.add(czas);
        pozycja();
        p = new Punkt();
        p.dodaj_punkt_D(dsx, dsy);
        p.dodaj_punkt_O(osx, osy);
        scena.add(p);
        reakcja = d.dt;
        if(sc==2&&d.x==o.x)
            pomoc=1;
        time = new Timer();
        TimerTask t_task = new TimerTask() {
            @Override
            public void run() {
                if (t == 1)
                    time.cancel();
                t -= 1;
                czas.setText("Czas : " + String.valueOf(t) + " s");
                if (reakcja == d.dt) {
                    parametry();
                    reakcja = 0;
                }
                if (prosta == 0)
                    przemieszczenie();
                if (prosta == 1)
                    przemieszczenieA();
                if (prosta == 2)
                    przemieszczenieB();
                if (prosta==4)
                    przemieszczenieC();
                if (reakcja == d.dt - 1 && t != 0) {
                    p.dodaj_punkt_D(dsx, dsy);
                    p.dodaj_punkt_O(osx, osy);
                }
                reakcja++;
                if (t == 0) {
                    p.dodaj_punkt_D(dsx, dsy);
                    p.dodaj_punkt_O(osx, osy);
                    if (wygrana == 0)
                        k = new Koniec("Drapieżnik odpuścił pościg");
                }
                p.repaint();
                scena.repaint();
            }
        };
        time.schedule(t_task, 1000, 1000);
    }

    public void pozycja() {
        if (sc == 1) {
            if (d.y > o.y) {
                if (d.x > o.x) {
                    osx = x - 50 - (d.x - o.x);
                    osy = 50 + (d.y - o.y);
                    dsx = x - 50;
                    dsy = 50;
                    //zabawa z czasem
                } else {
                    osx = 10 + (o.x - d.x);
                    osy = 10 + (d.y - o.y);
                    dsx = 10;
                    dsy = 10;
                }
            } else {
                if (d.x > o.x) {
                    osx = x - 30 - (d.x - o.x);
                    osy = y - 50 - (o.y - d.y);
                    dsx = x - 30;
                    dsy = y - 50;
                } else {
                    osx = 10 + (o.x - d.x);
                    osy = y - 50 - (o.y - d.y);
                    dsx = 10;
                    dsy = y - 50;
                }
            }
        }
        if (sc == 2) {
            if (o.a > 0) {
                if (o.y <= d.y && o.x <= d.x) {
                    osx = x - 50 - (d.x - o.x);
                    osy = 50 + (d.y - o.y);
                    dsx = x - 50;
                    dsy = 50;
                    kierunek = 0;
                } else if (o.y >= d.y && o.x >= d.x) {
                    osx = 10 + (o.x - d.x);
                    osy = y - 50 - (o.y - d.y);
                    dsx = 10;
                    dsy = y - 50;
                    kierunek = 1;
                } else {
                    if (o.y <= d.y) {
                        if (d.y - o.y > o.x - d.x) {
                            osx = x - 50;
                            osy = 50 + (d.y - o.y);
                            dsx = x - 50 - (o.x - d.x);
                            dsy = 50;
                            kierunek = 0;
                        } else {
                            osx = 10 + (o.x - d.x);
                            osy = y - 50;
                            dsx = 10;
                            dsy = y - 50 - (d.y - o.y);
                            kierunek = 1;
                        }
                    } else {
                        if (o.y - d.y > d.x - o.x) {
                            osx = 10;
                            osy = y - 50 - (o.y - d.y);
                            dsx = 10 + (d.x - o.x);
                            dsy = y - 50;
                            kierunek = 1;
                        } else {
                            osx = x - 50 - (d.x - o.x);
                            osy = 50;
                            dsx = x - 50;
                            dsy = 50 + (o.y - d.y);
                            kierunek = 0;
                        }
                    }
                }
            } else {
                if (o.y >= d.y && o.x <= d.x) {
                    osx = x - 30 - (d.x - o.x);
                    osy = y - 50 - (o.y - d.y);
                    dsx = x - 30;
                    dsy = y - 50;
                    kierunek = 0;
                } else if (o.y <= d.y && o.x >= d.x) {
                    osx = 10 + (o.x - d.x);
                    osy = 10 + (d.y - o.y);
                    dsx = 10;
                    dsy = 10;
                    kierunek = 1;
                } else {
                    if (o.y <= d.y) {
                        if (d.y - o.y > d.x - o.x) {
                            osx = 10;
                            osy = 10 + (d.y - o.y);
                            dsx = 10 + (d.x - o.x);
                            dsy = 10;
                            kierunek = 1;
                        } else {
                            osx = x - 30 - (d.x - o.x);
                            osy = y - 50;
                            dsx = x - 30;
                            dsy = y - 50 - (d.y - o.y);
                            kierunek = 0;
                        }
                    } else {
                        if (o.y - d.y > o.x - d.x) {
                            osx = x - 30;
                            osy = y - 50 - (o.y - d.y);
                            dsx = x - 30 - (o.x - d.x);
                            dsy = y - 50;
                            kierunek = 0;
                        } else {
                            osx = 10 + (o.x - d.x);
                            osy = 10;
                            dsx = 10;
                            dsy = 10 + (o.y - d.y);
                            kierunek = 1;
                        }
                    }
                }
            }

        }
    }

    public void przemieszczenie() {
        double a, b, c, x, y;
        a = 1 + (d.a * d.a);
        b = -2 * d.x + 2 * d.a * (d.b - d.y);
        c = d.x * d.x + (d.b - d.y) * (d.b - d.y) - d.v * d.v;
        x = delta(a, b, c);
        y = d.a * x + d.b;
            if (d.x < o.x) {
                if (x >= o.x) {
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            } else {
                if (x <= o.x) {
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            }
        p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
        dsx += x - d.x;
        dsy -= y - d.y;
        d.x = x;
        d.y = y;

        a = 1 + (o.a * o.a);
        b = -2 * o.x + 2 * o.a * (o.b - o.y);
        c = o.x * o.x + (o.b - o.y) * (o.b - o.y) - o.v * o.v;
        if(sc==1)
            x = delta(a, b, c);
        else {
            x = delta2(a, b, c);
        }
        y = o.a * x + o.b;
        p.dodaj_linie_O(osx, osy, osx + (x - o.x), osy - (y - o.y));
        osx += x - o.x;
        osy -= y - o.y;
        o.x = x;
        o.y = y;
    }

    public void przemieszczenieA() {
        double y = d.y;
        if (d.y > o.y) {
            y -= d.v;
            if (y <= o.y) {
                y = o.y;
                time.cancel();
                wygrana = 1;
                p.dodaj_linie_D(dsx, dsy, dsx, dsy - (y - d.y));
                p.dodaj_punkt_D(dsx, dsy - (y - d.y));
                k = new Koniec("Drapieżnik złapał ofiarę");
                return;
            } else {
                p.dodaj_linie_D(dsx, dsy, dsx, dsy - (y - d.y));
                dsy -= y - d.y;
                d.y = y;
                y = o.y;
                y -= o.v;
                p.dodaj_linie_O(osx, osy, osx, osy - (y - o.y));
                osy -= y - o.y;
                o.y = y;
            }
        } else {
            y += d.v;
            if (y >= o.y) {
                y = o.y;
                time.cancel();
                wygrana = 1;
                p.dodaj_linie_D(dsx, dsy, dsx, dsy - (y - d.y));
                p.dodaj_punkt_D(dsx, dsy - (y - d.y));
                k = new Koniec("Drapieżnik złapał ofiarę");
                return;
            } else {
                p.dodaj_linie_D(dsx, dsy, dsx, dsy - (y - d.y));
                dsy -= y - d.y;
                d.y = y;
                y = o.y;
                y += o.v;
                p.dodaj_linie_O(osx, osy, osx, osy - (y - o.y));
                osy -= y - o.y;
                o.y = y;
            }
        }
    }

    public void przemieszczenieB() {
        double x = d.x;
        if (d.x > o.x) {
            x -= d.v;
            if (x <= o.x) {
                x = o.x;
                time.cancel();
                wygrana = 1;
                p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy);
                p.dodaj_punkt_D(dsx + (x - d.x), dsy);
                k = new Koniec("Drapieżnik złapał ofiarę");
                return;
            } else {
                p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy);
                dsx += x - d.x;
                d.x = x;
                x = o.x;
                x -= o.v;
                p.dodaj_linie_O(osx, osy, osx + (x - o.x), osy);
                osx += x - o.x;
                o.x = x;
            }
        } else {
            x += d.v;
            if (x >= o.x) {
                x = o.x;
                time.cancel();
                wygrana = 1;
                p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy);
                p.dodaj_punkt_D(dsx + (x - d.x), dsy);
                k = new Koniec("Drapieżnik złapał ofiarę");
                return;
            } else {
                p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy);
                dsx += x - d.x;
                d.x = x;
                x = o.x;
                x += o.v;
                p.dodaj_linie_O(osx, osy, osx + (x - o.x), osy);
                osx += x - o.x;
                o.x = x;
            }
        }

    }

    public void parametry() {
        if(pomoc==1) {
            prosta = 4;
            d.a = (d.y - o.y) / (d.x - o.x);
            d.b = d.y - (d.y - o.y) / (d.x - o.x) * d.x;
            if(d.x==o.x)
                d.a=0;
        }
        else if (d.x == o.x) {
            prosta = 1;
        } else if (d.y == o.y) {
            prosta = 2;
        } else {
            prosta = 0;
            d.a = (d.y - o.y) / (d.x - o.x);
            d.b = d.y - (d.y - o.y) / (d.x - o.x) * d.x;
            if (sc == 1) {
                o.a = d.a;
                o.b = d.b;
            }
        }
    }

    public double delta(double a, double b, double c) {
        double dt = b * b - 4 * a * c;
        if (dt > 0) {
            double x1 = (-b - Math.sqrt(dt)) / (2 * a);
            double x2 = (-b + Math.sqrt(dt)) / (2 * a);
            if (d.x < o.x) {
                if (x1 > x2)
                    return x1;
                else
                    return x2;
            } else if (d.x > o.x) {
                if (x1 < x2)
                    return x1;
                else
                    return x2;
            } else
                return 0;
        } else if (dt == 0) {
            return -b / 2 * a;
        } else {
            return 0;
        }
    }

    public double delta2(double a, double b, double c) {
        double dt = b * b - 4 * a * c;
        if (dt > 0) {
            double x1 = (-b - Math.sqrt(dt)) / (2 * a);
            double x2 = (-b + Math.sqrt(dt)) / (2 * a);
            if (kierunek == 1) {
                if (x1 > x2)
                    return x1;
                else
                    return x2;
            } else {
                if (x1 < x2)
                    return x1;
                else
                    return x2;
            }
        } else if (dt == 0) {
            return -b / 2 * a;
        } else {
            return 0;
        }
    }
    public void przemieszczenieC(){
        double a, b, c, x, y;
        if(d.a!=0) {
            a = 1 + (d.a * d.a);
            b = -2 * d.x + 2 * d.a * (d.b - d.y);
            c = d.x * d.x + (d.b - d.y) * (d.b - d.y) - d.v * d.v;
            x = delta(a, b, c);
            y = d.a * x + d.b;
            if (d.x < o.x) {
                if (x >= o.x) {
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            } else {
                if (x <= o.x) {
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            }
        }
        else{
            x=d.x;
            y=d.y;
            if(d.y<o.y) {
                y += d.v;
                if(y>=o.y){
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            }
            else {
                y -= d.v;
                if(y<=o.y){
                    x = o.x;
                    y = o.y;
                    time.cancel();
                    wygrana = 1;
                    p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
                    p.dodaj_punkt_D(dsx + (x - d.x), dsy - (y - d.y));
                    k = new Koniec("Drapieżnik złapał ofiarę");
                    return;
                }
            }
        }
        p.dodaj_linie_D(dsx, dsy, dsx + (x - d.x), dsy - (y - d.y));
        dsx += x - d.x;
        dsy -= y - d.y;
        d.x = x;
        d.y = y;
        if(d.y>o.y){
            x=o.x;
            x+=o.v;
            p.dodaj_linie_O(osx, osy, osx + (x - o.x), osy);
            osx+=x-o.x;
            o.x=x;
        }
        else{
            x=o.x;
            x-=o.v;
            p.dodaj_linie_O(osx, osy, osx + (x - o.x), osy);
            osx+=x-o.x;
            o.x=x;
        }

    }
}
