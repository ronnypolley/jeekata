package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class BuyServlet
 */
@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
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
		long itemId = Long.parseLong(request.getParameter("item_id"));
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		try {
			update(itemId, customer);
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("search.jsp");
		dispatcher.forward(request, response);
	}

	private void update(long itemId, Customer customer) throws SQLException {
		Connection connection = ds.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("UPDATE item set buyer_id = ?, sold = CURRENT_TIMESTAMP where id = ?");

		statement.setLong(1, customer.getId());
		statement.setLong(2, itemId);

		statement.executeUpdate();

		connection.close();
	}
}
