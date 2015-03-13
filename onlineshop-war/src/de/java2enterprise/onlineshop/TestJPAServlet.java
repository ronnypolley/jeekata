package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestJPAServlet
 */
@WebServlet("/TestJPAServlet")
public class TestJPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charste=UTF-8");

		PrintWriter writer = response.getWriter();

		writer.println("<!DOCTYPE html>");
		writer.println("<html><body>");

		try {
			writer.print("Connection is valid:" + em.isOpen());
		} catch (Exception e) {
			writer.println(e.getMessage());
		}

		writer.println("<br>Test finsished!</body></html>");
	}

}
