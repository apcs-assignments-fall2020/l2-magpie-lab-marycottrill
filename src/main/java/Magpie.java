/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        int youfind2 = findWord(statement, "you");
        int ifind = findWord(statement, "I");
        String response = "";
        if (statement.indexOf("no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (statement.indexOf("mother") >= 0
                || statement.indexOf("father") >= 0
                || statement.indexOf("sister") >= 0
                || statement.indexOf("brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (statement.indexOf("dog") >= 0 || statement.indexOf("cat") >= 0){
            response = "Tell me more about your pets.";
        }
        else if (statement.indexOf("Mr.") >= 0){
            response = "He sounds like a good teacher. ";
        }
        else if (statement.indexOf("Ms.") >= 0 || statement.indexOf("Mrs.") >= 0){
            response = "She sounds like a good teacher.";
        } 
        else if (statement.trim().length() == 0){
            response = "Say something, please."; 
        }
        else if (statement.indexOf("ice cream") >= 0 || statement.indexOf("cake") >= 0){
            response = "Ooo I love dessert, that sounds good.";
        }
        else if (statement.indexOf("soccer") >= 0 || statement.indexOf("swimming") >= 0){
            response = "I know someone who played that sport!";
        }
        else if (statement.indexOf("black") >= 0 || statement.indexOf("pink") >= 0){
            response = "I love that color!";
        }
        else if (findWord(statement, "I want") >= 0) {
            response = transformIWantStatement(statement);
        }
        else if (ifind >= 0 && findWord(statement, "you") >= 0) {
            response = transformIYouStatement(statement);
        }
        else if (findWord(statement, "I want to") >= 0) {
            response = transformIWantToStatement(statement);

        }
        else if (youfind2 >= 0 && findWord(statement, "me") >= 0) {
            response = transformYouMeStatement(statement);
            
		} 
        else {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        } 
        else if (whichResponse == 4){

            response = "That's cool...";
        }
        else if (whichResponse == 5){

            response = "Sorry, I don't understand exactly.";
        }
    
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        
        str = " " + str.toLowerCase() + " ";
        word = " " + word.toLowerCase() + " ";

    return str.indexOf(word);
    }

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement){
        
        statement = statement.trim();
		String lastchar = statement.substring(statement.length() - 1); 
		if (lastchar.equals("!") || lastchar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
        }
        
		int find = findWord(statement, "I want");
		String finalstatement = statement.substring(find + 7).trim();
		return "Would you really be happy if you had " + finalstatement + "?";
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement){

        statement = statement.trim();
		String lastchar = statement.substring(statement.length() - 1);
		if (lastchar.equals("!") || lastchar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}

		int youfinal = findWord(statement, "I");
		int mefinal = findWord(statement, "you");
		String finalstatement = statement.substring(youfinal + 1, mefinal).trim();
		return "Why do you " + finalstatement + " me?";
	}

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement){
		statement = statement.trim();
		String lastchar = statement.substring(statement.length() - 1);
		if (lastchar.equals("!") || lastchar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}
		int find = findWord(statement, "I want to");
		String finalstatement = statement.substring(find + 9).trim();
		return "What would it mean to " + finalstatement + "?";
    }



    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement){
		statement = statement.trim();
		String lastchar = statement.substring(statement.length() - 1);
		if (lastchar.equals("!") || lastchar.equals(".")){
			statement = statement.substring(0, statement.length() - 1);
		}

		int youfind = findWord(statement, "you");
		int mefind = findWord(statement, "me");
		String finalstatement = statement.substring(youfind + 3, mefind).trim();
		return "What makes you think that I " + finalstatement + " you?";
    }
}
