package BattleShip;

import java.util.ArrayList;
import java.util.Scanner;

class PlayerGuess{
    public static int PlayerInput(String[][][]playerBoard,String[][][]compBoard,ArrayList<String> playerShips,ArrayList<String> aiShips,ArrayList<Move> moveHistAI,int hit){
        boolean gotXCoord=false;
        boolean gotYCoord=false;
        boolean cordsSet=false;
        boolean done=false;
        int xCoord=0;
        int yCoord=0;
        Main.space();
        while(!cordsSet){
            gotXCoord=false;
            gotYCoord=false;
            if(hit==0){
                System.out.print("\nMiss\n");
            }
            else if(hit==1){
                System.out.print("\nHit!\n");
            }
            else if(hit==2){
                System.out.print("\nYou sunk my battleship ship!\n");
                // This is a joke for the Simpsons ^^^^^
            }
            hit=-1;
            while(!gotXCoord){
                selectScreen(playerBoard,compBoard);
                System.out.print("\n(");
                xCoord= getCoord();
                if(xCoord!=-1){
                    gotXCoord=true;
                }
            }

            Main.space();
            while(!gotYCoord){
                selectScreen(playerBoard,compBoard);
                System.out.print("You can start your guess over");
                System.out.print("\n("+xCoord+",");
                yCoord= getCoord();

                if(yCoord>0){
                    gotYCoord=true;
                    if((compBoard[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)]==null)||!compBoard[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)].equals("ged")){
                        cordsSet=true;
                        done=true;
                        gotYCoord=true;
                    }
                    else{
                        yCoord=-3;
                    }
                }
                Main.space();
                if(yCoord==-3){
                    System.out.print("\nAlready guessed that!\n");
                }
                else if(yCoord<0){
                    System.out.print("\nInvalid Input\n");
                }
                if(yCoord<=-3){
                    done=false;
                    gotYCoord=true;
                }
            }
            if(!done){
                gotXCoord=false;
                gotYCoord=false;
                cordsSet=false;
                xCoord=0;
                yCoord=0;
            }
        }
        return guessResults(compBoard,aiShips,moveHistAI,xCoord,yCoord);
    }
    public static int guessResults(String[][][]adjBoard,ArrayList<String> shipList,ArrayList<Move> moveHist,int x,int y){
        x=Main.gTOaX(x);
        y=Main.gTOaY(y);
        String boardPlace=adjBoard[0][y][x];
        int hit;
        adjBoard[0][y][x]="ged";
        if(boardPlace==null){
            hit=0;
            adjBoard[1][y][x]=" O ";
        }
        else{
            if(shipContain(adjBoard,boardPlace)){
                hit=1;
                adjBoard[1][y][x]="\uD83D\uDCA5";
            }
            else{
                String SunkShip="";
                hit=2;
                int length=0;
                if (boardPlace.equals("a")){
                    SunkShip="\uD83C\uDD98";
                }
                else if(boardPlace.equals("b")){
                    SunkShip="\uD83C\uDD98";
                }
                else if(boardPlace.equals("c")){
                    SunkShip="\uD83C\uDD98";
                }
                else if(boardPlace.equals("s")){
                    SunkShip="\uD83C\uDD98";
                }
                else if(boardPlace.equals("d")){
                    SunkShip="\uD83C\uDD98";
                }
                for(int r = 0;r<10;r++){
                    for(int c = 0; c<10;c++){
                        try{
                            if(adjBoard[2][r][c].equals(boardPlace)){
                                adjBoard[1][r][c] = (SunkShip+" o\u001B[0m ");
                            }
                        }
                        catch(Exception e){}
                    }
                }
                shipList.remove(shipList.indexOf(boardPlace));
            }
        }
        return hit;
    }


    public static int getCoord(){
        Scanner coordInput = new Scanner(System.in);
        String stringCoord = coordInput.nextLine();
        try{
            int newCoord = Integer.parseInt(stringCoord);
            if(newCoord>10||newCoord<1){
                throw new Exception();
            }
            return newCoord;
        }
        catch(Exception e){
            Main.space();
            System.out.print("\nInvalid Input\n");
            stringCoord=stringCoord.toUpperCase();
            if(stringCoord.equals("CANCEL")){
                return -2;
            }
        }
        return -1;
    }

    public static boolean shipContain(String[][][]board,String ship){
        for(int r = 0;r<10;r++){
            for(int c = 0; c<10;c++){
                try{
                    if(board[0][r][c].equals(ship)){
                        return true;
                    }
                }
                catch(Exception e){}
            }
        }
        return false;
    }

    public static void selectScreen(String[][][]playerBoard,String[][][]compBoard){
        System.out.print("\nYour Turn To Guess\n");
        Main.printBoard(playerBoard,0);
        System.out.print("\n\nComputers Board\n");
        Main.printBoard(compBoard,1);
    }

}

