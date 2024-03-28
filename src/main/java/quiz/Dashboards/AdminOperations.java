package quiz.Dashboards;
import java.util.Scanner;
import java.util.List;

import quiz.models.Answer;
import quiz.models.Question;
import quiz.models.Test;
import quiz.models.User;
import quiz.service.QuestionService;
import quiz.service.TestService;
import quiz.service.UserService;
import quiz.serviceimpl.QuestionServiceImpl;
import quiz.serviceimpl.TestServiceImpl;
import quiz.serviceimpl.UserServiceImpl;

public class AdminOperations {
    Scanner scan = new Scanner(System.in);
    TestService testService = new TestServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    UserService userService = new UserServiceImpl();
    public void createTest() {
        System.out.println("Test Code : ");
        String testCode = scan.nextLine();
        System.out.println("Test Name : ");
        String testName = scan.nextLine();
        Test test = new Test();
        test.setQuesions(null);
        test.setTestCode(testCode);
        test.setTestName(testName);
        testService.createTest(test);
        System.out.println("Test is created successfully");
    }
    public void getAllTests()
    {
        List<Test> tests = testService.getAllTests();
        System.out.println("-----------All Test Details---------");
        for(Test t : tests)
        {
            System.out.println("Test Code : "+t.getTestCode()+" Test Name : "+t.getTestName());
        }
        System.out.println("-----------------------------------");
        return;
    }

    public void updateTestName() {
        System.out.println("Test code : ");
        String temp_testCode = scan.nextLine();
        while (testService.getTest(temp_testCode) == null) {
            System.out.println("Test code invalid...");
            System.out.println("Wanna try again? (yes/no)");
            String choice = scan.nextLine();
            if (choice.equals("yes") || choice.equals("Yes")) {
                System.out.println("Test code : ");
                temp_testCode = scan.nextLine();
            }
            else
                return;
        }
        System.out.println("New Test name");
        String new_testName = scan.nextLine();
        testService.updateTestName(temp_testCode, new_testName);
        System.out.println("test name updated");
        return;
    }
    public void deleteTest()
    {
        System.out.println("Test code : ");
        String testCode = scan.nextLine();
        Test test = testService.getTest(testCode);
        if(test==null)
        {
            System.out.println("Testcode is not valid...try again");
            return;
        }
        testService.deleteTest(testCode);
    }
    public void addQuestion()
    {
        System.out.println("Test code : ");
        String testCode = scan.nextLine();
        Test test = testService.getTest(testCode);
        while(test==null)
        {
            System.out.println("Test code is invalid...");
            System.out.println("Press y to try again or x to exit");
            String choice = scan.nextLine();
            if(choice.equals("y") || choice.equals("Y"))
            {
                System.out.println("Test code : ");
                testCode = scan.nextLine();
                test = testService.getTest(testCode);
            }
            else if(choice.equals("x") || choice.equals("X"))
                return;
            else
            {
                System.out.println("Invalid input...going back");
                return;
            }
        }
        System.out.println("Question : ");
        String ques = scan.nextLine();
        System.out.println("Option1 : ");
        String opt1 = scan.nextLine();
        System.out.println("Option2 : ");
        String opt2 = scan.nextLine();
        System.out.println("Option3 : ");
        String opt3 = scan.nextLine();
        System.out.println("Option4 : ");
        String opt4 = scan.nextLine();
        System.out.println("Answer : ");
        int ans = scan.nextInt();
        // creating question
        Question question = new Question();
        question.setQuestion(ques);
        question.setOption1(opt1);
        question.setOption2(opt2);
        question.setOption3(opt3);
        question.setOption4(opt4);
        
        Answer answer = new Answer();
        answer.setAnswer(ans);

        question.setAnswer(answer);

        questionService.createQuestion(testCode, question);
        System.out.println("Question is added to the Test : "+test.getTestName());
        return;
    }
    public void showAllQuestions()
    {
        System.out.println("Test code : ");
        String testcode = scan.nextLine();
        Test test = testService.getTest(testcode); 
        if(test!=null)
        {
            System.out.println("----------------------------------------------"+test.getTestName()+"Test----------------------------------------------\n");
            for(Question q : testService.getAllQuestions(testcode))
            {
                questionService.printQuestion(q);
                System.out.println(String.format("Answer : %-50s\n", q.getAnswer().getAnswer()));
            }

        }
        else
        {
            System.out.println("Test code is invalid....");
            return;
        }
    }
    public void updateQuestion()
    {
        System.out.println("Question_id : ");
        Long ques_id = scan.nextLong();
        Question temp = questionService.getQuestion(ques_id);
        while(temp==null)
        {
            System.out.println("Question_id is invalid...");
            System.out.println("Press y to try again or x to exit");
            String choice = scan.nextLine();
            if(choice.equals("y") || choice.equals("Y"))
            {
                System.out.println("Question_id : ");
                ques_id = scan.nextLong();
                temp = questionService.getQuestion(ques_id);
            }
            else if(choice.equals("x") || choice.equals("X"))
                return;
            else
            {
                System.out.println("Invalid input...going back");
                return;
            }
        }
        scan.nextLine();
        System.out.println("Question : ");
        String ques = scan.nextLine();
        System.out.println("Option1 : ");
        String opt1 = scan.nextLine();
        System.out.println("Option2 : ");
        String opt2 = scan.nextLine();
        System.out.println("Option3 : ");
        String opt3 = scan.nextLine();
        System.out.println("Option4 : ");
        String opt4 = scan.nextLine();
        System.out.println("Answer : ");
        int ans = scan.nextInt();
        // creating question
        temp.setQuestion(ques);
        temp.setOption1(opt1);
        temp.setOption2(opt2);
        temp.setOption3(opt3);
        temp.setOption4(opt4);
        
        Answer answer = new Answer();
        answer.setAnswer(ans);

        temp.setAnswer(answer);

        questionService.updateQuestion(temp);
        System.out.println("Question is updated to the Test : ");
        return;
    }
    public void deleteQuestion()
    {
        System.out.println("Question_id : ");
        Long ques_id = scan.nextLong();
        Question temp = questionService.getQuestion(ques_id);
        while(temp==null)
        {
            System.out.println("Question_id is invalid...");
            System.out.println("Press y to try again or x to exit");
            String choice = scan.nextLine();
            if(choice.equals("y") || choice.equals("Y"))
            {
                System.out.println("Question_id : ");
                ques_id = scan.nextLong();
                temp = questionService.getQuestion(ques_id);
            }
            else if(choice.equals("x") || choice.equals("X"))
                return;
            else
            {
                System.out.println("Invalid input...going back");
                return;
            }
        }
        questionService.deleteQuestion(ques_id);
        System.out.println("Question is deleted successfully");
        return;
    }
    public void unblockStudent()
    {
        System.out.println("Username : ");
        String username = scan.nextLine();
        User temp = userService.getUser(username);
        while(temp==null)
        {
            System.out.println("Username is invalid...");
            System.out.println("Press y to try again or x to exit");
            String choice = scan.nextLine();
            if(choice.equals("y") || choice.equals("Y"))
            {
                System.out.println("Question_id : ");
                username = scan.nextLine();
                temp = userService.getUser(username);
            }
            else if(choice.equals("x") || choice.equals("X"))
                return;
            else
            {
                System.out.println("Invalid input...going back");
                return;
            }
        }
        userService.unblockUser(username);
        System.out.println("User is unblocked successfully");
        return;
    }

}
