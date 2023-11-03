import java.util.*;
import java.io.*;

/**
 *  The WwcDataEntry class records inputed team details of the 
 *  participating womens soccer teams to a csv file
 *  
 *  @author Mitchell Pontague 19126924
 */

public class WwcDataEntry
{
    public static void main(String[] args) 
    {
        
        Scanner sc = new Scanner(System.in); // create scanner to read inputs
        String csvFileName = "data.csv"; // file name

        try
        {
        
            PrintWriter writer = new PrintWriter(csvFileName); // create print writer to write to file
            String columnNames = "Team Name,Team Code,Goals For,Goals Against,Group"; // column names for the csv fie
            writer.println(columnNames);

            // while loop that runs until the user decides to stop entering teams
            boolean run = true;
            
            while (run == true)
            {
                

                // calls to methods to get user input for the different data
                String teamName = inputValidator.stringEntry("Team Name: ", sc);
                String teamCode = inputValidator.stringEntry("Team Code: ", sc);
                int goalS = inputValidator.intEntry("Total goals scored by the Team: ",sc);
                int goalA = inputValidator.intEntry("Total goals against the Team: ",sc); 
                char group = inputValidator.charEntry("Group: ", sc);
                
                // creates entry for the csv file
                String comma = ",";
                String data = teamName+comma+teamCode+comma+Integer.toString(goalS)+comma+Integer.toString(goalA)+comma+group;

                writer.print(data);
                
                /* prompts the user for if they want to continue
                 * checks the input and continues if y/Y or exits the loop if n/N
                 */

                System.out.println("Team data entered! Would You like to enter another team: Y or N ");
                
                char cont = sc.next().charAt(0);
                cont = Character.toUpperCase(cont);
                if(cont == 'N')
                {
                    run = false;
                }
                else
                {
                    writer.print("\n");
                }

            }

            // flush streams
            writer.close();
            sc.close();
            
            System.out.println("Done");
        } 
        // catch any exceptions that might occur with i/o
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
