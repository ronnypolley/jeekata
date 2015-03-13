package de.java2enterprise.onlineshop;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class FotoServlet
 */
@WebServlet("/foto")
public class FotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT foto from item where id = ?");

			statement.setLong(1, Long.parseLong(id));
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				byte[] foto = rs.getBytes(1);
				ByteArrayInputStream in = new ByteArrayInputStream(foto);

				int length = foto.length;

				final int bufferSize = 256;
				byte[] buffer = new byte[bufferSize];

				ServletOutputStream os = response.getOutputStream();

				while ((length = in.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				in.close();
				os.close();
				foto = null;
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		}

	}

}
