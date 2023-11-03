/**
 * NAME: TEAM
 * <p>
 * PURPOSE: womens world cup team class
 */
public class Team {
    
    private String name;
    private String code;
    private int goalS;
    private int goalA;
    private char group;
    private int netGoals;
   
    /**
     * NAME: Team
     * <p>
     * PURPOSE: constructor that takes parameters for making team objects of the different soccer teams
     * 
     * @param tName name of the team
     * @param tCode teams code
     * @param tGoalS goals scored by the team
     * @param tGoalA goals scored against the team
     * @param tGroup teams group
     * 
     * @see
     * ASSERTIONS: PRE: All passed values are valid values 
     * <p>
     * POST: a valid team object is created;
     */
    public Team(String tName, String tCode, int tGoalS,int tGoalA,char tGroup)
    {
        
        name = tName;
        code = tCode;
        goalS = tGoalS;
        goalA = tGoalA;    
        group = tGroup;
        netGoals = goalS - goalA;
    }

    /**
     * NAME: Team
     * <p>
     * PURPOSE: copy constructor that takes a team object and creates a copy team
     * 
     * @param cteam team object that is copied
     * 
     * @see
     * ASSERTIONS: PRE: a valid team object is passed
     * <p>
     * POST: a valid team object is created;
     * <p>
     * REMARKS: not really used but added it to show that i understand the content
     */
    public Team(Team cteam)
    {
        
        name = cteam.getName();
        code = cteam.getcode();
        goalS = cteam.getGoalS();
        goalA = cteam.getGoalA();    
        group = cteam.getGroup();
        netGoals = goalS - goalA;
    }

    /**
     * NAME: Team
     * <p>
     * PURPOSE: default constructor that creates a defualt team
     * 
     * 
     * @see
     * ASSERTIONS: PRE: N/A
     * <p>
     * POST: a valid default team object is created;
     * <p>
     * REMARKS: not really used but added it to show that i understand the content
     */
    public Team()
    {
        
        name = "Unregistered Team";
        code = "NA";
        goalS = 0;
        goalA = 0;
        group = 'Z';
        netGoals = goalS - goalA;
    }

    // getters
    public String getName()
    {
        return name;
    }

    public String getcode()
    {
        return code;
    }

    public int getGoalS() 
    {
        return goalS;
    }

    public int getGoalA() 
    {
        return goalA;
    }

    public char getGroup()
    {
        return group;
    }
    
    public int getNetGoals() 
    {
        return netGoals;
    }

    //setters
    public void setName(String name) 
    {
        this.name = name;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public void setGoalS(int goalS) 
    {
        this.goalS = goalS;
    }

    public void setGoalA(int goalA) 
    {
        this.goalA = goalA;
    }

    public void setGroup(char group) 
    {
        this.group = group;
    }

    public void setNetGoals(int netGoals) 
    {
        this.netGoals = netGoals;
    }
    
    @Override
    public String toString()
    {
        String stringMsg1 = "Team Details\nName:"+getName()
                            +"\nCode: "+getcode()+"\nGoals Scored: "
                            +getGoalS()+"\nGoals Against "+getGoalA()+"\nGroup: "+getGroup()
                            +"\nNet Goals: "+getNetGoals();
        return stringMsg1;
    }
}

