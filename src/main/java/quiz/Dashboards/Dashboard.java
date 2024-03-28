package quiz.Dashboards;

import java.util.Scanner;

public class Dashboard {
    Scanner scan = new Scanner(System.in);
    DashboardOperations ao = new DashboardOperations();
    public void dashBoard()
    {
        System.out.println("------Welcome to Exam Portal------\n");
        while(true)
        {
            System.out.println("1. Registration");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int select = this.scan.nextInt();
            switch (select) {
                case 1:
                {
                    ao.registration();
                }    
                break;
                case 2:
                {
                    ao.login();
                }
                break;
                case 3:
                {
                    return;
                }
                default: System.out.println("Invalid Input...");
                break;
            }
        }
    }
}
