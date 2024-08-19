import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Main {


    private static HashMap<String, Player> db = new HashMap<String, Player>();;
    private static ArrayList<String> players = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        // create hashmap of players (key = player name, value = player object)

        // loop through every file
        for (int year = 1982; year < 2025; year++) {
            //System.out.println(year);

            //      GET FILES FOR THE YEAR
            String totals = "C:/Users/ebube/OneDrive/Desktop/coding/mini projects/nbaplayerdata/PlayerDataProject/player totals/nba_stats_players_";
            totals += year + ".csv";
            String advanced = "C:/Users/ebube/OneDrive/Desktop/coding/mini projects/nbaplayerdata/PlayerDataProject/player advanced/nba_advanced_players_";
            advanced += year + ".csv";
            BufferedReader tbr = new BufferedReader(new FileReader(totals));
            BufferedReader abr = new BufferedReader(new FileReader(advanced));
            String tline = "";
            String aline = "";
            String splitBy = ",";
            String[] tlabels;
            String[] alabels;
            ArrayList<String> allKeys = new ArrayList<String>();

            // GET LABELS OF FILE
            if (((tline = tbr.readLine()) != null) && ((aline = abr.readLine()) != null)) {
                tlabels = tline.split(splitBy);
                alabels = aline.split(splitBy);
                for (int i = 0; i < tlabels.length; i++)
                    allKeys.add(tlabels[i].toUpperCase());
                for (int i = 6; i < alabels.length; i++)
                    if ((i != 18) && (i != 23))
                        allKeys.add(alabels[i].toUpperCase());

            }

            // LOOP THROUGH EVERY PLAYER
            while (((tline = tbr.readLine()) != null) && ((aline = abr.readLine()) != null)) {
                String[] tstats = tline.split(splitBy);
                String[] astats = aline.split(splitBy);
                ArrayList<String> allStats = new ArrayList<String>();
                // combine stats
                for (int i = 0; i < tstats.length; i++)
                    allStats.add(tstats[i]);
                for (int i = 6; i < astats.length; i++)
                    if ((i != 18) && (i != 23))
                        allStats.add(astats[i]);
                // get name
                String fName = "";
                String lName = " ";
                String name = allStats.get(0);
                if(name.contains("*"))
                    name = name.substring(0,name.length()-1);

                if (name.contains(" ")) {
                    int splt = allStats.get(0).lastIndexOf(" ");
                    fName = name.substring(0, splt);
                    lName = name.substring(splt);
                    lName = lName.substring(1);

                }
                else {
                    fName = name;
                    }

                // get position and team
                String position = "";
                if (allStats.get(1).contains("C"))
                    position = "Big";
                else if (allStats.get(1).contains("F") || !allStats.get(1).contains("PG"))
                    position = "Wing";
                else
                    position = "Guard";

                String team = allStats.get(3);


                // compile stats
                HashMap<String, Double> sts = new HashMap<String, Double>();
                for (int x = 2; x < allStats.size(); x++) {
                    if (x != 3) {
                        if (allStats.get(x).equals(""))
                            allStats.set(x, "0.0");
                        sts.put(allKeys.get(x), Double.parseDouble(allStats.get(x)));
                    }
                }

                // System.out.println(name + ": " + position + " for " + team);
                // System.out.println(sts.toString());
                // add player if new
                if (!db.containsKey(name.toUpperCase())) {
                    Player p = new Player(fName, lName, position);
                    p.addYear(year, team, sts);
                    db.put(name.toUpperCase(), p);
                    players.add(name);
                } else {
                    Player s = db.get(name.toUpperCase());
                    s.addYear(year, team, sts);
                    Player replaced = db.replace(allStats.get(0).toUpperCase(), s);
                }
            }

            // while true call a method to give user options to ask questions
            //System.out.println(db.toString());

            System.out.println(playerDesc("Lebron James", year));
            //System.out.println(Arrays.toString(playerStats("Lebron James", year)));
            // System.out.println(playerDesc("Stephen Curry", year));
            // System.out.println(playerDesc("Kevin Durant", year));
            //System.out.println(playerDesc("Michael Jordan", year));

            //System.out.println("________________");


        }

        //System.out.println("Lebron James: " + Arrays.toString(careerStats("Lebron James")));
        //System.out.println("Stephen Curry: " + Arrays.toString(careerStats("Stephen Curry")));
        //System.out.println("James Harden: " + Arrays.toString(careerStats("James Harden")));
        //System.out.println("Michael Jordan: " + Arrays.toString(careerStats("Michael Jordan")));
        //System.out.println("Kobe Bryant: " + Arrays.toString(careerStats("Kobe Bryant")));
        //System.out.printf("Player Name\t\t\t\t PPG\t\tAPG\t\tRPG\t\tSPG\t\tBPG");
        //System.out.println("\n----------------------------------");
        System.out.println("normal");
        for (Leader l : leagueLeaders(2024,"PTS", true, 10))
            System.out.println(l.toString());
        System.out.println("\n----------------------------------");
        /*System.out.println("ALL TIME CAREER PPG LEADERS");
        for (Leader l : allTimeLeaders("PTS", true, 10))
            System.out.println(l.toString());
        System.out.println("\n----------------------------------");
        */
        /*System.out.println("ALL TIME CAREER PPG LEADERS BY SEASON");
        for (Leader l : seasonalRecords("PTS", true, 50))
            System.out.println(l.toString());*/
    }
    //  methods to make

    // player description
    public static String playerDesc(String name, int y) {
        // get player object from hashmap\
        if(!db.containsKey(name.toUpperCase()))
            return name.toUpperCase() + " NOT IN DATABASE";

        Player p = db.get(name.toUpperCase());

        // return players name, age, position, and team
        String desc = "In the " + y + " season, " + p.getFullName() + " was a " + p.getAge(y) + " year old " + p.getPosition().toLowerCase() + " for "
                + p.getTeam(y);
        return p.played(y) ? desc : name.toUpperCase() + " DID NOT PLAY BASKETBALL DURING THE " + y + " SEASON";
    }

    //  get player season stats
    public static double[] playerStats(String name, int y) {
        // get player object from hashmap
        if(!db.containsKey(name.toUpperCase())) {
            return new double[]{-1.0, -1.0, -1.0, -1.0, -1.0};
        }

        Player p = db.get(name.toUpperCase());

        // get players stats for the year
        double ppg = (int)(p.getPerGame(y, "PTS") * 100) / 100.0;
        double apg = (int)(p.getPerGame(y, "AST") * 100) / 100.0;
        double rpg = (int)(p.getPerGame(y, "TRB") * 100) / 100.0;
        double spg = (int)(p.getPerGame(y, "STL") * 100) / 100.0;
        double bpg = (int)(p.getPerGame(y, "BLK") * 100) / 100.0;

        double[] toreturn = {ppg, apg, rpg, spg, bpg};
        return toreturn;

    }

    //  get player career stats
    public static double[] careerStats(String name){
        // get player object from hashmap
        if(!db.containsKey(name.toUpperCase())) {
            return new double[]{-1.0, -1.0, -1.0, -1.0, -1.0};
        }

        Player p = db.get(name.toUpperCase());
        int s = p.getRookieYear();
        int e = p.getLastYear();

        return statsOverPeriod(name, s,e);
    }
    //  get player stats over a certain time period
    public static double[] statsOverPeriod(String name, int s, int e){
        // get player object from hashmap
        if(!db.containsKey(name.toUpperCase())) {
            return new double[]{-1.0, -1.0, -1.0, -1.0, -1.0};
        }

        Player p = db.get(name.toUpperCase());

        // get players stats for the time period
        double ppg = (int)(p.getPerGamePeriod(s,e, "PTS") * 100) / 100.0;
        double apg = (int)(p.getPerGamePeriod(s,e, "AST") * 100) / 100.0;
        double rpg = (int)(p.getPerGamePeriod(s,e, "TRB") * 100) / 100.0;
        double spg = (int)(p.getPerGamePeriod(s,e, "STL") * 100) / 100.0;
        double bpg = (int)(p.getPerGamePeriod(s,e, "BLK") * 100) / 100.0;

        double[] toreturn = {ppg, apg, rpg, spg, bpg};
        return toreturn;
    }

    //  stat league leaders from a season
    public static Leader[] leagueLeaders(int year, String stat, boolean perGame, int num){
        ArrayList<Leader> allEligible = new ArrayList<Leader>();

        for (String pl : players){
            Player p = db.get(pl.toUpperCase());
            if(p.played(year)){
                double stu = perGame ? (p.getPerGame(year,stat)) : (p.getStat(year, stat));
                allEligible.add(new Leader(p, stat, stu));
            }
        }

        allEligible = sort(allEligible);
        Leader[] toreturn = new Leader[num];
        for(int i = 0; i < num; i++)
            toreturn[i] = allEligible.get(i);

        return toreturn;
    }


    //  all time leaders
    public static Leader[] allTimeLeaders(String stat, boolean perGame, int num){
        ArrayList<Leader> allP = new ArrayList<Leader>();
        for (String pl : players) {
            Player p = db.get(pl.toUpperCase());
            double stu = perGame ? (p.getPerGamePeriod(p.getRookieYear(),p.getLastYear(),stat)) :
                    (p.getStatPeriod(p.getRookieYear(),p.getLastYear(), stat));
            allP.add(new Leader(p, stat, stu));
        }

        allP = sort(allP);
        Leader[] toreturn = new Leader[num];
        for(int i = 0; i < num; i++)
            toreturn[i] = allP.get(i);

        return toreturn;
    }

    // all time season highs
    public static Leader[] seasonalRecords(String stat, boolean perGame, int num){
        ArrayList<Leader> allP = new ArrayList<Leader>();
        for (String pl : players) {
            Player p = db.get(pl.toUpperCase());
            for (int year = p.getRookieYear(); year <= p.getLastYear(); year++){
                if(p.played(year)){
                    double stu = perGame ? (p.getPerGame(year,stat)) : (p.getStat(year, stat));
                    allP.add(new Leader(p, stat, stu, year));
                }
            }
        }

        allP = sort(allP);
        Leader[] toreturn = new Leader[num];
        for(int i = 0; i < num; i++)
            toreturn[i] = allP.get(i);

        return toreturn;
    }



    //  stat league leaders by position (adjust position classes)
    //  all time leaders by position
    //  team leaders by time period


    public static ArrayList<Leader> sort (ArrayList<Leader> list) {
        if (list.size() < 2) {
            return list;
        }

        int pivotIndex = list.size() / 2;
        Leader pivot = list.get(pivotIndex);
        ArrayList<Leader> less = new ArrayList<>();
        ArrayList<Leader> greater = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i == pivotIndex) continue;
            if (list.get(i).getStat() >= pivot.getStat()) {
                greater.add(list.get(i));
            } else {
                less.add(list.get(i));
            }
        }

        ArrayList<Leader> sorted = new ArrayList<>();
        sorted.addAll(sort(greater));
        sorted.add(pivot);
        sorted.addAll(sort(less));

        return sorted;


    }
}