package lv.javaguru.java2.console.database.ORM;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

abstract class ORMRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }
}
