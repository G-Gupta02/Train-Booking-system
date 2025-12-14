package org.example.services;

import org.example.entities.User;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.example.util.UserServiceUtil;
import org.example.entities.Ticket;
import org.example.entities.Train;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../localDb/users.json";

    public UserBookingService() throws IOException {}

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);

        if (users.exists() && users.length() > 0) {
            userList = objectMapper.readValue(
                    users, new TypeReference<List<User>>() {
                    });
        } else {
            userList = new ArrayList<>();
        }

        // userList = objectMapper.readValue(users, new TypeReference<List<User>>() {})
        // ;
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }
    // json--> object---> deserialisation
    // object --> json---> Serialisation

    public void fetchBookings() {
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Ticket ID to be cancelled: ");
        ticketId = sc.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Enter valid ticketId");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;// Since strings are immutable
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));

        if (removed) {
            System.out.println("Ticket with Ticket Id: " + ticketId + " has been cancelled");
            return Boolean.TRUE;
        } else {
            System.out.println("No Ticket found with Ticket Id: " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeats(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("Retry");
                return false;
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

}