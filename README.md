This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Dental Appointment management
PURPOSE OF PROJECT: to manage requests made by clients(users/patients) and either accept or deny depending if the time and date is avaliable.
VERSION or DATE: Version 1v
HOW TO START THIS PROJECT: 1)Establish server socket with a port number, thus run 'KnockKnockServer'and run void main(String[] args) and input a port number as a string
2)Run 'KnockKnockClient' with the parameters of host name,and port number.
AUTHORS: Angie Diaz
USER INSTRUCTIONS:
1. Once you have a connection established, you'll be prompted with multiple questions that you would expect at a doctor's office. For example, first name, last name, DOB, 
treatment type, insurance type, requested date and time of appointment. 
2. Once you submit all questions asked, the server will reply that your appointment is confirmed with your requested date and time 
3. if another client requests the same date and time, then the server will send back that that appointment request is unavailable and will be prompted to pick a new date
and time. Once new date and time is input by the user, the server will reply that your appointment is confirmed. 
4. This project deals with synchronization issues of having a client requesting an appointment with the same date and time. 

