package de.java2enterprise.onlineshop.controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceUnit
	private EntityManagerFactory emf;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			Query query = emf.createEntityManager().createQuery(
					"SELECT i.foto FROM Item i WHERE i.id = :id");
			query.setParameter("id", Long.valueOf(id));

			byte[] foto = (byte[]) query.getSingleResult();
			response.reset();
			response.getOutputStream().write(foto);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

}
