/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
            System.out.println("im in");
            try {
            	ois = new ObjectInputStream(bais);
            	//get rid of RID and PERMIT
            	ois.readInt();
            	ois.readObject();
            	String usr = (String)ois.readObject();
            	System.out.println("permit sent by: "+usr);
            	//check valid username
	            if (ChatMaster.usersDB.isUserValid(usr)){
	            	byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[]) ois.readObject());
	            	
	            	ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
					ObjectInputStream ois2 = new ObjectInputStream(bais2);
					//store T1
					userInfo.setTimeT1((byte[])ois2.readObject());
					//if skew is correct
	            	if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta()))
	            	{
	            		//Check Ua is same 
	            		String userBIp = null;
	            		UserInfo tmp = null;
	            		if (usr.equals((String)ois2.readObject())){
	            			String Ub = (String)ois2.readObject();
	            			System.out.println(Ub);
	            			//if(usr.equalsIgnoreCase(Ub))
	            			//{
	            			
	            			//Iterator<String> keyIter = (ChatMaster.users.keySet()).iterator();
	                       //String userIP = null;

	                        /*while (keyIter.hasNext()) {
	                            // for each entry
	                            userIP = keyIter.next();
	                            System.out.println("inside the while loop:" +userIP);
	                            // get userInfo
	                            tmp = (UserInfo) ChatMaster.users.get(userIP);
	                            if (Ub.equalsIgnoreCase(tmp.getUsername())){
	            					System.out.println("username:" +tmp.getUsername());
	            					userBIp = tmp.getIpAdress();
	            					break; 
	            				}   
	                            }
	                        }*/
	            			
	                       Iterator<String> iterator = (ChatMaster.users.keySet()).iterator();
	            			while (iterator.hasNext()) {
	            				tmp = (UserInfo)ChatMaster.users.get(iterator.next());    				
	            				if (Ub.equalsIgnoreCase(tmp.getUsername())){
	            					userBIp = tmp.getIpAdress();
	            					break; 
	            				}           					
	            				
	            			}
	            			try{
	            				if(userBIp != null) {
	            					
	            			UserInfo userBInfo = (UserInfo) ChatMaster.users.get(userBIp);
	            			
	            			if(userBInfo.isLoggedIn()){
	            				Request rid420 = new Rid420();
	            				Object[] objects = new Object[1];
	            				objects[0] = (UserInfo)userBInfo;
	            				rid420.sendRequest(userInfo, objects);
	            			}
	            	  	    else {System.out.println("Ub is not online");}
	            			}
	            			}
	            			catch(NullPointerException e){
	            				System.out.println("user" +Ub+ "is not online");
	            			}
	            			
	            			
	            		//}
	            		//	else{
	            		//		System.out.println("Requester for a ticket for self !!");
	            		//	}
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
