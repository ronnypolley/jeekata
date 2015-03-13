package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Item;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
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
		String searchString = request.getParameter("search");

		try {
			List<Item> foundItems = find(searchString);
			if (foundItems != null) {
				request.getSession().setAttribute("items", foundItems);
			}
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("search.jsp");
		dispatcher.forward(request, response);
	}

	private List<Item> find(String searchString) throws SQLException {
		Connection connection = ds.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("SELECT id, title, description, price, seller_id, buyer_id, sold FROM item WHERE title like ?");
		if (searchString != null) {
			statement.setString(1, "%" + searchString + "%");
		} else {
			statement.setString(1, searchString);
		}

		ResultSet resultSet = statement.executeQuery();

		List<Item> resultList = new ArrayList<Item>();

		while (resultSet.next()) {
			Item item = new Item(resultSet.getString("title"),
					resultSet.getString("description"),
					resultSet.getDouble("price"),
					resultSet.getLong("seller_id"));
			item.setBuyerId(resultSet.getLong("buyer_id"));
			item.setId(resultSet.getLong("id"));
			Timestamp timestamp = resultSet.getTimestamp("sold");
			if (timestamp != null) {
				item.setTraded(new Date(timestamp.getTime()));
			}
			resultList.add(item);
		}

		connection.close();
		return resultList;
	}

}
