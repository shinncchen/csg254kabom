package server.database;

import java.util.ArrayList;
import java.util.Arrays;

import server.security.Security;

public class UsersDB {
	
	ArrayList<Users> db = new ArrayList<Users>();
	
	//constructor: hard code DB
	public UsersDB() {
		db.add(new Users("Abdulla", new Security().getHash("ok".getBytes())));
		db.add(new Users("Shinn", new Security().getHash("ok".getBytes())));
		db.add(new Users("Deepak", new Security().getHash("ok".getBytes())));
		db.add(new Users("Raghu", new Security().getHash("ok".getBytes())));
	}

	public boolean isUserValid(String user) {
		for (int i=0; i<db.size(); i++) {
			if (db.get(i).getUserName().equals(user)) return true;
		}
		return false;
	}
	
	public boolean isPassCorrect(String userName, byte[] hashOfPassword) {
		for (int i=0; i<db.size(); i++) {
			if (db.get(i).getUserName().equals(userName) && Arrays.equals(db.get(i).getPassHash(), hashOfPassword))
					return true;
		}
		return false;
	}
}
