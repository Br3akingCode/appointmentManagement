import java.net.*;
import java.io.*;

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 1;
    private int state = WAITING;
    private String serverReceivedMessage;
    private String clientReceivedMessage;
    /**
     * Method: processInput
     * @param String theInput
     * @return theOuput based on theInput from the user; this is based on the
     * user's response to the server. A string is returned
     * 
     * it is checking if we are waiting or in conversation, therefore, think about this for 
     * suggestion: instead of waiting, maybe accept or deny based off of time and date issue(talk to chays) should it be in this class or is there a better way 
     * 
     */
    public String processInput(Appointment theInput) {
        //1st condition: connection established
        //2nd condition: I'm now in cpnversation: communication between client and server
        //3rd condition: connection ends: 'bye'
        //within else if, nested else that describes what happens during conversation
        //make another else if 27-31: to do predetermined responses output: 
        //optional for more of AI concept

        String theOutput = " ";

        if (state == WAITING) {
            /**
             * instead of connection established have, server ask client for patient information 
             * ask Dr. chays about caching for time and date
             * CAN REPLACE CONNECTION ESTABLISHED 
             */
            theOutput = "Input Patient Information";
            state = IN_CONVERSATION;

            
            /**
             * line 36 used stack overflow to explain why NullPointerException was 
             * present
             * URL: https://stackoverflow.com/questions
             * /218384/what-is-a-nullpointerexception-and-how-do-i-fix-it
             * ACCESSED ON: 02/08/24
             */
        } else if (state == IN_CONVERSATION && theInput !=null) {
            if (theInput.getStatus() == true) { //this will not run because we are reading in objects not strings 
                state = WAITING; 
                theOutput = "Your appointment has been approved";
            }
            // }else{
                // serverReceivedMessage = theInput;
                // clientReceivedMessage = theInput;
                // theOutput = serverReceivedMessage + "\n" + clientReceivedMessage;
            // }
        }
        return theOutput;
    } 
} 
