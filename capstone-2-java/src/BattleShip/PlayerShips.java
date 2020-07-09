package BattleShip;

import java.util.Scanner;
class PlayerShips{
    public static void PlayerFill(String[][][] grid){
        String shipsLeftList="5, 4, 3, 3, 2";
        String shipNumOnWord="first";
        int shipLengthOn=5;

        // To place All ships
        for(int r = 0;r<5;r++){
            if(r==1){
                shipsLeftList="4, 3, 3, 2";
                shipNumOnWord="second";
            }
            if(r==2){
                shipsLeftList="3, 3, 2";
                shipNumOnWord="third";
            }
            if(r==3){
                shipsLeftList="3, 2";
                shipNumOnWord="fourth";
                shipLengthOn++;
            }
            if(r==4){
                shipsLeftList="2";
                shipNumOnWord="last";
            }
            Main.space();
            firstShipCoord(shipsLeftList,shipNumOnWord,shipLengthOn,grid);
            shipLengthOn--;
        }
        Main.space();
        Main.printBoard(grid,0);
    }


    public static void firstShipCoord(String a,String b,int c, String[][][] grid){
        // Set ship location
        boolean gotXCoord=false;
        boolean gotYCoord=false;
        boolean gotDir=false;
        boolean cordsSet=false;
        boolean done=false;
        int xCoord=0;
        int yCoord=0;
        int dirY=0;
        int dirX=0;
        int lastX=0;
        int lastY=0;
        String coordInput="";
        String b4Space="";

        while(!done){
            done=true;

            while(!cordsSet){
                gotXCoord=false;
                gotYCoord=false;
                while(!gotXCoord){
                    Main.space();
                    System.out.print("\tShips left to place: "+a+"\n\n\n\n"+"Where would you like to place your next ship?\n\nYour "+b+" ship's length is "+c+". \n\nPlease enter the coordinates of where you would like to begin your ship.\n\n");
                    Main.printBoard(grid,0);
                    System.out.print("\n\n(");
                    xCoord= getCoord(a,b,c,grid);
                    if(xCoord!=-1){
                        gotXCoord=true;
                    }
                }

                while(!gotYCoord){
                    Main.space();
                    System.out.print("\tShips left to place "+a+"\n\n\n\n"+"Please Place Your Next Ship\n\nYour "+b+" ship's length is "+c+". \n\nPlease enter the coordinates of where you would like to begin your ship.\n\n");
                    Main.printBoard(grid,0);
                    System.out.print("\n\n("+xCoord+",");
                    yCoord= getCoord(a,b,c,grid);
                    if(yCoord!=-1){
                        gotYCoord=true;
                        if(grid[1][ Main.gTOaY(yCoord) ][ Main.gTOaX(xCoord) ]==null){
                            cordsSet=true;
                        }
                    }
                }
                Main.space();
            }

            while(!gotDir){
                System.out.print("\tShips left to place "+a+"\n\n\n\n"+"Where would you like to place your next ship?\n\nYour "+b+" ship's length is "+c+". \n\nPlease enter the direction where you would like your ship to continued.\n\n");
                b4Space= grid[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)];
                grid[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)]="#";
                Main.printBoard(grid,0);
                System.out.print("\n\n("+xCoord+","+yCoord+")\n");
                coordInput= shipDirection(a,b,c,grid,xCoord,yCoord);
                if(!coordInput.equals("notValid")){
                    gotDir=true;
                }
                else{
                    Main.space();
                    System.out.print("That was not a valid direction.\n");
                }
                if(coordInput.equals("Pick a New Location")){
                    done=false;
                }
            }
            if(!done){
                grid[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)]=b4Space;
                gotXCoord=false;
                gotYCoord=false;
                gotDir=false;
                cordsSet=false;
                xCoord=0;
                yCoord=0;
                dirY=0;
                dirX=0;
                lastX=0;
                lastY=0;
                coordInput="";
            }
        }
        if(coordInput.equals("UP")){
            dirY=1;
        }
        else if(coordInput.equals("DOWN")){
            dirY=-1;
        }
        else{
            dirY=0;
        }

        if(coordInput.equals("LEFT")){
            dirX=-1;
        }
        else if(coordInput.equals("RIGHT")){
            dirX=1;
        }
        else{
            dirX=0;
        }

        String charShip="";
        String shipIcon="";

        if (b.equals("first")){
            charShip="a";
            shipIcon=" \u2693 ";
        }
        else if(b.equals("second")){
            charShip="b";
            shipIcon=" \u26F5 ";
        }
        else if(b.equals("third")){
            charShip="c";
            shipIcon=" \uD83D\uDEA4 ";
        }
        else if(b.equals("fourth")){
            charShip="s";
            shipIcon=" \uD83D\uDEF6 ";
        }
        else if(b.equals("last")){
            charShip="d";
            shipIcon=" \uD83D\uDEE5 ";
        }
       //Array Changes
        lastY=yCoord+((c-1)*dirY);
        lastX=xCoord+((c-1)*dirX);

        if(lastX==xCoord){
            if (yCoord<lastY){
                for(int i=yCoord;i<=lastY;i++){
                    grid[0][Main.gTOaY(i)][Main.gTOaX(xCoord)]=shipIcon;
                    grid[1][Main.gTOaY(i)][Main.gTOaX(xCoord)]=charShip;
                }
            }
            else{
                for(int i=yCoord;i>=lastY;i--){
                    grid[0][Main.gTOaY(i)][Main.gTOaX(xCoord)]=shipIcon;
                    grid[1][Main.gTOaY(i)][Main.gTOaX(xCoord)]=charShip;
                }
            }
        }
        else{
            if (xCoord<lastX){
                for(int i=xCoord;i<=lastX;i++){
                    grid[0][Main.gTOaY(yCoord)][Main.gTOaX(i)]=shipIcon;
                    grid[1][Main.gTOaY(yCoord)][Main.gTOaX(i)]=charShip;
                }
            }
            else{
                for(int i=xCoord;i>=lastX;i--){
                    grid[0][Main.gTOaY(yCoord)][Main.gTOaX(i)]=shipIcon;
                    grid[1][Main.gTOaY(yCoord)][Main.gTOaX(i)]=charShip;
                }
            }
        }
    }
    public static String shipDirection(String a,String b,int c, String[][][] grid,int x,int y){
        int dirX=0;
        int dirY=0;
        String direction="";
        Scanner coordInput = new Scanner(System.in);
        System.out.print("If you want to start over, type CANCEL. If not, please type: UP, DOWN, LEFT, or RIGHT.");
        String stringCoordDir = coordInput.nextLine();
        stringCoordDir=stringCoordDir.toUpperCase();
        try{
            if(stringCoordDir.equals("UP")){
                dirY=1;
                for(int i= y; i <=y+(dirY*(c-1)); i++){
                    if(grid[1][Main.gTOaY(i)][Main.gTOaX(x)]!=null){
                        throw new Exception();
                    }
                }
            }
            else if(stringCoordDir.equals("DOWN")){
                dirY=-1;
                for (int i= y; i >=y+(dirY*(c-1)); i--){
                    if(grid[1][Main.gTOaY(i)][Main.gTOaX(x)]!=null){
                        throw new Exception();
                    }
                }
            }
            else if(stringCoordDir.equals("LEFT")){
                dirX=-1;
                for (int i=Main.gTOaX(x);i>=Main.gTOaX(x+(dirX*(c-1)));i--){
                    if(grid[1][Main.gTOaY(y)][i]!=null){
                        throw new Exception();
                    }
                }
            }
            else if(stringCoordDir.equals("RIGHT")){
                dirX=1;
                for (int i=Main.gTOaX(x);i<=Main.gTOaX(x+(dirX*(c-1)));i++){
                    if(grid[1][Main.gTOaY(y)][i]!=null){
                        throw new Exception();
                    }
                }
            }
            else if(stringCoordDir.equals("CANCEL")){
                return "Pick A New Location";
            }
            else{
                throw new Exception();
            }

            String b5Val=grid[0][ Main.gTOaY(y+(dirY*(c-1))) ][ Main.gTOaX(x+(dirX*(c-1))) ];

            direction=stringCoordDir;
            return direction;
        }
        catch(Exception e){
            return "notValid";
        }
    }

    public static int getCoord(String a,String b,int c, String[][][] grid){
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
            System.out.print("That was not a valid number.\n");
        }
        return -1;
    }
}


