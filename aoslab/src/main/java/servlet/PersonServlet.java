package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAL.PersonRepository;
import model.Person;

public class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("index.jsp").forward(request, response);

		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Person person = new Person();
		person.setName(name);
		person.setEmail(email);

		PersonRepository repo = new PersonRepository("Person");

		person = repo.createOrUpdate(person);

		response.getWriter().write("<html>" + "<body>" + "id: " + person.getId() + "</body>" + "</html>");
		response.getWriter().flush();
		
		
		request.setAttribute("id_it_test", person.getId());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}