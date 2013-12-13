package com.lo54project.webservice.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo54project.webservice.handler.CourseSessionHandler;


/**
 * Servlet implementation class RegisterFormServlet
 */
@WebServlet("/registerForm")
public class RegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=null;
		String url= "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		CourseSessionHandler csh = new CourseSessionHandler(url);
		for (Cookie c : request.getCookies()){
			if(c.getName().equals("courseSessionsList")){
				s=URLDecoder.decode(c.getValue(), "UTF-8");
				break;
			}
		}
		s=s.replace('\\',' ');
		s=s.replace('"',' ');
		
		ObjectMapper mapper = new ObjectMapper();
		request.setAttribute("courseSessions", csh.parseCourseSessionsById(mapper.readValue(s,int[].class)));

		getServletContext().getRequestDispatcher("/registerForm.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
