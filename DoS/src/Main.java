import java.lang.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length==0) {
			System.out.println("Usage: server port [0|1]");
			System.out.println("0 for UDP | 1 for TCP");
		} else {
			try {
				new Run(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			} catch (Exception e) { System.out.println("Exception e: " + e); e.printStackTrace(); }
		}
	}

}
