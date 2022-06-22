import java.util.Arrays;
import java.util.Scanner;

public class TurtleGraphics{
    Scanner sc = new Scanner(System.in);
    private final int GRID_ROWS = 20;//20行
    private final int GRID_COLS = 20;//20列
    private enum Direction{UP, DOWN, LEFT, RIGHT};//储存方向
    private int[][] arrGrid = new int[GRID_ROWS][GRID_COLS];
    private int[] coords2D = new int[2];//0储存第几行，1储存第几列
    private boolean penDown = false;
    private int endPoint;
    private char pen = '*';//用*来绘图案
    private Direction drawDir = Direction.RIGHT;
    // 构造函数
    public TurtleGraphics(){
        reset();// 将网格和坐标重置为默认值
    }
    // 更改画笔
    public void setPen(){
        System.out.printf("\nCurrent pen: %c\n", pen);
        System.out.print("Enter new pen character: ");
        pen = sc.next().charAt(0);
    }
    //抬笔
    public void penUp(){
        if(penDown == true) penDown = false;
    }
    // 落笔
    public void penDown(){
        if(penDown == false) penDown = true;
        // 在当前位置绘点
        arrGrid[coords2D[0]][coords2D[1]] = 1;
    }
    // 左转
    public void turnLeft(){
        switch(drawDir){
            case RIGHT:
                drawDir = Direction.UP;
                break;
            case UP:
                drawDir = Direction.LEFT;
                break;
            case LEFT:
                drawDir = Direction.DOWN;
                break;
            case DOWN:
                drawDir = Direction.RIGHT;
                break;
        }
    }  
    // 右转
    public void turnRight(){
        switch(drawDir){
            case RIGHT:
                drawDir = Direction.DOWN;
                break;
            case DOWN:
                drawDir = Direction.LEFT;
                break;
            case LEFT:
                drawDir = Direction.UP;
                break;
            case UP:
                drawDir = Direction.RIGHT;
                break;
        }
    }
    // 打印所在位置与画笔的方向
    public void getLocationDirection(){
    	 System.out.println("------------------------");
         System.out.printf("\nPen is at location (%d,%d)", coords2D[0], coords2D[1]);
         System.out.printf("\nDrawing direction is %s\n", drawDir);
         System.out.println("\n------------------------\n");
    }
    // 在drawDir方向移动n步
    public void forward(int distance){
        switch(drawDir){
            case RIGHT:
                endPoint = coords2D[1] + distance;// move right
                if(endPoint >= GRID_COLS)//当超过绘图区
                    endPoint = GRID_COLS - 1;//删去超出部分
                if(penDown){ // 绘制路径
                    for(int col=coords2D[1]; col<endPoint; col++){
                        arrGrid[coords2D[0]][col] = 1;
                    }
                }
                coords2D[1] = endPoint;//更改画笔坐标
                break;
               
            case LEFT: 
                endPoint = coords2D[1] - distance;// move left
                // check out of bounds
                if(endPoint <= 0)  endPoint = 0;
                if(penDown){ // 绘制路径
                    for(int col=coords2D[1]; col>=endPoint; col--){
                        arrGrid[coords2D[0]][col] = 1;
                    }
                }
                coords2D[1] = endPoint;//更改画笔坐标
                break;
               
            case UP:
                endPoint = coords2D[0] - distance; // move up
                // check out of bounds
                if(endPoint <= 0)  endPoint = 0;
                if(penDown){ // 绘制路径
                    for(int row=coords2D[0]; row>=endPoint; row--){
                        arrGrid[row][coords2D[1]] = 1;
                    }
                }
                coords2D[0] = endPoint;//更改画笔坐标
                break;

            case DOWN:
                endPoint = coords2D[0] + distance;  // move down
                // check out of bounds
                if(endPoint >= GRID_ROWS) endPoint = GRID_ROWS - 1;
                if(penDown){  // 绘制路径
                    for(int row=coords2D[0]; row<endPoint; row++){
                        arrGrid[row][coords2D[1]] = 1;
                    }
                }

                coords2D[0] = endPoint;//更改画笔坐标
                break;
        }
    }
    // 打印画布
    public void showGrid(){
        System.out.println();
        for(int row=0; row<arrGrid.length; row++){
            // border
            System.out.print("| ");
            for(int column=0; column<arrGrid[row].length; column++){
            	//如果arrGrid被标记为1过，则打印*，否则打印空格
                System.out.printf("%c",
                        (arrGrid[row][column] == 1) ? pen : ' ');
            }
            System.out.print(" |");//边界
            System.out.println();//空行
        }
    }
    // 将网格和坐标重置为默认值
    public void reset(){
        // reset each row
        for(int[] row : arrGrid)
            Arrays.fill(row, 0);
        // 画笔坐标
        coords2D[0] = 0;
        coords2D[1] = 0;
    }
    // 打印命令菜单
    public void printMenu(){
        getLocationDirection();
        System.out.println("0. Change Pen\n1. Pen Up\n2. Pen Down\n3. Turn Right\n4. Turn Left");
        System.out.println("5. Move forward n spaces");
        System.out.println("6. Display the Drawing\n8. Reset Drawing\n9. Finish Drawing");
        System.out.print("Please input:");
    }
}