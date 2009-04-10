package server;


/**
 *
 * @author Raghuram
 */
public class Main {

    /**
     * @param args first argument must be port of server or null. if null, it will use the default (4444)
     */
    public static void main(String[] args) {
    	if(args != null && args.length == 1) {
            try {
                ChatMaster.SERVER_PORT = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Synopsis: java -jar bin [port]");
                e.printStackTrace();
                System.exit(0);
            }
        }
        ChatMaster.initialize();
    }

}
