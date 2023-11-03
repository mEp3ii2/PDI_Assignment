
import java.util.*;;
/**
 * NAME: inputValidator
 * <p>
 * Purpose: validates user inputs
 */
public class inputValidator 
{

    /**
     * NAME: errorPrinter
     * <p>
     * PURPOSE: display the error messages to the terminal(in red)
     * @param msg the error message to be displayed
     * @see
     * ASSERTIONS:
     * PRE: msg is not empty, ANSI escape codes are correct
     * POST: terminal is cleared and error message is displayed in red
     * REMARKS: N/A
     */
    public static void errorPrinter(String msg) {
        String clearScreen ="\u001b[H\u001b[2J";
        String redColorCode = "\u001B[31m";
        String resetColourCode = "\u001B[0m";    
        System.out.println(clearScreen+redColorCode+msg+resetColourCode);
        System.out.println(redColorCode+ "Try again\n"+ resetColourCode);
    }
    
    /**
     * NAME: stringEntry
     * <p>
     * PURPOSE: return a valid string to the caller
     * <p>
     * 
     * @param prompt string msg to be displayed to the user
     * @param sc     scanner object to read inputs
     *               </p>
     *               <p>
     * @return the valid inputed string
     *         </p>
     * @see
     *      ASSERTIONS: Pre: prompt is not null otherwise user will not have a
     *      message to read first
     *      <p>
     *      Post: return string will not contain any symbols or numbers only letters
     *      <p>
     *      REMARKS: uses some regex to make sure that the string only contains letters or spaces
     *@source FOP Lecture 6/7 i think for the regex
     * 
     */

    public static String stringEntry(String prompt, Scanner sc)
    {
        
        boolean run;
        String inputString = "";

        do 
        {
            try 
            {
                System.out.println(prompt);
                inputString = sc.nextLine();
            
                if (!inputString.matches("[ a-zA-Z]+")) 
                {
                    throw new IllegalArgumentException("Input must only letters");
                }

                run = false;
            } 
            catch (IllegalArgumentException e) 
            {
                errorPrinter("Invalid input try again: " + e);
                run = true;
            }

            catch (Exception e) 
            {
                errorPrinter("Unkown error:" + e);
                run = true;
            }

        } while (run);

        return inputString;
    }

    /**
     * NAME: intEntry
     * <p>
     * PURPOSE: return a valid int to the caller
     * <p>
     * 
     * @param prompt string msg to be displayed to the user
     * @param sc     scanner object to read inputs
     *               </p>
     *               <p>
     * @return the valid inputed int
     *         </p>
     * @see
     *      ASSERTIONS: Pre: prompt is not null otherwise user will not have a
     *      message to read first
     *      <p>
     *      Post: return int will be a positive number
     *      <p>
     *      REMARKS: N/A
     */

    public static int intEntry(String prompt, Scanner sc) 
    {
        boolean run;
        int inputInt = 0;
        do 
        {
            try 
            {
                //sc.nextLine();
                System.out.println(prompt);
                inputInt = sc.nextInt();

                if (inputInt < 0) {
                    throw new IllegalArgumentException("Input must not be negative");
                }

                run = false;

            } 
            catch (InputMismatchException e) 
            {
                sc.nextLine();//consume the invalid input
                errorPrinter("Invalid input try again: " + e + " Input must only be numbers");
                run = true;

            } 
            catch (IllegalArgumentException e) 
            {
                errorPrinter("Invalid input: " + e);
                run = true;
            } 
            catch (Exception e) 
            {
                errorPrinter("Unkown error:" + e);
                run = true;
            }

        } while (run);

        return inputInt;
    }

    /**
     * NAME: charEntry
     * <p>
     * PURPOSE: return a valid char to the caller
     * <p>
     * 
     * @param prompt string msg to be displayed to the user
     * @param sc     scanner object to read inputs
     *               </p>
     *               <p>
     * @return the valid inputed char
     *         </p>
     * @see
     *      ASSERTIONS: Pre: prompt is not null otherwise user will not have a
     *      message to read first
     *      <p>
     *      Post: return char will be only be a letter
     *      <p>
     *      REMARKS: N/A
     */

    public static char charEntry(String prompt, Scanner sc) 
    {
        boolean run;
        String testString;
        char inputChar = ' ';
        
        do 
        {
            try 
            {
                System.out.println(prompt);
                testString = sc.next();
                
                if (testString.length() != 1) 
                {
                    throw new IllegalArgumentException("Input must be exactly one character long");
                }

                inputChar = testString.charAt(0);

                if (!Character.isLetter(inputChar)) 
                {
                    throw new IllegalArgumentException("Input must be a letter");
                }

                run = false;

            } 
            
            catch (IllegalArgumentException e) 
            {
                errorPrinter("Invalid input: " + e);
                run = true;
            } 
            catch (Exception e) 
            {
                errorPrinter("Unkown error:" + e);
                run = true;
            }

        } while (run);

        return inputChar;

    }

    public static int menuIntEntry(String prompt, Scanner sc) 
    {
        
        boolean run;
        int inputInt = 0;
        
        do 
        {
            try 
            {

                System.out.println(prompt);
                inputInt = sc.nextInt();

                if (inputInt < 1 || inputInt > 5) 
                {
                    throw new IllegalArgumentException("Input not in the valid range");
                }
                run = false;

            } 
            catch (InputMismatchException e) 
            {
                sc.nextLine(); // consume the input
                errorPrinter("Invalid input try again: " + e + " Input must only be numbers");                
                run = true;

            } 
            catch (IllegalArgumentException e) 
            {
                errorPrinter("Invalid input: " + e);
                run = true;
            } 
            catch (Exception e) 
            {
                errorPrinter("Unkown error:" + e);
                run = true;
            } 
            finally 
            {
                sc.nextLine();
            }

        } while (run);

        return inputInt;
    }

}

