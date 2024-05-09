
/**
 * This will have an array that will store all my patient's info, only server should have access to this 
 * 
 * appointment book and drop class merge these two classes and make the methods synchronized
 * 
 * @author Angie Diaz
 * @version 1.0
 */
public class AppointmentBook
{
    //  array: similar to appointment obj class will have methods,: done 
    //  have a search method, that searches by date, and looks at appt book to see if date is available or not 
    // instance variables - replace the example below with your own
    //method that puts appointments in the array; done
    //search method that goes thru the appt book 
    //suggestion remove method? 
    //keep track of the number of appts in appt book, so you can access index

    //make an array of appointment objects 
    private Appointment storage[];

    //private boolean empty = true;

    int numAppt;

    /**
     * Constructor for objects of class AppointmentBook: var-name = new type [size];
     * determine size of array and numAPPt in constructor 
     */
    public AppointmentBook()
    {
        storage = new Appointment [10]; //initalizing the size 
        numAppt = 0; 

    }

    /** 
     * @method: Synchronized methods: this passes the array through bookHold and then increments the number of appts
     * @param Appointment bookHold
     * 
     */
    public synchronized void addAppt(Appointment bookHold)
    {
        storage [numAppt] = bookHold; //this holds numAppt in array which is passed thru bookHold
        numAppt++; //increment numAppt
    }

    /**
     * @method: this is a synchronized method: this will compare and search through book focusing on time and date
     * @param: Appointment searchBook
     * @return; false if conditions are not met of date and time 
     * syn issue
     */
    public synchronized boolean searchAppt(Appointment searchBook){
        //for loop: we actually want to see the details about the appt 
        for(int i = 0; i <numAppt; i++){
            //want to grab date and time and then compare it to searchbook
            //if condition: looks at first day and then compares it to whatever day in in my searchbook
            //.equal is how you compare strings equivalent to ==
            if(storage[i].getday() .equals (searchBook.getday())){
                if(storage[i].getclock() .equals (searchBook.getclock())){
                    return true;
                }
            }
        }
        return false;

    }
}
