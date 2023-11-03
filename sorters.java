import java.util.function.Function;
/**
 * NAME: sorters
 * <p>
 * Purpose: sorting methods to sort passed arrays
 */
public class sorters {

    /**
     * NAME: sorter
     * <p>
     * PURPORSE: sorts the passes Team array using the given getter and 
     * bool using selection sort
     * <p>
     * @param teams array of teams to be sorted
     * @param getter function pointer to get the values from the Team class to compare for sorting
     * @param orderDesc bool to determine if sorting in DESC or ASC
     * @return the sorted team array 'teams'
     * 
     * @see 
     * ASSERTIONS: PRE: function pointer has been initalised and the team array filled and 
     *             order decided
     *             <p>
     *             POST: array should be sorted correctly
     *             <p>
     *             REMARKS: SOURCE adapted from DSA 2023 Sem 1 Lecture 1
     */
    public static Team[] sorter(Team[] teams, Function<Team, Integer> getter, boolean orderDesc) {
        for (int i = 0; i < teams.length - 1; i++) 
        {

            int min_idx = i;
            for (int j = i + 1; j < teams.length; j++) 
            {
                Team team1 = teams[j];
                Team team2 = teams[min_idx];
                if ((orderDesc && getter.apply(team1) > getter.apply(team2))||(!orderDesc && getter.apply(team1) < getter.apply(team2))) 
                {
                    min_idx = j;
                }
            }

            Team tempTeam = teams[min_idx];
            teams[min_idx] = teams[i];
            teams[i] = tempTeam;
        }
        return teams;
    }

    /**
     * NAME: groupSorter
     * <p>
     * PURPOSE: takes the char array of groups and sorts them
     * 
     * @param groups char array of groups
     * @return the sorted group array
     * @see
     * ASSERTIONS: <p>PRE: user has selected to analysis a group
     * <p>
     * POST: group array is sorted correctly
     * <p>
     * REMARKS: N/A 
     */
    public static char[] groupSorter(char[] groups) {
        for(int i =0; i <groups.length-1;i++){
            int min_idx =i;
            for(int j =i+1;j<groups.length;j++){
                if(groups[j]<groups[min_idx]){
                    min_idx = j;
                }
            }

            char tempChar = groups[min_idx];
            groups[min_idx] = groups[i];
            groups[i] = tempChar;
        }

        return groups;
    }


}
