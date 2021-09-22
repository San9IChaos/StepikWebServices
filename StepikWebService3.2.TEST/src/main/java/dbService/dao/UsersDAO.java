package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UsersDAO {

    private final Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet getUserById(long id) throws HibernateException {
        return session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUserByName(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return (UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public long getUserIdByName(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
    }

    public List<UsersDataSet> readAll() throws HibernateException {

        CriteriaQuery<UsersDataSet> criteriaQuery = session.getCriteriaBuilder().createQuery(UsersDataSet.class);
        criteriaQuery.from(UsersDataSet.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}