package quiz.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import quiz.models.Question;
import quiz.models.Test;
import quiz.service.QuestionService;
import quiz.service.TestService;
import quiz.util.HibernateUtil;

public class QuestionServiceImpl implements QuestionService{
    SessionFactory factory = HibernateUtil.getSessionFactory();
    TestService testService = new TestServiceImpl();
    @Override
    public void createQuestion(String testcode, Question question) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Test test = (Test) session.get(Test.class, testcode);
        if(test.getQuesions()==null)
        {
            Set<Question> questions = new HashSet<Question>();
            questions.add(question);
            test.setQuesions(questions);
            session.persist(test);
        }
        else
        {
            Set<Question> questions = test.getQuesions();
            questions.add(question);
            test.setQuesions(questions);
            session.persist(test);
        }
        session.persist(question.getAnswer());
        session.persist(question);
        transaction.commit();
        session.close();
    }
    @Override
    public Question getQuestion(Long id) {
        Session session = factory.openSession();
        Question quesion = (Question)session.get(Question.class, id);
        return quesion;    
    }
    @Override
    public void updateQuestion(Question question) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Question ques = (Question) session.get(Question.class, question.getQuesId());
        if(question.getQuestion()!="")
            ques.setQuestion(question.getQuestion());
        if(question.getOption1()!="")
            ques.setOption1(question.getOption1());
        if(question.getOption2()!="")
            ques.setOption2(question.getOption2());
        if(question.getOption3()!="")
            ques.setOption3(question.getOption3());
        if(question.getOption4()!="")
            ques.setOption4(question.getOption4());
        if(question.getAnswer().getAnswer()!=0)
            ques.setAnswer(question.getAnswer());
        transaction.commit();
        session.close();
        System.out.println("Deails are updated/n");
    }
    @Override
    public void deleteQuestion(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Question ques = (Question) session.get(Question.class, id);
        session.remove(ques);
        transaction.commit();
        session.close();
        
    }
    @Override
    public void printQuestion(Question question) {
        System.out.println(String.format("Q_id %-2d: %-120s", question.getQuesId(),question.getQuestion()));
        System.out.println(String.format("1. %-55s\t2.%-55s", question.getOption1(),question.getOption2()));
        System.out.println(String.format("3. %-55s\t4.%-55s", question.getOption3(),question.getOption4()));
    }
    
}
