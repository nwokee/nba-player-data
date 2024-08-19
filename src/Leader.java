public class Leader {

    private Player p;
    private String statName;
    private double stat;
    private int year = -1;

    public Leader(Player p, String statName, double stat) {
        this.p = p;
        this.statName = statName;
        this.stat = stat;
    }

    public Leader(Player p, String statName, double stat, int year) {
        this.p = p;
        this.statName = statName;
        this.stat = stat;
        this.year = year;
    }


    public String getName(int format){

        switch (format){
            case 1:
                return p.getInitial(true) + ". " + p.getName(false);
            case 2:
                return p.getName(true) + " " + p.getInitial(false) + ".";
            case 3:
                return p.getInitial(true) + ". " + p.getInitial(false) + ".";
            default:
                return p.getFullName();
        }
    }

    public String getStatName(){
        return statName;
    }

    public double getStat(){
        return stat;
    }

    public String toString(){
        String base = getName(1) + ": " + ((int)(getStat() * 100) / 100.0);
        return year == -1 ? base : "(" + year + ") " + base;
    }



}
