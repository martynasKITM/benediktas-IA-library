package com.benedict.minibank.Models;

import com.benedict.minibank.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private boolean loginSuccessFlag;
    private final ObservableList<Client> clients;


    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.loginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return  model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){
        return this.databaseDriver;
    }

    public boolean getAdminSuccessFlag(){
        return this.loginSuccessFlag;
    }

    public void  setClientAdminSuccessFlag(boolean flag){
        this.loginSuccessFlag = flag;
    }

    public void checkCredentials(String userName, String password){
        User user = databaseDriver.findUserByCredentials(userName, password);
        if (user != null) {
            this.loginSuccessFlag = true;
        }
    }

    public ObservableList<Client> getClients(){
        return clients;
    }

    public void setClients(){
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try{
            while (resultSet.next()){
                String firstName =  resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                clients.add(new Client(firstName, lastName,pAddress, date));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
