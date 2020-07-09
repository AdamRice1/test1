package BattleShip;

import java.util.ArrayList;
class ComputerPlayer{
    public static void ComputerPlayerMove(String[][][]userBoard,String[][][]compBoard,ArrayList<String> userShips,ArrayList<String> ComputerPlayerShips,ArrayList<Move> moveHist){
        boolean validShip=false;
        int rowCoord=-1;
        int colCoord=-1;
        while(!validShip){
            rowCoord=(int)(Math.random()*10);
            colCoord=(int)(Math.random()*10);
            if((rowCoord+colCoord)%2==0){
                validShip=true;
            }
            try{
                if(userBoard[1][rowCoord][colCoord].equals("ged")){
                    validShip=false;
                }
            }
            catch(Exception e){}
        }
        String shipPlace= userBoard[2][rowCoord][colCoord];
        int shipHit;
        boolean shipHitB;
        boolean shipDes;
        shipHit=guessResultComputerPlayer(userBoard,userShips,moveHist,colCoord,rowCoord);
        if(shipHit==1){
            shipHitB= true;
            shipDes= false;
        }
        else if(shipHit==2){
            shipHitB=true;
            shipDes=true;
        }
        else{
            shipHitB=false;
            shipDes=false;
        }
        moveHist.add(new Move(colCoord,rowCoord,shipHitB,shipDes,shipPlace));
    }

    public static int guessResultComputerPlayer(String[][][]adjBoard,ArrayList<String> shipList,ArrayList<Move> moveHist,int x,int y){
        String boardPlace=adjBoard[1][y][x];
        int shipHit=0;
        adjBoard[1][y][x]="ged";
        if(boardPlace==null){
            adjBoard[0][y][x]=" O ";
        }
        else{
            shipHit=1;
            adjBoard[0][y][x]=" \u001B[31mX\u001B[0m ";
            if(!shipContainComputerPlayer(adjBoard,boardPlace)){
                shipList.remove(shipList.indexOf(boardPlace));
                shipHit=2;
            }
        }
        return shipHit;
    }

    public static boolean shipContainComputerPlayer(String[][][]board,String ship){
        for(int r = 0;r<10;r++){
            for(int c = 0; c<10;c++){
                try{
                    if(board[1][r][c].equals(ship)){
                        return true;
                    }
                }
                catch(Exception e){}
            }
        }
        return false;
    }

}
