package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SiginServlet
 */
@WebServlet("/signinwithcookies")
public class SiginServletWithCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiginServletWithCookie() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String cookieEmail = null;
		String cookiePassword = null;

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>Cookie-Name</td>");
		out.println("<td>Cookie-Value</td>");
		out.println("</tr>");

		for (Cookie cookie : request.getCookies()) {
			out.println("<tr>");
			out.println("<td>" + cookie.getName() + "</td>");
			out.println("<td>" + cookie.getValue() + "</td>");
			out.println("</tr>");

			if (cookie.getName().equals("email")) {
				cookieEmail = cookie.getValue();
			} else if (cookie.getName().equals("password")) {
				cookiePassword = cookie.getValue();
			}
		}
		out.println("</tr>");

		out.println("</table>");

		if (email.equals(cookieEmail) && password.equals(cookiePassword)) {
			out.println("Benutzer ist valide!");
		} else {
			out.println("Benutzer ist nicht valide");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
