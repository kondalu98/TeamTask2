import java.util.*;
import java.util.stream.Collectors;

//kondal
public class one
{
    public static void main(String[] args) throws Exception {
        ArrayList<Names> teams=new ArrayList<>();
        teams.add(new Names("India"));
        teams.add(new Names("England"));
        teams.add(new Names("srilanka"));
        teams.add(new Names("Pakistan"));
        teams.add(new Names("Newzealand"));
        List<Match> matches=Scheduler.createSchedule(teams);
        System.out.println(matches);
        Simulator.plays(matches);
        Simulator.table(teams,matches);
    }
}
class Match
{
    private Names t1;
    private Names t2;
    private Names Wining;
    private Names losing;
    Match(Names team,Names team3)
    {
        this.t1=team;
        this.t2=team3;
    }
    public Names getT1()
    {
        return t1;
    }
    public Names getT2()
    {
        return t2;
    }
    public Names getWining()
    {
        return Wining;
    }
    public void setWining(Names winner)
    {
        this.Wining=winner;
    }
    public Names getLosing()
    {
        return losing;
    }
    public void setLosing(Names loser)
    {
        this.losing=loser;
    }
    @Override
    public String toString()
    {
        String matchDescription = t1 +" vs "+t2;
        if(Wining!=null)
        {
            String result="\n winner="+this.Wining.toString()+"Loser="+this.losing.toString();
            matchDescription+=result;
        }
        return matchDescription;
    }
}
class Scheduler
{
    public static List<Match> createSchedule(List<Names> teams)
    {
        List<Match> matches=new ArrayList<>();
        for(int i=0;i<teams.size();i++)
        {
            for(int j=i+1;j<teams.size();j++)
            {
                Match match=new Match(teams.get(i), teams.get(j));
                matches.add(match);
            }
        }
        return matches;
    }
}
class Simulator
{
    /**
     * @param matches
     */
    public static void plays(List<Match> matches)
    {
        for(Match match:matches)
        {
            int random=(int)((Math.random()*10)+1);
            if(random%2==0)
            {
                match.setWining(match.getT1());
                match.setLosing(match.getT2());
            }
            else
            {
                match.setWining(match.getT2());
                match.setLosing(match.getT1());
            }
        }
    }
    public static void table(List<Names> teams,List<Match> matches)
    {
        for(Names team:teams)
        {
            int wonGames=matches.stream().filter(m ->m.getWining().equals(team)).collect(Collectors.toList()).size();
            int lostGames=matches.stream().filter(m ->m.getLosing().equals(team)).collect(Collectors.toList()).size();
            System.out.println(team);
            System.out.println("Winning  "+ wonGames+"\t");
            System.out.println("Lossing  "+lostGames+"\t");
            System.out.println("################");
    
        }
    }
}
class Names
{
    private String name;
    Names(String name)
    {
        this.name=name;
    }
    @Override
    public String toString()
    {
        return name;
    }
    public boolean equals(Object obj)
    {
        return this.name.equals(((Names)obj).name);
    }
}
