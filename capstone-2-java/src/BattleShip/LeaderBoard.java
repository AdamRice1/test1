package BattleShip;

import java.util.ArrayList;

public abstract class LeaderBoard {
    private ArrayList leaders;

    public ArrayList recordLeaders(String name, int i){
        leaders.add(name);
        leaders.add(i);
        return leaders;
    }
    public ArrayList getLeaders() {
        System.out.println(leaders);
        return leaders;
    }
}

