package BattleShip;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int turns = 0;
        LeaderBoard leaders = new LeaderBoard() {
            @Override
            public ArrayList recordLeaders(String name, int i) {
                return super.recordLeaders(name, i);
            }
        };
        //creates multiple boards

        String[][][]playerBoard = new String[3][10][10];
        String[][][]boardComputer = new String[3][10][10];
        intFillBoard(playerBoard,0);
        intFillBoard(boardComputer,1);


//    5 Aircraft Carrier --- a: Icon: Anchor "\u2693"
//    4 Battleship --------- b: Icon: Sailboat "\u26F5"
//    3 Destroyer ---------- c: Icon: Speedboat "\uD83D\uDEA4"
//    3 Submarine ---------- s: Icon: Canoe "\uD83D\uDEF6"
//    2 Tug Boat ----------- d: Icon: Ship "\uD83D\uDEE5"

        Player myPlayer = new Player() {
            @Override
            public String sayName(String name) {
                return name;
            }
        };
        //creates history of moves made by user and the AI
        ArrayList<Move> moveHistAI= new ArrayList<Move>();
        ArrayList<String> playersShips= new ArrayList<String>();
        ArrayList<String> computerShips= new ArrayList<String>();
        shipLeftFill(playersShips);
        shipLeftFill(computerShips);
        Scanner enter = new Scanner(System.in);
        space();

        if(myPlayer.name == ""){
            System.out.println("\nPlease Enter Your Name");
            Scanner name1 = new Scanner(System.in);
            String name = name1.nextLine();
        }

        System.out.print("Welcome to Battleship " + myPlayer.sayName("Adam") + " \u2693 \u2693 \u2693 \u2693. Sink The Computer Ships to Win The Game.\n\n\n");
        printBoard(playerBoard,0);

        System.out.print("\nPress enter to begin!");
        String wait = enter.nextLine();

        CompInputShips.autoFill(boardComputer);
        PlayerShips.PlayerFill(playerBoard);
        int hit=-1;
        boolean gameWon=false;

        for(int r = 0;r<10;r++){
            for(int c = 0; c<10;c++){
                boardComputer[2][r][c]=boardComputer[0][r][c];
                playerBoard[2][r][c]=playerBoard[1][r][c];
            }
        }

        while(!gameWon){
            turns++;
            hit=PlayerGuess.PlayerInput(playerBoard,boardComputer,playersShips,computerShips,moveHistAI,hit);
            if(computerShips.size()==0){
                gameWon= true;
            }
            if(!gameWon){
                ComputerPlayer.ComputerPlayerMove(playerBoard,boardComputer,playersShips,computerShips,moveHistAI);
                if(playersShips.size()==0){
                    leaders.recordLeaders("name", turns);
                    leaders.getLeaders();
                    turns = 0;
                    gameWon= true;
                }
            }
        }
    }


    //Fills board with "-" for ships to be placed on
    public static void intFillBoard(String[][][] grid, int e){
        for(int r = 0;r<10;r++){
            for(int c = 0; c<10;c++){
                grid [e][r][c] = " - ";
            }
        }
    }

    //Prints coordinates on the side of the board
    public static void printBoard(String[][][] grid, int a){
        for(int r = 0;r<10;r++){
            System.out.print(10-r +"   ");
            if(r!=0){
                System.out.print(" ");
            }
            for(int c = 0; c<10;c++){
                System.out.print(grid[a][r][c] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n      1   2   3   4   5   6   7   8   9   10");
    }

    //Method for spacing screen out
    public static void space(){
        for (int i=1;i<=30;i++){
            System.out.print("\n");
        }
    }
    public static void shipLeftFill(ArrayList<String> list){
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("s");
        list.add("d");
    }
    public static int gTOaX(int graphX){
        return graphX-1;
    }
    public static int gTOaY(int graphY){
        return 10-graphY;
    }
}
