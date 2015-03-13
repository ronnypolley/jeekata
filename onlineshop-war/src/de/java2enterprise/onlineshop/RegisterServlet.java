package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String[] autogenKeys = new String[] { "id" };
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO customer (email, password) VALUES (?,?)",
				autogenKeys);

		statement.setString(1, customer.getEmail());
		statement.setString(2, customer.getPassword());

		statement.executeQuery();

		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			customer.setId(resultSet.getLong(1));
		}

		connection.close();
	}

}
