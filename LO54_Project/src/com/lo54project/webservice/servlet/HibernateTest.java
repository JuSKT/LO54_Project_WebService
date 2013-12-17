package com.lo54project.webservice.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Client;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.model.CourseSession;
import com.lo54project.webservice.model.Location;

/**
 * Servlet implementation class HibernateTest
 */
@WebServlet("/hibernatetest")
public class HibernateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HibernateTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        
        Location l = new Location("lalalala");
        Course c = new Course("AG", "Frfrfrfr");
        CourseSession cs = new CourseSession(new Date(), new Date());  
        cs.setCrs(c);
        cs.setLoc(l);
        Client cl = new Client("dede", "frfr", "frfr", "56327876", "ezfzefzef@kuik.mp");
        cl.setCrss(cs);
        
        session.save(l);
        session.save(c);
        session.save(cs);
        session.save(cl);
        
        session.getTransaction().commit();
        session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
