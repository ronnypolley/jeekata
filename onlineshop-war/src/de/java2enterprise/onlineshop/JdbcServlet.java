package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcServlet
 */
// Servlet defined in web.xml
// @WebServlet("/db_settings")
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JdbcServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String jdbcPropertiesLocation = getInitParameter("jdbc_properties");

		InputStream jdbcPropertiesFile = request.getServletContext()
				.getResourceAsStream(jdbcPropertiesLocation);
		Properties jdbcProperties = new Properties();
		jdbcProperties.load(jdbcPropertiesFile);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println("<br>driver: " + jdbcProperties.getProperty("driver"));
		out.println("<br>url: " + jdbcProperties.getProperty("url"));
		out.println("<br>username: " + jdbcProperties.getProperty("username"));
		out.println("<br>password: " + jdbcProperties.getProperty("password"));
		out.println("</body>");
		out.println("</html>");
	}

}
