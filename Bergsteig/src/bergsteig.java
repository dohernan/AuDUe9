import java.util.ArrayList;

public class bergsteig {





    public static void main(String []args){
        //boolean [][]panel = new boolean[5][5];
        //for(int i=0;i<panel[0].length;i++) {
        //    for (int j = 0; j < panel[0].length; j++) {
        //        panel[i][j]=true;
        //    }
        //}
        //panel[0][2]=false;
        //panel[1][1]=false;
        //panel[1][3]=false;
        //panel[2][1]=false;
        //panel[3][4]=false;
        //panel[4][2]=false;
//
        //int [] result = bersteig2(panel,-2,4,1);
        //result = bersteig2(panel,4,4,1);
        ////result = bersteig(panel,4,0,0,3,1);
        int [][]M = new int[4][2];
        M[0][0]=0;
        M[0][1]=2;
        M[1][0]=0;
        M[1][1]=3;
        M[2][0]=1;
        M[2][1]=2;
        M[3][0]=1;
        M[3][1]=1;

        M = new int[3][4];

        for(int i=0; i<M[0].length;i++){
            M[0][i]=i;
            M[1][i]=i+M[0].length;
            M[2][i]=i+2*M[0].length;
        }
        M=combinations(M);
        System.out.println(existiertMenge(M,4));
    }

    public static int[][] combinations(int[][] X){
        int[][] M= new int[(int)Math.pow(X[0].length,X.length)][X.length];
        int a=0;
        int b=0;
        for(int i=0;i<X[0].length;i++){
            for (int j = 0; j < X[1].length; j++) {
                for (int k = 0; k < X[2].length; k++) {

                        M[b][0]=X[a][i];
                        M[b][1]=X[a+1][j];
                        M[b][2]=X[a+2][k];
                        b++;
                }
            }
        }
        return M;
    }

    public static int[] bersteig(boolean [][]panel, int x, int y, int zx, int zy,int e){

        int ax=x;
        int ay=y;

        int ex=x;
        int ey=y;

        int dist=2*panel[0].length;
        int adist=f(x,y,zx,zy);

        while(adist<dist) {
            dist=adist;
            for (int i = 1; i > -2; i -= 2) {
                if (((i==1&&y<4)||(i==-1&&y>0))&&panel[x][y + i]) {
                    if (f(x, y + i, zx, zy) < adist) {
                        ax=x;
                        ay=y+i;
                        adist = f(ax,ay,zx,zy);

                    }
                }
                if (((i==-1&&x<4)||(i==1&&x>0))&&panel[x - i][y]) {
                    if (f(x - i, y, zx, zy) < adist) {
                        ax=x-i;
                        ay=y;
                        adist = f(ax,ay,zx,zy);
                    }
                }
            }
            System.out.println(ax+" "+ay);
            x=ax;
            y=ay;
        }

        int [] result = new int[2];
        result[0]=x;
        result[1]=y;
        return result;
    }


    public static int[] bersteig2(boolean [][]panel, int x, int y,int e){

        int ax=x;
        int ay=y;

        double dist=Double.NEGATIVE_INFINITY;
        double adist=f2(x,y);

        while(adist>dist) {
            dist=adist;
            for(int i=x-e;i<x+e+1;i++){
                for(int j=y-e; j<y+e+1;j++){
                    if(d(x,y,i,j)<=e){
                        if(f2(i,j)>adist) {
                            ax = i;
                            ay = j;
                            adist = f2(ax, ay);
                        }
                    }
                }
            }
            System.out.println(ax+" "+ay);
            x=ax;
            y=ay;
        }

        int [] result = new int[2];
        result[0]=x;
        result[1]=y;
        return result;
    }

    public static int f(int x1,int y1,int x2,int y2){

        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }


    public static double f2(int x1, int y1){
        return Math.pow(2,-(x1*x1)-(y1*y1)+1)+3*Math.pow(2,-(x1*x1)-(y1*y1)+2*x1+4*y1-5);
    }
    public static int d(int x1, int y1,int x2,int y2){
        return Math.max(Math.abs(x1-x2),Math.abs(y1-y2));
    }



    //H2

    public static boolean existiertMenge(int[][] M, int q){

        ArrayList<int[]> points = new ArrayList<>();
        return existierMengeBack(M, q, points,0,0);

    }
    public static boolean existierMengeBack(int[][] M, int q, ArrayList<int[]> points, int s,int a){
        if(points.size()>1){
            if(sindVerschieden(points))
                return true;
            else
                return false;
        }
        else{
            while(points.size()<q){
                if(s<M.length) {
                    points.add(M[s]);
                }
                else{
                    points.remove(points.size()-1);
                    a++;
                    s=a;
                    if(a==M.length)
                        return false;
                    else
                        points.add(M[a]);
                }
                if(!existierMengeBack(M,q,points,s+1,a)){
                    if(points.size()>0) {
                        points.remove(points.size() - 1);
                    }
                    else
                        return false;
                }
                s++;

            }
        }
        System.out.println("gefunden");
        for(int i=0;i<points.size();i++) {
            for (int j = 0; j < points.get(0).length; j++) {
                System.out.println(points.get(i)[j]);
            }
        }
        return true;
    }

    public static boolean sindVerschieden( ArrayList<int[]> points){

        if(points.size()<1){
            return false;
        }

        for(int i=0;i<points.get(0).length;i++) {
            for (int j = 0; j < points.size(); j++) {
                for (int k = j+1; k < points.size(); k++) {
                    if(points.get(j)[i]==points.get(k)[i]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
