package com.benedict.minibank.Models;

import com.benedict.minibank.Services.dao.AuthorDAO;
import com.benedict.minibank.Services.dao.UserDAO;
import com.benedict.minibank.Views.ViewFactory;


import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    public final UserDAO userDAO;
    public final AuthorDAO authorDAO;
    private boolean loginSuccessFlag;
    // private final ObservableList<Client> clients;
    private  User currentUser;



    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
        this.authorDAO = new AuthorDAO(new DatabaseDriver().getConnection());
        this.loginSuccessFlag = false;
        this.currentUser = null;
        //this.clients = FXCollections.observableArrayList();
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


    public boolean getAdminSuccessFlag(){
        return this.loginSuccessFlag;
    }

    public void  setClientAdminSuccessFlag(boolean flag){
        this.loginSuccessFlag = flag;
    }

    public boolean hasRegisteredUsers() {
        return userDAO.countUsers() > 0;
    }

    public boolean isUserAlreadyRegistered(String userName) {
        return userDAO.isUserExist(userName);
    }

    public void createUser(String userName, String password) {
        userDAO.createUser(userName, password, LocalDate.now());
    }

    public void checkCredentials(String userName, String password){
        User user = userDAO.findUserByCredentials(userName, password);
        if (user != null) {
            this.loginSuccessFlag = true;
            this.currentUser = user;
        }
    }

    public String getLoggedUserName(){
        return  currentUser != null ? currentUser.usernameProperty() : null;
    }

    public int getLoggedUserId(){
        return currentUser != null ? currentUser.getId() : null;
    }

    public void createAuthor(String fName, String lastName, String email, String city){
        Author author = new Author(fName, lastName, email, city);

        authorDAO.create(author);
    }
}
