package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UsersDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(User user) {
        session().save(user);
    }

    public boolean exists(String username) {
        Criteria crit = session().createCriteria(User.class);
        crit.add(Restrictions.idEq(username));
        User user = (User) crit.uniqueResult();
        return user != null;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        //return session().createQuery("from User").list();
        Criteria crit = session().createCriteria(User.class);
        return crit.list();
    }

    public void deleteUsers() {
        session().createQuery("delete from User").executeUpdate();
    }
}
