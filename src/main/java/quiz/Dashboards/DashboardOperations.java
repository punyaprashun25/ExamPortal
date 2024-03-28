package quiz.Dashboards;

import java.util.Scanner;

import quiz.models.User;
import quiz.service.UserService;
import quiz.serviceimpl.UserServiceImpl;

public class DashboardOperations {
    Scanner scan = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

    public void registration() {
        System.out.println("--------Registration-------");
        System.out.print("Username : ");
        String username = scan.nextLine();
        System.out.print("Full Name : ");
        String name = scan.nextLine();
        System.out.print("Email : ");
        String email = scan.nextLine();
        System.out.print("Mob : ");
        String mob = scan.nextLine();
        System.out.print("Password: ");
        String pass = scan.nextLine();
        System.out.print("Cofirm Password: ");
        String temp_pass = scan.nextLine();
        while (!temp_pass.equals(pass)) {
            System.out.print("Password: ");
            pass = scan.nextLine();
            System.out.print("Cofirm Password: ");
            temp_pass = scan.nextLine();
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setMob(mob);
        user.setPassword(pass);
        user.setReports(null);
        userService.createUser(user);
        System.out.println("User created....");
    }

    public void login() {
        System.out.println("Username : ");
        String temp_username = scan.nextLine();
        System.out.println("Password : ");
        String temp_pass = scan.nextLine();

        User user = userService.getUser(temp_username);
        while (user == null) {
            System.out.println("Username doesn't exist...kindly provide correct details");
            System.out.println("press x to exit or any key to try again");
            String choice = scan.nextLine();
            if (choice.equals("x"))
                return;
            System.out.println("Username : ");
            temp_username = scan.nextLine();
            System.out.println("Password : ");
            temp_pass = scan.nextLine();
            user = userService.getUser(temp_username);
        }
        while (user.getEnabled() && user.getAttempts() < 3) {
            if (user.getPassword().equals(temp_pass)) {
                // getting role of the user
                switch (user.getRole().getRoleName()) {
                    case "Student": {
                        StudentDashboard sd = new StudentDashboard(user);
                        sd.DashBoard();
                    }
                        return;
                    case "Admin": {
                        AdminDashboard ad = new AdminDashboard(user);
                        ad.DashBoard();
                    }
                        return;
                }
            } else {
                userService.updateAttempt(temp_username, user.getAttempts() + 1);
                user = userService.getUser(temp_username);
                if (user.getAttempts() == 3) {
                    userService.blockUser(temp_username);
                    userService.updateAttempt(temp_username, 0);
                    System.out.println("Your account is blocked...Contact Admin");
                    return;
                }
                System.out.println("Login details are wrong...Only " + (3 - user.getAttempts()) + " attempt left");
                String option = "";
                System.out.println("Wanna try again? (yes/no)");
                option = scan.nextLine();
                if (option.equals("yes") || option.equals("Yes")) {
                    System.out.println("Username : ");
                    temp_username = scan.nextLine();
                    System.out.println("Password : ");
                    temp_pass = scan.nextLine();
                } else if (option.equals("no") || option.equals("No"))
                    System.out.println("Going back to Home Dashboard");
                else {
                    System.out.println("Wrong input...going back to Home Dashboard");
                    return;
                }
            }
        }
        System.out.println("Your account is blocked...Contact Admin");
        return;
    }
}
