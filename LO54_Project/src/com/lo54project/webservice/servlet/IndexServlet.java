package com.lo54project.webservice.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lo54project.webservice.handler.CourseHandler;
import com.lo54project.webservice.handler.CourseSessionHandler;
import com.lo54project.webservice.handler.LocationHandler;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		LocationHandler lh = new LocationHandler();
		request.setAttribute("locations", lh.parseLocations());
		
		CourseSessionHandler csh = new CourseSessionHandler();
		try {
			request.setAttribute("coursesessions", csh.parseCourseSessions());
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CourseHandler ch = new CourseHandler();
		request.setAttribute("courses", ch.parseCourses());
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}
}