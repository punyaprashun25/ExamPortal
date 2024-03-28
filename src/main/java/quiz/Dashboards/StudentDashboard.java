package quiz.Dashboards;
import java.util.Scanner;

import java.util.List;
import quiz.models.Test;
import quiz.models.User;
import quiz.service.TestService;
import quiz.serviceimpl.TestServiceImpl;

public class StudentDashboard {
    User user;
    StudentDashboard(){}
    StudentDashboard(User user)
    {
        this.user = user;
    }
    Scanner scan = new Scanner(System.in);
    StudentOperations so = new StudentOperations();
    TestService ts = new TestServiceImpl();
    public void DashBoard()
    {
        System.out.println("------Student Dashboard------\n");
        while(true)
        {
            System.out.println("1. Show Available Tests");
            System.out.println("2. Reports");
            System.out.println("3. Update details");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");
            int select = this.scan.nextInt();
            switch (select) {
                case 1:
                {
                    List<Test> test = ts.getAllTests();
                    for(int i = 0; i<test.size(); i++)
                    {
                        System.out.print(i+1+". ");
                        System.out.println(test.get(i).getTestName());
                    }
                    System.out.println("Select test no. to start the test - ");
                    int opt = scan.nextInt();
                    if(opt>0 && opt<=test.size())
                    {
                        ts.startTest(test.get(opt-1).getTestName(),test.get(opt-1).getQuesions());
                    }
                    else
                    {
                        System.out.println("Invalid input!!...going back to Dashboard!");
                    }
                }    
                break;
                case 2:
                {
                    // so.show_reports();
                }
                break;
                case 3:
                {
                    // so.update_details();
                }
                break;
                case 4:
                {
                    // so.change_pass();
                }
                break;
                case 5:
                {
                    return;
                }
                default: System.out.println("Invalid Input...");
                break;
            }
        }
    }
}
