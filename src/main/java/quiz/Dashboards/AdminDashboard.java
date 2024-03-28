package quiz.Dashboards;
import java.util.Scanner;
import quiz.models.User;

public class AdminDashboard {
    User user;
    AdminDashboard(){}
    AdminDashboard(User user)
    {
        this.user = user;
    }
    Scanner scan = new Scanner(System.in);
    AdminOperations adminOperations = new AdminOperations();
    public void DashBoard() {
        System.out.println("------Admin Dashboard------\n");
        while (true) {
            System.out.println("1. Create Test");
            System.out.println("2. Show Tests");
            System.out.println("3. Update Test Name");
            System.out.println("4. Delete Test");
            System.out.println("5. Add Question");
            System.out.println("6. Show All Question");
            System.out.println("7. Update Question");
            System.out.println("8. Delete Question");
            System.out.println("9. Unblock Student");
            System.out.println("10. Reset Student Password");
            System.out.println("11. Delete Student");
            System.out.println("12. Change admin password");
            System.out.println("13. Exit");
            int select = this.scan.nextInt();
            switch (select) {
                case 1: {
                    adminOperations.createTest();
                }
                break;
                case 2: {
                    adminOperations.getAllTests();
                }
                break;
                case 3: {
                    adminOperations.updateTestName();
                }
                break;
                case 4: {
                    adminOperations.deleteTest();
                }
                break;
                case 5: {
                    adminOperations.addQuestion();
                }
                break;
                case 6: {
                    adminOperations.showAllQuestions();
                }
                break;
                case 7: {
                    adminOperations.updateQuestion();
                }
                break;
                case 8: {
                    adminOperations.deleteQuestion();
                }
                break;
                case 9: {
                    adminOperations.unblockStudent();
                }
                break;
                case 10: {
                    adminOperations.addQuestion();
                }
                break;
                case 11: {
                    adminOperations.addQuestion();
                }
                break;
                case 12: {
                    adminOperations.addQuestion();
                }
                break;
                case 13: {
                    return;
                }
                default:
                    System.out.println("Invalid Input...");
                    break;
            }
        }
    }
}
