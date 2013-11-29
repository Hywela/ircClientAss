package no.hig.irc_client;

/**
 * @author Uhu
 *
 */
public class Profile {

	
	String username;	// name of your user
	String realName;	// real name of user
	String currentNick;	// nickname beeing used
	String primaryNick;	// primary nickname
	String altNick1;	// alternate nicknames
	String altNick2;	
	

	public Profile(String username, String realName, String primaryNick, String altNick1, String altNick2){
		this.username = username;											//username Required
		this.realName = realName == null ? "" : realName;					//real name can be empty
		this.primaryNick = primaryNick == null ? username : primaryNick;	//nickname is set to username if empty
		this.altNick1 = altNick1 == null ? "" : altNick1;					//alternate nicknames optional
		this.altNick2 = altNick2 == null ? "" : altNick2;
		
		currentNick = primaryNick;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getCurrentNick() {
		return currentNick;
	}


	public void setCurrentNick(String currentNick) {
		this.currentNick = currentNick;
	}


	public String getPrimaryNick() {
		return primaryNick;
	}


	public void setPrimaryNick(String primaryNick) {
		this.primaryNick = primaryNick;
	}


	public String getAltNick1() {
		return altNick1;
	}


	public void setAltNick1(String altNick1) {
		this.altNick1 = altNick1;
	}


	public String getAltNick2() {
		return altNick2;
	}


	public void setAltNick2(String altNick2) {
		this.altNick2 = altNick2;
	}

}
