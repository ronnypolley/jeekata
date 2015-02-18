package de.java2enterprise.onlineshop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/sellsync")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 30, fileSizeThreshold = 1024 * 1024, location = "/tmp")
public class SellServletSync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServletSync() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Part fotoPart = request.getPart("foto");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		response.setContentType("text/html;charset=UTF-8");

		InputStream in = fotoPart.getInputStream();
		FileOutputStream out = null;

		try {
			File file = new File("C:/tmp/"
					+ fotoPart.getSubmittedFileName().substring(
							fotoPart.getSubmittedFileName().lastIndexOf('\\')));
			out = new FileOutputStream(file);

			byte[] b = new byte[1024];
			int i = 0;

			while ((i = in.read(b)) != -1) {
				out.write(b, 0, i);
			}

		} catch (Exception e) {
			log("error", e);
		} finally {
			in.close();
			out.close();
		}

		PrintWriter outWriter = response.getWriter();

		outWriter.println("<!DOCTYPE html>");
		outWriter.println("<html>");
		outWriter.println("<body>");
		outWriter.println("<br>Gespeicherter Artikel: " + title);
		outWriter.println("<br>Beschreibung: " + description);
		outWriter.println("<br>Preis: " + price);
		outWriter.println("<br>Bild: " + fotoPart.getSubmittedFileName());
		outWriter.println("</body>");
		outWriter.println("</html>");
	}
}
