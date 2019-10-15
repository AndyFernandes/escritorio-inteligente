import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

import org.json.*;


//Lampada
public class main {
	
	 public static void main(String args[]){
		 
		 try {
	            Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
	            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            String content = br.readLine();
	            System.out.println(content);
	            try {
	            	String status;
	            	String id;
	            	JSONObject json = new JSONObject(content);
	            	status = json.getJSONObject("acoes").getString("status");
	            	id = json.getString("id");
	            	
	            	JSONObject envio = new JSONObject();
		            envio.put("status", status);
		            envio.put("porta", 5000);
		            envio.put("ip", InetAddress.getLocalHost());
		            envio.put("id", id);
		            
		          
		            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		            output.writeUTF(envio.toString());
		            output.close();
		               
		            
		            
	            }
	            catch(Exception e) {
	            	
	            }
	            
	            
	            socket.close();

	        } catch (UnknownHostException e) {
	            System.err.println("Unknown Host.");
	           // System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for "
	                               + "the connection.");
	          //  System.exit(1);
	        }

     

     }
}