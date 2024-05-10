/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;
/**
 * this represents each server thread communicating with one client and server thread has access to apptbook
 */
public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    private AppointmentBook apptBook;
    /**
     * show him constructor and how we move forward to communicate appointment(client-server) and appointmentBook(server to server)
     */
    public KKMultiServerThread(Socket socket,AppointmentBook apptBook) {
        super("KKMultiServerThread");
        this.socket = socket;
        this.apptBook = apptBook;
    }

    public void run() {

        try (
        //refer to lab 6 instead of printwriter out and buffered reader in
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            Appointment inputLine; 
            String outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            in.readObject(); //you want to read it, instead of writing it, trying to send something 
            //look at while loop and you can use system.out.println(inputline) 
            //server is talking over client; therefore you want 
            while ((inputLine = (Appointment)in.readObject()) != null) {
                System.out.println(inputLine); //print out client's message
                outputLine = kkp.processInput(inputLine); //processes the message; sends it to protocol //ask which one to send appointment or appointmentBook
                System.out.println("Server" + outputLine); // this will print the server side

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

            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: Class not found");
        }
    }
}
