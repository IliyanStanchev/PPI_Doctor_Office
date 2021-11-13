package sample;

import dao.implementation.*;
import entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class DatabaseFiller {

    public void fillDatabase() {

        User user = new User("ench3r@gmail.com", "sach", "123", "Iliyan", "Stanchev", "0897875640");

        UserDAO userDAO = new UserDAO();

        user = userDAO.saveOrUpdate(user);
    }
}
