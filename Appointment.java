import java.io.*; 
/**
 * This is a class for Appointment object and will set the requirements for my object
 * (Appointment)
 *
 * @author Angie Diaz
 * @version 04/12/24
 */
public class Appointment implements java.io.Serializable
/**
 * for instance variables, insert attributes for appointment object name, dob...
 */
{
    private String firstName; //one attribute
    private String lastName; //second attribute
    private String treatmentType; //third attribute: treatment type
    private String DOB; //talk to chays about storing is it okay? //talk about java reserved word date and time 
    private String insuranceType; //fifth attribute insurance type 
    private String day;
    private String clock;
    private boolean status; // this will confirm or deny for a patient 
    /**
     * separate class that stores all appointments; array: synchronzied methods; update and wait 
     * drop class from lab 5, a
     * server threads will share appointment book: the same way we shared it in drop class
     * create appointment object and pass it as a parameter to each server thread constructor. 
     * this will handle multiple points 
     */
    /**
     * Appointment object is my blueprint class(double check with him) 
     * what information is needed from the client to request an appointment?
     * new blueprint class: would have this information as attributes 
     * transaction request obj from client to server and it could be an appointment object to server to client. 
     */
    
    /**
     * once server checks transaction request, then checks if apppointment is valid in appointment book and then updates it, checking and updating will be synchornized 
     * methods
     */
    /**
     * @constructor
     * @param String firstName, String lastName, String treatmentType, String DOB, String insuranceType
     * 
     * 
     */
    public Appointment(String firstName, String lastName, String treatmentType, String DOB, String insuranceType, String day, String clock){
        this.firstName = firstName;
        this.lastName = lastName;
        this.treatmentType = treatmentType;
        this.DOB = DOB;
        this.insuranceType = insuranceType;
        this.day = day;
        this.clock = clock;
        this.status = false;
    }
    
    public void setName(String firstName){
        this.firstName = firstName; 
    }
    
    public void setlastName(String lastName){
        this.lastName = lastName;
    }
    public void setTreatment(String treatmentType){
        this.treatmentType = treatmentType;
    }
    public void setDOB(String DOB){
        this.DOB = DOB;
    }
    public void setInsurance(String insuranceType){
        this.insuranceType = insuranceType; 
    }
    public void setDay(String day){
        this.day = day; 
    }
    public void setClock(String clock){
        this.clock = clock;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }
    public String getName(){
        return firstName;
    }
    public String getlastName(){
        return lastName;
    }
    public String getTreatment(){
        return treatmentType;
    }
    public String getDOB(){
        return DOB;
    }
    public String getInsurance(){
        return insuranceType;
    }
    public String getday(){
        return day;
    }
    public String getclock(){
        return clock;
    }
    public boolean getStatus(){
        return status;
    }
}
