import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
//imorted necessary libraries so far
public class USACO{
  private int[] ln1;
  private int elevation;
  private int[][] lake;
  private int[][] instructions;
  public USACO(String f) throws FileNotFoundException{
    read(f);
  }
  private void read(String Filename) throws FileNotFoundException{
    File LakeData = new File(Filename);
    Scanner scanner = new Scanner(LakeData);
    //read first line and saving elevation;
    ln1 = new int[4];
    for(int i = 0; i<ln1.length; i++){
      ln1[i]=scanner.nextInt();}
    elevation = ln1[2];
    //reading lake matrix;
    lake = new int[ln1[0]][ln1[1]];
    for(int row = 0; row<lake.length;row++){
      for(int col =0; col<lake[0].length;col++){
        lake[row][col]=scanner.nextInt();
      }
    }
    //reading instructions;
    instructions = new int[ln1[3]][3];
    for(int inst = 0; inst<instructions.length;inst++){
      instructions[inst][0]=scanner.nextInt();
      instructions[inst][1]=scanner.nextInt();
      instructions[inst][2]=scanner.nextInt();
    }
  }
  private void operate(int[] directions){
    int r =directions[0]-1;
    int c =directions[1]-1;
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
    lake[r][c]-=directions[2];
    for(int[] i:patch){
      if(lake[i[0]][i[1]]>lake[r][c]){
        lake[i[0]][i[1]] = lake[r][c];
      }
    }
  }
  public static String printthis(int[][] board){
    String brd="";
      for(int i=0;i<board.length;i++){
        for(int j=0;j<board[0].length;j++){
          if(board[i][j]<10)brd+=" ";
            if(board[i][j]==0)brd+="_";
            else brd+=board[i][j];
           brd+=" ";
          }
        brd = brd.substring(0, brd.length() - 1);
        brd+="\n\n";
      }
    return brd;
    }
  public static void main(String[] args) throws FileNotFoundException {
    USACO test = new USACO("testFile.txt");
    System.out.println("\nSuccessfuly Read and Stored File \nHere's the First Line:");
    System.out.print(Arrays.toString(test.ln1));
    System.out.println("\nHere's the Lake:");
    System.out.print(printthis(test.lake));
    System.out.println("\nAnd Here are the Instructions:");
    System.out.print(printthis(test.instructions));
    test.operate(test.instructions[0]);
    System.out.println("\nJust Finished Stomping with given directions");
    System.out.print(printthis(test.lake));
  }
}
