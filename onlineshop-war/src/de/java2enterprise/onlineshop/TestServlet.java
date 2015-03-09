package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestSerlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/__default")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		response.setContentType("text/html;charset=UTF-8");
		writer.println("<!DOCTYPE html>");
		writer.println("<html><body>");

		try {
			Connection connection = ds.getConnection();
			if (connection.isValid(10)) {
				writer.println("<br>Connected!");
			}
			connection.close();
		} catch (SQLException e) {
			writer.println(e.getMessage());
		}
		writer.println("<br> Test finished!");

		writer.println("</body></html>");
	}

}
