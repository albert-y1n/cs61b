public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] array = new Planet[num];
        for(int i=0;i<num;i++){
            array[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return array;
    }
    public static void main(String[] args){
        double T = Float.parseFloat(args[0]);
        double dt = Float.parseFloat(args[1]);
        String filename = args[2];
        double radius= NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        String img = "images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,img);
        for(int i=0;i<planets.length;i++){
            planets[i].draw();
        }
        for(double t=0;t<T;t+=dt){
            double[] xforces = new double[planets.length];
            double[] yforces = new double[planets.length];
            for(int i=0;i<planets.length;i++){
                xforces[i] = planets[i].calcNetForceExertedByX(planets);
                yforces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt, xforces[i], yforces[i]);
            }
            StdDraw.setScale(-radius,radius);
            StdDraw.picture(0,0,img);
            for(int i=0;i<planets.length;i++){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
