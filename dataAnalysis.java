

import java.util.function.Function;

/**
 * NAME: dataAnalysis
 * <p>
 * PURPOSE: creates function methods(function pointers)
 * and display strings
 */
public class dataAnalysis {
    /**
     * NAME: getFunction
     * <p>
     * PURPOSE: to get the desired getter method from the Team class
     * 
     * @param selection int variable from the users selection
     * @return A Function method
     * 
     *@see
     *ASSERTIONS: PRE: users selection is a valid int between 1-5
     *            POST: Function method is chosen and passed back to the caller
     *            REMARKS: In doing ucp function pointers have been pretty cool
     *            and I thought Java should have something similiar which can then be used
     *            to make it so that i dont need a different sorting method for each getter
     *            that we're going to use so after a bit of research ive got this
     *@sources https://www.geeksforgeeks.org/function-interface-in-java-with-examples/ <p>
     *          https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html <p>
     *          https://mkyong.com/java8/java-8-function-examples/ 
     */
    public static Function<Team,Integer> getFunction(int selection) {
        
        Function<Team,Integer> getter;                
        
        switch (selection) 
        {
            /* Goals Scored Against Desc */
            case 2:
                getter = Team::getGoalA;
                break;

            /* Goals Scoared Desc */
            case 3:
                getter = Team::getGoalS;
                break;

            /* Net goals*/
            case 1:
            case 4:
            case 5:
                getter = Team::getNetGoals;
                break;
            /* If not matching default to netgoals*/                
            default:
                getter = Team::getNetGoals;
                break;
        } 

        return getter;
    }

    /**
     * NAME: getMsg
     * <p>
     * PURPOSE: get the message string to be displayed using the selected user input
     * @param selection int variable from the user input 
     * @return a string message of what data is being displayed
     * @see
     * ASSERTIONS:
     * <p>
     * PRE: users selection is a valid int between 1-5
     * <p>
     * POST: string is selected and passed back to the called
     * <p>
     * REMARKS: N/A
     */
    public static String getMsg(int selection){
        String msg = "" ;
        switch(selection)
        {
            case 1:
                msg = "Net goals in descending order: ";
                break;
            case 2:
                msg ="Total goals scored against them in descending order: ";
                break;
            case 3:
                msg ="Total goals Scored in descending order: ";
                break;
            case 4:
                msg ="Best Performing Team by Net Goals: ";
                break;
            case 5:
                msg ="Net goals in acending order";
                break;
            default:
                msg ="Error";
                break;
        }

        return msg;
    }
}
