package quiz.serviceimpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import quiz.models.Role;
import quiz.models.User;
import quiz.service.UserService;
import quiz.util.HibernateUtil;

public class UserServiceImpl implements UserService
{
    SessionFactory factory = HibernateUtil.getSessionFactory();
    // Open a new session
    @Override
    public boolean createUser(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User temp_user =(User) session.get(User.class, user.getUsername());
        if(temp_user==null)
        {
            // role is alread preset or not
            Role userRole = session.createQuery("FROM Role WHERE roleName = :name", Role.class)
                        .setParameter("name", "Student")
                        .uniqueResult();
            if (userRole == null)
            {
                userRole = new Role();
                userRole.setRoleName("Student");
                session.persist(userRole);
            }
            user.setRole(userRole);
            session.persist(user);
            transaction.commit();
            session.close();
            return true;
        }
        System.out.println("Username already existed!");
        return false;
    }
    @Override
    public User getUser(String username) {
        Session session = factory.openSession();
        User user = (User) session.get(User.class, username);
        session.close();
        return user;
    }
    @Override
    public void updateAttempt(String username, Integer count) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, username);
        user.setAttempts(count);
        session.persist(user);
        transaction.commit();
        session.close();
    }
    @Override
    public void blockUser(String username) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, username);
        user.setEnabled(false);
        session.persist(user);
        transaction.commit();
        session.close();
    }
    @Override
    public void unblockUser(String username) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, username);
        user.setEnabled(true);
        transaction.commit();
        session.close();
    }
    @Override
    public void deleteUser(String username) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, username);
        session.remove(user);
        transaction.commit();
        session.close();
    }
    @Override
    public void updateBasicDeails(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User temp = (User) session.get(User.class, user.getUsername());
        if(user.getName()!="")
            temp.setName(user.getName());
        if(user.getEmail()!="")
            temp.setEmail(user.getEmail());
        if(user.getMob()!="")
            temp.setMob(user.getMob());
        transaction.commit();
        session.close();
        System.out.println("Deails are updated/n");
    }
    @Override
    public void updatePassword(String username, String new_password) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, username);
        user.setPassword(new_password);
        transaction.commit();
        session.close();
    }

}
