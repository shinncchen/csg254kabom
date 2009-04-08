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
