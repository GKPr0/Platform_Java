package cz.tul.data;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UsersDao {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    public Session session() {
        return sessionFactory.getObject().getCurrentSession();
    }

    public void create(User user) {
        session().save(user);
    }

//    public boolean exists(String username) {
//        CriteriaQuery<User> crit = session().getCriteriaBuilder().createQuery(User.class);
//        crit.add(Restrictions.idEq(username));
//        User user = crit.uniqueResult();
//        return user != null;
//    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return session().createQuery("from User").list();
        //Criteria crit = session().createCriteria(User.class);
        //return crit.list();
    }

    public void deleteUsers() {
        session().createQuery("delete from User").executeUpdate();
    }
}
