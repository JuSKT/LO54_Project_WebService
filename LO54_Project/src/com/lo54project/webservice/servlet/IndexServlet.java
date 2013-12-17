package com.lo54project.webservice.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lo54project.webservice.handler.CourseHandler;
import com.lo54project.webservice.handler.LocationHandler;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/** Servlet implementation class IndexServlet */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** @see HttpServlet#HttpServlet() */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response, "index");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "subContent");
	}
	
	
	/**
	 * @see HttpServlet#doProcess(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doProcess(HttpServletRequest request,HttpServletResponse response,String forwardTo) 
			throws ServletException, IOException {
		String url= "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		LocationHandler lh = new LocationHandler(url);
		CourseHandler ch = new CourseHandler(url);
		
		request.setAttribute("locations", lh.parseLocations());
		try {
			request.setAttribute("courses", ch.parseCourses());
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/"+forwardTo+".jsp").forward(request,response);

	}
	
	
}