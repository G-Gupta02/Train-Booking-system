package org.example.entities;
import java.util.*;
import java.sql.Time;
public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> Seats;
    private Map<String, String> stationTimes;
    private List<String> stations;
    

    public Train(){}

    public Train(String trainId, String trainNo, List<List<Integer>> Seats, Map<String, String> stationTimes, List<String> stations){
        this.trainId=trainId;
        this.trainNo=trainNo;
        this.Seats=Seats;
        this.stationTimes=stationTimes;
        this.stations=stations;
    }

    public String getTrainId(){
        return trainId;
    }

    public void setTrainId(String trainId){
        this.trainId=trainId;
    }


    public List<List<Integer>> getSeats(){
        return Seats;
    }
    public void setSeats(List<List<Integer>> Seats){
        this.Seats=Seats;
    }

    public Map<String, String> getStationTimes(){
        return stationTimes;
    }
    public void setStationTimes(Map<String, String> stationTimes){
        this.stationTimes=stationTimes;
    }

    public List<String> getStations(){
        return stations; 
    }
    public void setStations(List<String> stations){
        this.stations=stations;
    }

    public String getTrainNo(){
        return trainNo;
    }
    public void setTrainNo(String trainNo){
        this.trainNo=trainNo;
    }


    public String getTrainInfo(){
        return String.format("Train ID: "+trainId+" Train No.: "+trainNo);
    }
}
