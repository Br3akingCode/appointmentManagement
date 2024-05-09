import java.net.*;
import java.io.*;
import java.io.ObjectOutputStream;
/**
 * create main object of type Apptbook before the loop and pass it to each server thread
 */
public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
        // 1: Listen on the given port for a connection request
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // 3: Accept connection request
        Socket clientSocket = serverSocket.accept();
        //this sends out the message as an object; ObjectOutputStream works with objects
        ObjectOutputStream out =
            new ObjectOutputStream(clientSocket.getOutputStream());

        ObjectInputStream in = new ObjectInputStream
            (clientSocket.getInputStream());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {

            AppointmentBook apptBook = new AppointmentBook();
            Appointment inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();

            /**
             * lines 37-52 is inspired by this explanation from Youtube creator, 
             * WittCode
             * Video Title: "Java Socket Programming Client Server Messenger"
             * URL: https://www.youtube.com/watch?v=gchR3DpY-8Q
             * Reason: I needed to understand concept and didn't know how to proceed
             * ACCESSED ON: 02/08/24
             */

            while (true) {
                // #9 receieve response from client
                //look at docs. instead of reading line, I want to read the object
                //inputLine = in.readLine();
                //change data type: cast message; use inputline as a message object
                try{
                    inputLine = (Appointment)in.readObject();
                    if (inputLine == null) {
                        break;
                    }
                    //if there is no appt at that date and time
                    if(apptBook.searchAppt(inputLine) ==false){
                        //add to appt book
                        apptBook.addAppt(inputLine);
                        inputLine.setStatus(true); //status initially set to false, once an appt is added change to true
                        out.writeObject(inputLine); //you want the client to know the appt was accepted, so you send the object back
                    }else{
                        out.writeObject(inputLine); // this sends the client's object back if we can't add the appointment 
                    }

                }catch (ClassNotFoundException e) {
                    System.out.println("Exception: Class not found");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
