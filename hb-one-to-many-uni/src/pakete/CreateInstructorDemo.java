package pakete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import clases.Course;
import clases.Instructor;
import clases.InstructorDetail;
import clases.Review;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

// create session
		Session session = factory.getCurrentSession();

		try {

// create the objects			
			Instructor tempInstructor = new Instructor("Bearded", "Bahun", "bhandari@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

// start a transaction
			session.beginTransaction();

// save the instructor
//
// Note: this will ALSO save the details object
// because of CascadeType.ALL
//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {

// add clean up code

			factory.close();
		}
	}

}
