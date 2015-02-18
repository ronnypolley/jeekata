package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.java2enterprise.onlineshop.model.Customer;

/**
 * Servlet implementation class SiginServlet
 */
@WebServlet("/signinwithsession")
public class SiginServletWithSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiginServletWithSession() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		response.setContentType("text/html;charset=UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		if (email.equals(customer.getEmail())
				&& password.equals(customer.getPassword())) {
			out.println("Benutzer ist valide!");
		} else {
			out.println("Benutzer ist nicht valide");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
