//package net.school.schoolApp.repository;
//
//import net.school.schoolApp.entity.Grade;
//import net.school.schoolApp.entity.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//@Repository
//@Transactional
//public class UserDaoService {
//
//   @Autowired
//   private SessionFactory sessionFactory;
//
//   @PersistenceContext
//   private EntityManager entityManager;
//
//   public long insert(User user) {
//      entityManager.persist(user);
//      return user.getId();
//   }
//
//   public void createUser(User user) {
//      Session session = null;
//
//      try {
//         session = sessionFactory.openSession();
//         session.beginTransaction();
//         Long id = (Long) session.save(user);
//         System.out.println("Grade created with ID:: " + id);
//         session.getTransaction().commit();
//      }
//      catch (Exception e) {
//         e.printStackTrace();
//      }
//
//   }
//
//}
