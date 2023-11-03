import java.util.*;
import java.io.*;

/**
 * NAME:fileReader
 * <p>
 * PURPOSE: reads in the csv file to create the array of teams
 * @see
 * REMARKS: csv is set to testdata.csv but the output csv from section 1
 *          is data.csv
 */
public class fileReader {
    
    /**
     * NAME: loafFiles
     * <p>
     * PURPOSE: to read the csv file and creat an array of teams
     * @param f - file object from passed command line arg
     * @return the created team array
     * ASSERTIONS: PRE: csv file exists and is not empty
     *            POST: team array is loaded and passed back
     *            REMARKS: will still work if csv is empty will pass back an empty array
     *                     
     */
    public static Team[] loadFile(File f){

        String tName, tCode;
        int tGoalS, tGoalA;
        char group;

        int lines = getLineCount() - 1;
        Team[] teams = new Team[lines];

        try {

           
            Scanner sc = new Scanner(f);

            sc.nextLine();

            for (int i = 0; i < lines; i++) 
            {

                String line = sc.nextLine();
                String[] parts = line.split(",");

                tName = parts[0];
                tCode = parts[1];
                tGoalS = Integer.parseInt(parts[2]);
                tGoalA = Integer.parseInt(parts[3]);
                group = parts[4].charAt(0);

                teams[i] = new Team(tName, tCode, tGoalS, tGoalA, group);

            }
            
            sc.close();

        } 

        catch (IOException e) 
        {
            System.out.println("File I/O error");
        }

        return teams;
    }
    /**
     * NAME: getLineCount
     * <p>
     * PURPOSE: to get the line count of the csv file so the application can determine the size of the team array
     * 
     * @return int lines
     * @see
     * ASSERTIONS: PRE: file exists
     *            POST: lines is a valid int at 0 or greater
     *            REMARKS: N/A
     */
    public static int getLineCount() 
    {
        
        int lines = 0;
        
        try {
        
            File file = new File("testdata.csv");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) 
            {
                sc.nextLine();
                lines++;
            }

            
            sc.close();
        } 
        catch (IOException e) 
        {
            System.out.println("IOE error");
        }
        return lines;
    }
}