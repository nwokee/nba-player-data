import java.util.ArrayList;
import java.util.HashMap;


public class Player {
    private final TeamCodes tc = new TeamCodes();
    private String fName;
    private String lName;
    private ArrayList<String> positions;
    private ArrayList<HashMap<String, Double>> stats;
    private ArrayList<Integer> years;
    private ArrayList<String> teams;

    public Player(String fName, String lName, String position) {
        this.fName = fName;
        this.lName = lName;
        positions = new ArrayList<String>();
        positions.add(position);
        stats = new ArrayList<HashMap<String, Double>>();
        stats.add(new HashMap<String, Double>());
        stats.get(0).put("PLACEHOLDER", 0.0);
        years = new ArrayList<Integer>();
        years.add(0);
        teams = new ArrayList<String>();
        teams.add("PLACEHOLDER");

    }


    public void addYear(int y, String t, HashMap<String,Double> s) {
        years.add(y);
        teams.add(t);
        stats.add(s);
    }

    public String getName(boolean f){
        if (lName.equals(" "))
            f = true;

        return f ? fName : lName;
    }

    public String getFullName(){
        return fName + " " + lName;
    }

    public String getInitial(boolean f){
        if (lName.equals(" "))
            f = true;

        return f ? fName.substring(0,1)
                : lName.substring(0,1);
    }

    public String getPosition() {
        return positions.get(positions.size()-1);
    }

    public int getAge(int y) {
        return (int) getStat(y, "AGE");
    }

    public double getStat(int y, String st){
        if(!played(y))
            return -111.0101;
        else if(!stats.get(years.indexOf(y)).containsKey(st))
            return -222.0202;
        else{
            return stats.get(years.indexOf(y)).get(st);
        }

    }

    public double getStatPeriod(int sy, int ey, String st){
        if(ey < sy){
            int temp = ey;
            ey = sy;
            sy = temp;
        }

        double stt = -1.0;

        for (int y = sy; y <= ey; y++){
            if(played(y)){
                if(stt == -1.0)
                    stt = 0.0;
                stt += getStat(y, st);
            }
        }

        return stt;

    }

    public double getPerGamePeriod(int sy, int ey, String st){
        return getStatPeriod(sy,ey,st) / getStatPeriod(sy,ey, "G");
    }

    public double getPerGame(int y, String st) {
        if (!played(y))
            return -111.0101;
        else if (!stats.get(years.indexOf(y)).containsKey(st))
            return -222.0202;
        else {
            return getStat(y, st) / getStat(y, "G");
        }
    }

    public boolean played(int y){
        return years.contains(y);
    }

    public ArrayList<String> getTeams(int y){
        ArrayList<String> tor = new ArrayList<String>();
        if(!played(y))
            return tor;
        String ret = teams.get(years.indexOf(y));
        while (ret.length() > 3) {
            tor.add(ret.substring(0,3));
            ret = ret.substring(3);
        }
        tor.add(ret);
        return tor;

    }

    public String getTeam(int y) {
        return !played(y) ? "PLACEHOLDER" : teams.get(years.indexOf(y)).substring(0,3);

    }

    public String getTeamName(int y)
    {
        String t = getTeam(y);
        return tc.team(t);
    }

    public int getRookieYear(){
        if(years.size() > 1)
            return years.get(1);
        else
            return -1;

    }

    public int getLastYear(){
        if(years.size() > 1)
            return years.get(years.size()-1);
        else
            return -1;

    }

    public ArrayList<Integer> playedPosition(String p){
        ArrayList<Integer> tor = new ArrayList<Integer>();
        for(int i = 0; i < positions.size(); i++){
            if(positions.get(i).contains(p))
                tor.add(years.get(i));
        }
        return tor;
    }



    

}