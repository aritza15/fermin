package pakete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import clases.Course;
import clases.Instructor;
import clases.InstructorDetail;
import clases.Review;

public class DeleteCourseAndReviewDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

// create session
		Session session = factory.openSession();

		try {

// start a transaction
			session.beginTransaction();

// get a course
			int theId = 11;
			Course tempCourse = session.get(Course.class, theId);

// delete course
			System.out.println("Deleting course: " + tempCourse);

			session.delete(tempCourse);

// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {

// add clean up code
			session.close();

			factory.close();
		}
	}

}