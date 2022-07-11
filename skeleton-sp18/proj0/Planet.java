public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel; //x方向速度
    public double yyVel; //y方向速度
    public double mass; //质量
    public String imgFileName;
    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet b){
        double x = this.xxPos - b.xxPos;
        double y = this.yyPos - b.yyPos;
        return Math.sqrt(x*x+y*y);
    }
    public double calcForceExertedBy(Planet b){
        double d = this.calcDistance(b);
        double f = 6.67*Math.pow(10, -11)*this.mass*b.mass/(d*d);
        return f;
    }
    public double calcForceExertedByX(Planet b){
        double f = this.calcForceExertedBy(b);
        double dx = b.xxPos-this.xxPos;
        double fx = f*dx/this.calcDistance(b);
        return fx;
    }
    public double calcForceExertedByY(Planet b){
        double f = this.calcForceExertedBy(b);
        double dy = b.yyPos-this.yyPos;
        double fy = f*dy/this.calcDistance(b);
        return fy;
    }
    public double calcNetForceExertedByX(Planet[] list){
        double total_fx=0;
        for(int i=0;i<list.length;i++){
            if(this.equals(list[i])==false){
                total_fx += this.calcForceExertedByX(list[i]);
            }
        }
        return total_fx;
    }
    public double calcNetForceExertedByY(Planet[] list){
        double total_fy=0;
        for(int i=0;i<list.length;i++){
            if(this.equals(list[i])==false){
                total_fy += this.calcForceExertedByY(list[i]);
            }
        }
        return total_fy;
    }
    public void update(double dt,double fx,double fy){
        double ax = fx/this.mass;
        double ay = fy/this.mass;
        this.xxVel += ax*dt;
        this.yyVel += ay*dt;
        this.xxPos += this.xxVel*dt;
        this.yyPos += this.yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
