package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.getAllUsers();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(4);
        List<User> lst = userService.getAllUsers();
        for (User l:lst) {
            System.out.println(l);
        }
        userService.cleanUsersTable();
        lst = userService.getAllUsers();
        for (User l:lst) {
            System.out.println(l);
        }
        userService.dropUsersTable();
    }
}
