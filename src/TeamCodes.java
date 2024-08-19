import java.util.HashMap;

public class TeamCodes {

    private HashMap<String,String> teams;

    public TeamCodes(){
        teams = new HashMap<String,String>();

        teams.put("ATL","Atlanta Hawks");
        teams.put("BKN","Brooklyn Nets");
        teams.put("BOS","Boston Celtics");
        teams.put("CHA","Charlotte Hornets");
        teams.put("CHI","Chicago Bulls");
        teams.put("CLE","Cleveland Cavaliers");
        teams.put("DAL","Dallas Mavericks");
        teams.put("DEN","Denver Nuggets");
        teams.put("DET","Detroit Pistons");
        teams.put("GSW","Golden State Warriors");
        teams.put("HOU","Houston Rockets");
        teams.put("IND","Indiana Pacers");
        teams.put("LAC","Los Angeles Clippers");
        teams.put("LAL","Los Angeles Lakers");
        teams.put("MEM","Memphis Grizzlies");
        teams.put("MIA","Miami Heat");
        teams.put("MIL","Milwaukee Bucks");
        teams.put("MIN","Minnesota Timberwolves");
        teams.put("NOP","New Orleans Pelicans");
        teams.put("NYK","New York Knicks");
        teams.put("OKC","Oklahoma City Thunder");
        teams.put("ORL","Orlando Magic");
        teams.put("PHI","Philadelphia 76ers");
        teams.put("PHO","Phoenix Suns");
        teams.put("POR","Portland Trailblazers");
        teams.put("SAC","Sacramento Kings");
        teams.put("SAS","San Antonio Spurs");
        teams.put("TOR","Toronto Raptors");
        teams.put("UTA","Utah Jazz");
        teams.put("WAS","Washington Wizards");


        teams.put("KCK","Kansas City Knicks");
        teams.put("NJN","New Jersey Nets");
        teams.put("NOH","New Orleans Hornets");
        teams.put("SDC","San Diego Clippers");
        teams.put("SEA","Seattle Supersonics");
        teams.put("VAN","Vancouver Grizzlies");
        teams.put("WSB","Washington Bullets");

    }

    public String team(String code){
        return teams.containsKey(code) ? teams.get(code) : "N/A";
    }
}
