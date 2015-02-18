package de.java2enterprise.onlineshop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
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
@WebServlet(urlPatterns = { "/sell" }, asyncSupported = true)
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 30, fileSizeThreshold = 1024 * 1024, location = "/tmp")
public class SellServletASync extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServletASync() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AsyncContext asyncContext = request.startAsync();
		asyncContext.start(new FotoService(asyncContext));
	}

	private static class FotoService implements Runnable {

		private AsyncContext ac;

		public FotoService(AsyncContext ac) {
			this.ac = ac;
		}

		@Override
		public void run() {
			HttpServletRequest request = (HttpServletRequest) ac.getRequest();
			HttpServletResponse response = (HttpServletResponse) ac
					.getResponse();

			PrintWriter outWriter = null;
			FileOutputStream out = null;
			try {
				Part fotoPart = request.getPart("foto");

				InputStream in = fotoPart.getInputStream();
				File file = new File("C:/tmp/"
						+ fotoPart.getSubmittedFileName().substring(
								fotoPart.getSubmittedFileName().lastIndexOf(
										'\\')));
				out = new FileOutputStream(file);
				outWriter = response.getWriter();

				byte[] b = new byte[1024];
				int i = 0;

				while ((i = in.read(b)) != -1) {
					out.write(b, 0, i);
				}
				out.flush();
				outWriter.write("true");
				ac.complete();
			} catch (Exception e) {
				outWriter.write("false");
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
}
