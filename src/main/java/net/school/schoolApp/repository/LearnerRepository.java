package net.school.schoolApp.repository;

import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
   // Get all learners by grade
//   List<Learner> findAllByGradeId(Long id);
//
//   List<Learner> findAllByGradeGradeNameIgnoreCase(String name);
//
//   @Query(value = "SELECT ln FROM learner ln JOIN ln.grade g WHERE g.id=?1")
//   List<Learner> getByGradeId(@Param("id") Long id);

//   Learner addSubject(Long subjectId, Learner learner);

//   boolean attendLesson(Long learnerId, Long lessonId);
//   boolean removeLearnerSubject(Long subjectId);

//   @Query(value = "SELECT s FROM subject s JOIN s.learners ls WHERE l.id=?1")
   //List<Subject> getSubjects(Long id);


//   public final static String product_ordered ="Select p from Product p Join p.orderDetail od Where od.id = :id";
//   @Query(product_ordered)
//   public List<Learner> findById(@Param("id") int id);


 //  @Query("SELECT b FROM Book b INNER JOIN b.categories c WHERE c IN (?1)")
//   List<Book> findByCategories(Collection<Category> categories);

//   @Query("SELECT b FROM Book b INNER JOIN b.categories c WHERE c IN (:categories)")
//   List<Book> findByCategories(@Param("categories") Collection<Category> categories);


//   @Query("select b from Book b where b.publisher.idd = ?1")
//   Book findByPublisherId(int id);
//
//   @Query("select b from Book b where b.publisher.idd <> ?1")
//   Book findByDifferentPublisherId(int id);


   ////In BookRepository
  // Get publisher's books
  // findBooksByBookPublishersPublisherId(Long publisherId)
  // Get books not published by publisher
  // findBooksByBookPublishersPublisherIdNot(Long publisherId)


   //   @Query("SELECT p FROM Person p LEFT JOIN FETCH p.moviesActor WHERE size(p.moviesActor) > 0");
//   List<Person> findActors1();
//
//   // Or
//
//   @Query("SELECT p FROM Person p JOIN FETCH p.moviesActor");
//   List<Person> findActors2();

//   @Query("SELECT p FROM Person p  JOIN  p.moviesActor movie");

//   @Query("select b from Blog b join fetch b.tags where b.name = :name")
//   Blog getBlog(@Param("name") String blogName);

//   select distinct distributor
//   from Distributor distributor
//   join distributor.towns town
//   join town.district district
//   where district.name = :name


//   @Query("SELECT u FROM OAuthUser as u JOIN u.roles as r WHERE r.id = ?1 AND u.isEnabled = true")







}

