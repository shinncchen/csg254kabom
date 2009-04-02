/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import server.ChatMaster;
import server.UserInfo;
import server.security.Security;

/**
 *
 * @author HuskyHackers
 */
public class Rid410 extends Request {
    
    public Rid410() {
        super(Request.RID_410);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        if(super.requestData != null && requestData.length > 0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;
            try {
            	ois = new ObjectInputStream(bais);
            	//get rid of RID and PERMIT
            	ois.readInt();
            	ois.readObject();
            	String usr = (String)ois.readObject();
            	//check valid username
	            if (ChatMaster.usersDB.isUserValid(usr)){
	            	byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[]) ois.readObject());
	            	
	            	ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
					ObjectInputStream ois2 = new ObjectInputStream(bais2);
					//store T1
					userInfo.setTimeT1((byte[])ois2.readObject());
					//if skew is correct
	            	if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta())){
	            		//Check Ua is same 
	            		String userBIp = null;
	            		if (usr.equals((String)ois2.readObject())){
	            			String Ub = (String)ois2.readObject();
	            			Iterator iterator = ChatMaster.users.entrySet().iterator();
	            			while (iterator.hasNext()) {
	            				if(Ub.equalsIgnoreCase(((UserInfo)iterator.next()).getUsername())){
	            					userBIp = ((UserInfo)iterator).getIpAdress();
	            					break;
	            				}
	            			}
	            			UserInfo userBInfo = (UserInfo) ChatMaster.users.get(userBIp);
	            			if(userBInfo.isLoggedIn()){
	            				Request rid420 = new Rid420();
	            				Object[] objects = new Object[1];
	            				objects[0] = (UserInfo)userBInfo;
	            				rid420.sendRequest(userInfo, objects);
	            			}
	            	  	    else {System.out.println("Ub is not online");}
	            		}
	            		else{} //ignore
		            	}
		            	else{} //ignore
	            	}     
	            } //end of try catch block
            catch (Exception e) { System.err.println("Couldn't parse objects, ignoring"); e.printStackTrace(); }
            
        }
    }
    
    
    
    
}
