/**
 * Database where username/hash password are stored for a client
 * 
 * @author HuskyHackers
 */

package server.database;

import java.util.ArrayList;
import java.util.Arrays;
import server.security.Security;

public class UsersDB {
	
	ArrayList<Users> db = new ArrayList<Users>();

    /**
     * UserDB constructor: hard code DB
     */
	public UsersDB() {
		db.add(new Users("Abdulla", Security.hexToByteArray("31b7f0ffe620dba070702df0c80e0c4195a2757f")));
		db.add(new Users("Shinn", Security.hexToByteArray("fb9fdc2ad3f0128d33a532df2916a3488994730c")));
		db.add(new Users("Deepak", Security.hexToByteArray("3fa78fd63dd76ba74a102cf4e1e79303ef9149a1")));
		db.add(new Users("Raghu", Security.hexToByteArray("91d1dd8615d34a96099572be3f37ee9127974eeb")));
		db.add(new Users("ta1", Security.hexToByteArray("f26245dc6d3d53ee390f44af0039bbe7c01de0dd")));
		db.add(new Users("ta2", Security.hexToByteArray("934402734f777ba90d48f43c6850acfbba72bf1c")));
		db.add(new Users("team1", Security.hexToByteArray("7f7a0b54859c409a9f7a4dfa1855059506eb2991")));
		db.add(new Users("team2", Security.hexToByteArray("f3841311fe1eecf560e72094e67a0efbabbee149")));
		db.add(new Users("team3", Security.hexToByteArray("f7fe1eadea9c6279d673c7c911b09eff8d2e085f")));
		db.add(new Users("team4", Security.hexToByteArray("9d272b3a051d2e622a3d619a90f598ff66d01ea4")));
		db.add(new Users("team5", Security.hexToByteArray("c0b2f2591caac164d0bec9b6dd7c0e7f07e14e49")));
		db.add(new Users("team6", Security.hexToByteArray("0200cae9db950ab8b255a8b0217f01a3344bf33d")));
		db.add(new Users("team7", Security.hexToByteArray("feac1b3483d50be1050a31abfcac58eb4ed9e27b")));
		db.add(new Users("noubir", Security.hexToByteArray("89ac946b49a15351f5139ce432ebfb676766890a")));
	}

    /**
     * Check if a user exist in the db
     * @param user - string, the username to be verified
     * @return     - boolean, true if the username exist
     *                        false otherwise
     */
	public boolean isUserValid(String user) {
		for (int i=0; i<db.size(); i++) {
			if (db.get(i).getUserName().equals(user)) return true;
		}
		return false;
	}

    /**
     * Check if the hash password is correct given an username
     * @param userName       - string, username to be verified
     * @param hashOfPassword - byte[], its hash password
     * @return               - boolean, true if it matches
     *                                  false otherwise
     */
	public boolean isPassCorrect(String userName, byte[] hashOfPassword) {
		for (int i=0; i<db.size(); i++) {
			if (db.get(i).getUserName().equalsIgnoreCase(userName) && Arrays.equals(db.get(i).getPassHash(), hashOfPassword))
					return true;
		}
		return false;
	}
}
