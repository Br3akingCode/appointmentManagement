import java.io.*;
import java.net.*;

public class KnockKnockClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        //change args length and then add args[]
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
        // #2: Initiate a connection request to server's IP address, port number
        //replace with appropriate things
        Socket kkSocket = new Socket(hostName, portNumber);
        ObjectOutputStream out = new ObjectOutputStream(kkSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(kkSocket.getInputStream());
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            //String fromServer;
            //String fromUser;
            String firstName; //one attribute
            String lastName; //second attribute
            String treatmentType; //third attribute: treatment type
            String DOB; //fourth attribute
            String insuranceType; //fifth attribute insurance type 
            String day;
            String clock;

            //read server and then print it out
            //System.out.println("Server: " + in.readObject()); // Display message from server
            //option hardcode it in
            while (true) {
                System.out.println("What's your first name"); //ask my first question to store first name in 
                System.out.print("Patient: ");
                // #7 read response from client
                firstName = stdIn.readLine();

                System.out.println("What's your last name"); //askss the clients their last name

                System.out.print("Patient: ");
                // #7 read response from client
                lastName = stdIn.readLine();

                System.out.println("What treatment are you receiving?"); //asks the client what treatment they are receiving

                System.out.print("Patient: ");
                // #7 read response from client
                treatmentType = stdIn.readLine();

                System.out.println("What's your Date of Birth?"); //asks the clients their DOB
                System.out.print("Patient: ");
                // #7 read response from client
                DOB = stdIn.readLine();
                System.out.println("What's your insurance?"); //asks the clients the insurance they have

                System.out.print("Patient: ");
                // #7 read response from client
                insuranceType = stdIn.readLine();

                System.out.println("What day do you want your appointment?"); //asks the client what day they want their appointment

                System.out.print("Patient: ");
                // #7 read response from client
                day = stdIn.readLine();

                System.out.println("What time do you want your appointment?"); //asks the client what time do they want their appointment 

                System.out.print("Patient: ");
                // #7 read response from client
                clock = stdIn.readLine();

                Appointment userResponse = new Appointment(firstName, lastName,treatmentType, insuranceType, DOB, day, clock ); //pass all attributes 
                // #8 send response to server
                out.writeObject(userResponse);

                // #5 Receive message from server
                //if statement is checking date and time and status
                //check status of the appt

                Appointment serverResponse = (Appointment) in.readObject();
                //if appointment is not accepted then choose new day

                //for loop that if their is already an appointment in the request the client has sent then they will be prompted to chppse another date and time 
                //date and time will be inputed and then date and time will be updated and sent back to the server
                if(serverResponse.getStatus() == false){
                    System.out.print("Date in unavailiable. Please choose another date.");
                    // #7 read response from client
                    day = stdIn.readLine();
                    serverResponse.setDay(day);//this "updates" the new day; parameter is new date
                    System.out.print("Time in unavailiable. Please choose another time.");
                    // #7 read response from client
                    clock = stdIn.readLine();
                    serverResponse.setClock(clock);//this "updates" the new time; parameter is new time 
                    out.writeObject(serverResponse); // this sends response to server 
                    System.out.println("Your appointment has been confirmed for " + serverResponse.getday() + " at " + serverResponse.getclock());
                }else{
                    System.out.println("Your appointment has been confirmed for " +  serverResponse.getday() + " at " + serverResponse.getclock());
                }

            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: Class not found");
        }
    }
}
