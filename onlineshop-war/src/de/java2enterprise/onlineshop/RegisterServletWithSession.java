package de.java2enterprise.onlineshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.java2enterprise.onlineshop.model.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerwithsession")
public class RegisterServletWithSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer(request.getParameter("email"),
				request.getParameter("password"));
		request.getSession().setAttribute("customer", customer);

		request.getRequestDispatcher("index.html").forward(request, response);

	}
}
