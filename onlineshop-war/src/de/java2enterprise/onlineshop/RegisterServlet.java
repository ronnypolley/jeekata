package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	DataSource ds;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		Customer customer = new Customer();
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));

		try {
			persist(customer);
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void persist(Customer customer) throws SQLException {
		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();

		statement
		.executeUpdate("INSERT INTO customer (email, password) VALUES ( '"
				+ customer.getEmail()
				+ "', '"
				+ customer.getPassword()
				+ "')");
		connection.close();
	}

}
