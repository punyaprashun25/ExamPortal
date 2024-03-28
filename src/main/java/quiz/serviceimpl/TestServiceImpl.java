package quiz.serviceimpl;

import java.util.Set;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quiz.models.Question;
import quiz.models.Test;
import quiz.service.TestService;
import quiz.util.HibernateUtil;

public class TestServiceImpl implements TestService {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Scanner scan = new Scanner(System.in);
    @Override
    public void createTest(Test test) {
        Session session = factory.openSession();
        Test temp_test = (Test) session.get(Test.class, test.getTestCode());
        if (temp_test == null) {
            Transaction transaction = session.beginTransaction();
            session.persist(test);
            transaction.commit();
            session.close();
        }
        else
        {
            System.out.println("Test code already exists...try again with another test code\n");
        }
    }

    @Override
    public void updateTestName(String testcode, String testname) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Test test = (Test) session.get(Test.class, testcode);
        test.setTestName(testname);
        transaction.commit();
        session.close();
    }

    @Override
    public Test getTest(String testcode) {
        Session session = factory.openSession();
        Test test = (Test) session.get(Test.class, testcode);
        return test;
    }

    @Override
    public List<Test> getAllTests() {
        Session session = factory.openSession();
        List<Test> tests = session.createQuery("FROM Test", Test.class).getResultList();
        return tests;
    }

    @Override
    public void deleteTest(String testcode) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Test test =(Test) session.get(Test.class, testcode);
        session.remove(test);
        System.out.println("Test code : "+test.getTestCode()+" Test name : "+test.getTestName()+" is deleted successfully.");
        transaction.commit();
        session.close();
    }
    
    @Override
    public Set<Question> getAllQuestions(String testcode) {
        Session session = factory.openSession();
        return session.get(Test.class, testcode).getQuesions();
    }

    @Override
    public void startTest(String testName, Set<Question> question) {
        if(question.size()==0)
        {
            System.out.println("No questions available!");
            return;
        }
        int count = 0;
        int score = 0;
        System.out.println("----------------------------------------------"+testName+" test is started --------------------------------------------------------");
        for(Question ques : question)
        {
            System.out.print("Q."+(count+1)+" ");
            System.out.println(String.format("%-120s", ques.getQuestion()));
            System.out.println(String.format("1. %-55s\t2.%-55s", ques.getOption1(),ques.getOption2()));
            System.out.println(String.format("3. %-55s\t4.%-55s", ques.getOption3(),ques.getOption4()));
            System.out.print("Enter your answer : ");
            int ans = scan.nextInt();
            while(!(ans>=1 && ans<=4))
            {
                System.out.println("Invalid input");
                System.out.println("Enter a valid option : ");
                ans = scan.nextInt();
            }
            if(ans==ques.getAnswer().getAnswer())
            {
                score++;
            }
            count++;
            System.out.println("");
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Your score : "+score+" out of "+question.size());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }

}
