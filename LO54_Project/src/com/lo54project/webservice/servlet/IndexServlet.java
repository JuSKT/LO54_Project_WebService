package com.lo54project.webservice.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lo54project.webservice.handler.CourseHandler;
import com.lo54project.webservice.handler.CourseSessionHandler;
import com.lo54project.webservice.handler.LocationHandler;
import com.lo54project.webservice.hibernate.util.HibernateUtil;
import com.lo54project.webservice.model.Client;
import com.lo54project.webservice.model.Course;
import com.lo54project.webservice.model.CourseSession;
import com.lo54project.webservice.model.Location;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/** Servlet implementation class IndexServlet */
public class IndexServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public IndexServlet() 
    {
        super();
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		request.setAttribute("locations", LocationHandler.parseLocations());
		
		try 
		{
			request.setAttribute("coursesessions", CourseSessionHandler.parseCourseSessions());
		} 
		catch (UniformInterfaceException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ClientHandlerException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ParseException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			request.setAttribute("courses", CourseHandler.parseCourse());
		} 
		catch (UniformInterfaceException e) 
		{
			e.printStackTrace();
		} 
		catch (ClientHandlerException e) 
		{
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}
}