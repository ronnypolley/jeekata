package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RevisionServlet
 */
@WebServlet("/revision")
public class RevisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RevisionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		createRemote(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		createRemote(request, response);
	}

	private void createRemote(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String remoteAddr = request.getRemoteAddr();
		log("Remote address is " + remoteAddr);

		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		out.println("Remote address: " + remoteAddr);
		out.println("</body></html>");
	}

}
