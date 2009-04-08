/**
 * A Class where the server get/set user's credential
 *
 * @author HuskyHackers
 */

package server.database;

public class Users {

	private String userName;
	private byte[] passHash;

    /**
     * Users constructor
     * @param user  - string, username of the client
     * @param hPass - byte[], hash password
     */
	public Users(String user, byte[] hPass) {
		this.userName = user;
		this.passHash = hPass;
	}
	
	/**
     * Get the username
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
     * Set the username
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
     * Get the hash password
	 * @return the passHash
	 */
	public byte[] getPassHash() {
		return passHash;
	}
	/**
     * Set the hash password
	 * @param passHash the passHash to set
	 */
	public void setPassHash(byte[] passHash) {
		this.passHash = passHash;
	}
	
}
