package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class SigninServlet
 */
@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
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

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = null;

		try {
			customer = find(email, password);
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		}

		if (customer != null) {
			request.getSession().setAttribute("customer", customer);
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	private Customer find(String email, String password) throws SQLException {

		Connection connection = ds.getConnection();
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement
				.executeQuery("SELECT id, email, password from customer where email = '"
						+ email + "' and password = '" + password + "'");

		if (resultSet.next()) {
			Customer customer = new Customer();

			customer.setId(resultSet.getLong("id"));
			customer.setEmail(resultSet.getString("email"));
			customer.setPassword(resultSet.getString("password"));

			return customer;
		}
		connection.close();

		return null;
	}

}
