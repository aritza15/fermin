package pakete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import clases.Course;
import clases.Instructor;
import clases.InstructorDetail;
import clases.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

// create session
		Session session = factory.openSession();

		try {

// start a transaction
			session.beginTransaction();

// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

// create some courses
			Course tempCourse1 = new Course("Blues Guitar - The Ultimate Guide");
			Course tempCourse2 = new Course("Selfish");

//add some reviews
			tempCourse1.addReview(new Review("Great course... loved it!!"));
			tempCourse1.addReview(new Review("Best...absolutely loved it!!"));
			tempCourse2.addReview(new Review("Great"));
			tempCourse2.addReview(new Review("Best in the world!!!"));
			tempCourse1.addReview(new Review("Awesome"));

// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);

// save the courses and ... leverage the cascade all
			System.out.println("saving the courses");
			System.out.println(tempCourse1);
			System.out.println(tempCourse2);
			System.out.println(tempCourse1.getReviews());
			System.out.println(tempCourse2.getReviews());
			session.save(tempCourse1);
			session.save(tempCourse2);

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
