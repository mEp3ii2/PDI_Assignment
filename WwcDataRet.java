import java.util.*;
import java.util.function.Function;
import java.io.*;
//import java.io.*;

/**
 * NAME: WwcDataRet
 * <p>
 * PURPOSE: retrieving the team data and displaying the 
 * results in various way based on the selected metric by the user
 * 
 */
public class WwcDataRet {

    /**
     * NAME: main
     * <p>
     * PURPOSE: validate command line arg before loading file and starting application
     * @param args - file name from command line
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Please provide a file name through the command line");
            System.out.println("Such as 'java WwcDataRet <filename.txt>'");
        }else if(args.length>1){
            System.out.println("Too many command line arguments passed!!!!");
        }else{
            String fileName = args[0];
            File f = new File(fileName);
            if(f.exists() && f.length()>0){
                Team[] teams = fileReader.loadFile(f);
                dataAnalysisMenu(teams);
            }
        }
        
    }

    /**
     * NAME: dataAnalysisMenu
     * <p>
     * PURPOSE: prints a menu to the screen and prompts the user for an input
     * input is then used to determine if the analysis if for a
     * selected group or for the whole competition 
     * <p>
     * 
     * @param teams array of teams
     * @see
     *      ASSERTIONS: PRE: csv exists and the teams have been read in
     *      POST: N/A
     * 
     */
    public static void dataAnalysisMenu(Team[] teams) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {

            System.out.println();
            int groupSel = inputValidator.menuIntEntry("Group or competition Analysis " +
                    "Enter '1' for group " +
                    "Enter '2' for competition Analysis "+
                    "Enter '3' to exit", sc);

            if (groupSel == 1) {
                Team[] groupTeam;
                char[] groups = getGroups(teams);
                
                System.out.println("Groups: ");

                
                
                boolean match = false;
                char InptGroup= '\u0000'; // assign to null
                while (!match){
                    String groupsChars="";
                    for (char c : groups) {
                        if(c != '\0' && !Character.isWhitespace(c)){
                            groupsChars = groupsChars+c+"\n";
                        }
                        
                    }

                    InptGroup = inputValidator.charEntry("Please Select a group:\n"+groupsChars, sc);
                
                    for (int i = 0; i < teams.length; i++) 
                    {
                        if (InptGroup == groups[i]) 
                        {
                            match = true;
                        }
                        else if(match==false)
                        {
                            inputValidator.errorPrinter(InptGroup+ " is not a valid group");
                        }
                    }                   
                }
                

                
                    groupTeam = createGroup(teams, InptGroup);
                    dataAnalysis(groupTeam);
                
                   
                

            } else if (groupSel == 2) {
                dataAnalysis(teams);
            } else if(groupSel==3){
                run = false;
            }else {
                System.out.println("Invalid Input please try again");
            }

        }

        sc.close();
    }

    /**
     * NAME: dataAnalysis
     * <p>
     * PURPOSE: display a menu of options to sort the teams by
     *          then get a input from the user of their selected
     *          then creates the diplay message, Function method and order
     *          then calls the sorting method before passing the results to the display
     *          method
     * 
     * @param teams array of teams
     * @see
     *      ASSERTIONS: PRE user has selected group or competition and a group array has been created if necessary
     *      <p>
     *      POST: array has been sorted before being passed to the display method
     *      <p>
     *      REMARKS N/A
     */
    public static void dataAnalysis(Team[] teams) {

        Scanner sc = new Scanner(System.in);

        int sel = inputValidator.menuIntEntry("1. Sort the teams based on their net goals in descending order.\n" +
                "2. Sort the teams based on the total goals scored against them in descending order.\n" +
                "3. Sort the teams based on the total goals they scored in descending order.\n" +
                "4. Display the team with the highest net goals as the 'best performing team.'\n" +
                "5. Display all teams in order of their net goals.\n" +
                "Input: ", sc);

        Function<Team, Integer> getter = dataAnalysis.getFunction(sel);
        String msg = dataAnalysis.getMsg(sel);
        boolean orderDesc = true;

        if (sel == 5) {
            orderDesc = false;
        }

        teams = sorters.sorter(teams, getter, orderDesc);

        if (sel == 4) {
            displayRes(teams, msg);
        } else {
            displayRes(teams, msg, getter);
        }

    }

    /**
     * NAME: getGroups
     * <p>
     * PURPOSE: to get a list of all unique group chars
     * 
     * @param teams array of teams
     * @return
     * @see
     *      ASSERTIONS: PRE teams have been loaded
     *      <p>
     *      POST: unique groups have been found
     *      <p>
     *      REMARKS: N/A
     */
    public static char[] getGroups(Team[] teams) {
        char[] tempchars = new char[teams.length + 1];
        int uniqueCount = 0;

        for (int i = 0; i < teams.length; i++) {
            boolean match = true;
            for (int j = 0; j <= uniqueCount; j++) {
                if (teams[i].getGroup() == tempchars[j]) {
                    match = false;
                }
            }
            if (match) {
                tempchars[uniqueCount] = teams[i].getGroup();
                uniqueCount++;
            }
        }

        return tempchars;
    }

    /**
     * NAME: createGroup
     * <p>
     * PURPOSE: filters the team array so that a array of teams only from the 
     * selected group remains 
     * 
     * @param teams array of teams 
     * @param group char variable for the selected group
     * @return groupTeams a array of teams from the selected group
     * @see
     *      ASSERTIONS: PRE: group char has been inputed
     *      <p>
     *      POST: groupArray has all teams beloning to the selected group
     *      <p>
     *      REMARKS: N/A
     */
    public static Team[] createGroup(Team[] teams, char group) {

        
        int matches = 0;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getGroup() == group) {
                matches++;
            }
        }

        Team[] groupTeams = new Team[matches];

        int index = 0;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].getGroup() == group) {
                groupTeams[index] = teams[i];
                index++;
            }
        }

        return groupTeams;
    }

    /**
     * NAME: displayRes
     * <p>
     * PURPOSE: display the teams names and their metric
     * <p>
     * 
     * @param teams  array of teams
     * @param msg    string msg to be diplayed
     * @param getter function pointer to display the result of the metric used
     *               <p>
     * @see
     *      ASSERTIONS: Pre: teams have been sorted and function pointer inisalised
     *      <p>
     *      Post: results will be displayed to the terminal in the
     *      sorted order
     *      <p>
     *      REMARKS: N/A
     */
    public static void displayRes(Team[] teams, String msg, Function<Team, Integer> getter) {
        int displayValue;
        System.out.println(msg);

        for (int i = 0; i < teams.length; i++) {
            Team team = teams[i];
            String Name = team.getName();
            displayValue = getter.apply(team);
            System.out.println("Team Name: " + Name + "\n" + displayValue);
        }
    }

    /**
     * NAME: displayRes
     * <p>
     * PURPOSE: display the teams names and their metric, used for displaying the best team
     * <p>
     * 
     * @param teams  array of teams
     * @param msg    string msg to be diplayed
     * @see
     *      ASSERTIONS: Pre: teams have been sorted
     *      <p>
     *      Post: results will be displayed to the terminal in the
     *      sorted order
     *      <p>
     *      REMARKS: N/A
     */
    public static void displayRes(Team[] teams, String msg){
        
        int maxGoals = teams[0].getNetGoals();
        int ties = 0;

        for(int i =0; i < teams.length; i++){
            if(teams[i].getNetGoals()==maxGoals){
                ties++;
            }
        }

        
        Team[] bestTeams = new Team[ties];
        bestTeams[0]= teams[0];

        if(ties>1){
            for(int i = 1;i<ties;i++){
                bestTeams[i]=teams[i];
            }
        }
        

        System.out.println("Best Performing Team/s");
        for (Team team : bestTeams) {
            System.out.println(team.getName());
            System.out.println(team.getNetGoals()+" Net Goals");
        }
    }
}
