package irc_client;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * file for handelig the finding and storing the lanugabe bundle spesified by user
 * if no lanugage is chosen will look up default language.
 * 
 * @author Kristoffer
 * @author Kevin
 *
 */
public class Language {

	private static ResourceBundle messages;	//holds string values
	private static Locale currentLocale;	//holds users current locaton and language
	
	/**
	 * Chooses what language bundle to use
	 * 
	 * @param args to specify what language user want to use, if not supplied the computers default language will be chosen
	 */
	public static void setLocale(String[] args){
		
		//if user decideds a language on launch
		if (args.length==2) {
			currentLocale = new Locale (args[0], args[1]);
		} else if (args.length==1) {
			currentLocale = new Locale (args[0]);
		} else	//else the computers default language 
			currentLocale = Locale.getDefault();
			
		//get correct lanugage bundle
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	}
	
	/**
	 * Function for geting string resourses based on the resourse key
	 * 
	 * @param key The resource key to get
	 * @return The string correct language string
	 */
	public static String getMsg(String key){
		return messages.getString(key);
	}

}