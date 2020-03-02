import javafx.scene.shape.Rectangle;

/**
 * Created by edisongrauman on 1/31/20.
 */
public class NoteGrid {

    int xAmt;
    int yAmt;

    OneGrid[][] grids;

    public NoteGrid(int xAmt, int yAmt) {
        this.xAmt = xAmt;
        this.yAmt = yAmt;

        grids = new OneGrid[xAmt][yAmt];
        for (int x = 0; x < xAmt; x++) {
            for (int y = 0; y < yAmt; y++) {
                grids[x][y] = new OneGrid((5*x)+y);
            }
        }
    }

    public OneGrid getGrid(int x, int y) {
        return grids[x][y];
    }


}

 class OneGrid {

    int num;
    String name;

    OneGrid(int n) {
        num = n;
//        System.out.println(num);
    }

     public int getNum() {
         return num;
     }

     public void setNum(int num) {
         this.num = num;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 }
