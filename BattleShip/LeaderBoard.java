package BattleShip;

import java.util.ArrayList;

public abstract class LeaderBoard {
    public ArrayList leaders;

    public ArrayList recordLeaders(String name, int i){
        leaders.add(name);
        leaders.add(i);
        return leaders;
    }
}

