import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
//imorted necessary libraries so far
public class USACO{
  public static int bronze(String filename) throws FileNotFoundException{
    File LakeData = new File(filename);
    Scanner scanner = new Scanner(LakeData);
    //read first line and saving elevation;
    int[] ln1 = new int[4];
    for(int i = 0; i<ln1.length; i++){
      ln1[i]=scanner.nextInt();}
    int elevation = ln1[2];
    //reading lake matrix;
    int[][] lake = new int[ln1[0]][ln1[1]];
    for(int row = 0; row<lake.length;row++){
      for(int col =0; col<lake[0].length;col++){
        lake[row][col]=scanner.nextInt();
      }
    }
    //reading instructions;
    int[][] instructions = new int[ln1[3]][3];
    for(int inst = 0; inst<instructions.length;inst++){
      instructions[inst][0]=scanner.nextInt();
      instructions[inst][1]=scanner.nextInt();
      instructions[inst][2]=scanner.nextInt();
    }
    for(int[] g :instructions){
      int r =g[0]-1;
      int c =g[1]-1;
      //creating patch of 9x9 plot for cows to stomp on
      int[][] patch = new int[][]{
        {r+0,c+0},
        {r+1,c+0},
        {r+2,c+0},
        {r+0,c+1},
        {r+1,c+1},
        {r+2,c+1},
        {r+0,c+2},
        {r+1,c+2},
        {r+2,c+2}
      };
      int highest = lake[r][c];
      for(int[] i : patch){
        if(lake[i[0]][i[1]]>=highest){
          highest = lake[i[0]][i[1]];
          r = i[0];
          c = i[1];
        }
      }
      lake[r][c]-=g[2];
      for(int[] i:patch){
        if(lake[i[0]][i[1]]>lake[r][c]){
          lake[i[0]][i[1]] = lake[r][c];
        }
      }
    }
    int depth =0;
    for(int i=0; i<lake.length;i++){
      for(int j=0;j<lake[0].length;j++){
        if(elevation-lake[i][j]>0){
          depth+=elevation-lake[i][j];
        }
      }
    }

    return depth*72*72;
    }
  public static void main(String[] args) throws FileNotFoundException {
  }
}
