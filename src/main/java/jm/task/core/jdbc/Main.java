package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan1", "Ivanov1", (byte) 10);
        userService.saveUser("Ivan2", "Ivanov2", (byte) 20);
        userService.saveUser("Ivan3", "Ivanov3", (byte) 30);
        userService.saveUser("Ivan4", "Ivanov4", (byte) 40);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
