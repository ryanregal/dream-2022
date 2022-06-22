import java.util.Scanner;
public class Drawer{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        TurtleGraphics tg = new TurtleGraphics();
        boolean running = true;
        while(running){
            tg.printMenu();//¥Ú”°≤Àµ•
            switch(sc.nextInt()){
                case 0:
                    tg.setPen();
                    break;
                case 1:
                    tg.penUp();
                    break;
                case 2:
                    tg.penDown();
                    break;
                case 3:
                    tg.turnRight();
                    break;
                case 4:
                    tg.turnLeft();
                    break;
                case 5:
                    tg.forward(sc.nextInt());
                    break;
                case 6:
                    tg.showGrid();
                    break;
                case 8:
                    tg.reset();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}