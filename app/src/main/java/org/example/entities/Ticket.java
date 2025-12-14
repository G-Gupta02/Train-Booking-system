package org.example.entities;
import java.util.Date;

import javax.print.attribute.standard.Destination;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private Train train;

    public Ticket(){}

    public Ticket(String userId, String ticketId, String Source, String Destination, Date dateOfTravel, Train train){
        this.userId= userId;
        this.ticketId= ticketId;
        this.source= Source;
        this.destination= Destination;
        this.dateOfTravel= dateOfTravel;
        this.train=train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: "+ticketId+" belongs to User "+userId+" from "+source+" to "+destination+" on "+dateOfTravel );
    }
    public String getTicketId(){
        return ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId=ticketId;
    }

    public String getSource(){
        return source;
    }

    public void setSource(String source){
        this.source=source;
    }
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination=destination;
    }


    public Date getDateOfTravel(){
        return dateOfTravel;
    }

    public void setDateOfTravel(Date dateOfTravel){
        this.dateOfTravel=dateOfTravel;
    }
    
    public Train getTrain(){
        return train;
    }

    public void setTrain(Train train){
        this.train=train;
    }

}
