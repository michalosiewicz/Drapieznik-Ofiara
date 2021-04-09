public class Obiekt {
    double x,y; // wspolrzedne punktu obiektu
    double v; // predkosc obietku
    int dt; // co jaki czas obiekt spoglada
    double a,b; // parametry prostej obiektu
    int t; //czas po jakim zmęczy się drapeżnik

    public Obiekt(double x1,double y1,double v1,int t1,double a1,double b1,int t2){
        x=x1;
        y=y1;
        v=v1;
        dt=t1;
        a=a1;
        b=b1;
        t=t2;
    }

}
