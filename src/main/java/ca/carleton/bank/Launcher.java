package ca.carleton.bank;

import ca.carleton.bank.entity.QUser;
import ca.carleton.bank.entity.User;
import ca.carleton.bank.persistence.HibernateUtil;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Some fiddling with liquibase, hibernate and querydsl.
 */
public class Launcher {

    public static void main(final String[] args) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        final User admin = new User();
        admin.setUserName("admin12");
        admin.setPassword("password21");

        try {
            session.save(admin);
            transaction.commit();
        } catch (final Exception exception) {
            exception.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        // lets try getting it back from the database.
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
        final HibernateQuery query = new HibernateQuery(session);
        final QUser user = QUser.user;

        final List<User> users = query.from(user)
                .where(user.userName.isNotNull())
                .list(user);

        transaction.commit();
        session.close();

        users.forEach(System.out::println);


    }

}
