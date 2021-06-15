package net.school.schoolApp;

import net.school.schoolApp.entity.Grade;
import net.school.schoolApp.entity.Learner;
import net.school.schoolApp.entity.Subject;
import net.school.schoolApp.entity.User;
import net.school.schoolApp.repository.GradeRepository;
import net.school.schoolApp.repository.LearnerRepository;
import net.school.schoolApp.repository.SubjectRepository;
import net.school.schoolApp.service.GradeService;
import net.school.schoolApp.service.LearnerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
public class SchoolApp implements CommandLineRunner {

//@SpringBootApplication
//public class SchoolApp {

//	@Autowired
//	private UserDaoService userDaoService;

//	@Autowired
//	LearnerService learnerService;
//
//	@Autowired
//	private GradeRepository gradeRepository;

//	private SessionFactory sessionFactory;
//
//	public SessionFactory getSessionFactory() {
//		return getSessionFactory();
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	public Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	public void createGrade(Grade grade) {
//		getSession().beginTransaction();
//		getSession().save(grade);
//		getSession().getTransaction().commit();
//	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String host = "localhost";
		int port = 8080;

		// set up logging
		java.util.logging.Logger logger1 = Logger.getLogger("Status Logger");
		logger1.setLevel(Level.SEVERE);

		// in case we need to check the status
		Supplier<String> status = () -> {
			int timeout = 1000;
			try (Socket socket = new Socket()) {
				socket.connect(new InetSocketAddress(host, port), timeout);
				return "up";
			} catch (IOException e) {
				return "down";
			}
		};

		try {

			logger1.log(Level.INFO, status);
			//throw new IOException();
		} catch (Exception e) {
			logger1.log(Level.SEVERE, status);
		}


//		System.out.println("REALLY GETTING THERE...");
//
//		Learner learner = new Learner("Andre", "Vermeulem", "andre@gmail.com", 12);
//		Grade grade  = new Grade("Grade 50");
//
//		if (grade == null) {
//			System.out.println("GRADE === NULL...");
//		} else {
//			System.out.println("GRADE IS NOT == NULL...");
//			System.out.println("GRADE: " + grade);
//			//learner.setGrade(grade);
//		}
//
//		grade.getLearners().add(learner);
		//learner.setGrade(grade);

//		System.out.println("LEARNER: " + learner);
//		System.out.println("GRADE: " + grade);

//		learnerService.addLearner(learner);

		//Session session = new HibernateUtilConfi;
		//SessionFactory sessionFactory = session.getSessionFactory();

//		User user = new User("USER1", "User");
//		userDaoService.createUser(user);
//
//		System.out.println("THE MAIN METHOD IS WORKING...");
//		List<Subject> subjectList = new ArrayList<>();
////		Learner learner = new Learner(3L, "Andre", "Vermeulem", "andre@gmail.com", 12, subjectList);
////		Learner learner2 = new Learner(4L, "Simpra", "Xhakane", "sim@gmail.com", 20, subjectList);
//
//		Learner learner = learnerService.getLearnerById(3L);
//		Learner learner2 = learnerService.getLearnerById(4L);
//
////		Grade grade = new Grade();
////		grade.setGradeName("New grade");
//		Grade grade = gradeRepository.getGradeById(12L);
//
//		grade.getLearners().add(learner);
//		grade.getLearners().add(learner2);
//
//		gradeRepository.updateGrade(12L, grade);


//		Subject subject1 = new Subject("Mathematics");
//		Subject subject2 = new Subject("English");
//		Subject subject3 = new Subject("Biology");
//
//		learner.getSubjects().add(subject1);
//		learner.getSubjects().add(subject2);
//		learner.getSubjects().add(subject3);
//
//		//System.out.println(learner);
//		this.learnerRepository.save(learner);

//		User user = new User("James", "user");
//		long insert = userDaoService.insert(user);
//		System.out.println("New user is created: ");
//		System.out.println(user);


	}
}
