package server.database;

public class Users {

	private String userName;
	private byte[] passHash;
	
	public Users(String user, byte[] hPass) {
		this.userName = user;
		this.passHash = hPass;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passHash
	 */
	public byte[] getPassHash() {
		return passHash;
	}
	/**
	 * @param passHash the passHash to set
	 */
	public void setPassHash(byte[] passHash) {
		this.passHash = passHash;
	}
	
	
}
