package de.java2enterprise.onlineshop;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Customer;
import de.java2enterprise.onlineshop.model.Item;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/sell")
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 5 * 5)
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServlet() {
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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			InputStream inputStream = fotoPart.getInputStream();
			int i = 0;
			while ((i = inputStream.read()) != -1) {
				baos.write(i);
			}
		} catch (IOException e) {
			throw new ServletException(e.getMessage());
		}

		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");

		if (customer != null) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String price = request.getParameter("price");

			Item item = new Item();
			item.setTitle(title);
			item.setDescription(description);
			item.setPrice(Double.valueOf(price));
			item.setSeller(customer);
			item.setFoto(scale(baos.toByteArray()));
			baos.flush();

			try {
				persist(item);
			} catch (SQLException e) {
				throw new ServletException(e.getMessage());
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		}
	}

	private byte[] scale(byte[] foto) throws IOException {
		ByteArrayInputStream inputStream2 = new ByteArrayInputStream(foto);
		BufferedImage unscaledFoto = ImageIO.read(inputStream2);
		int height = unscaledFoto.getHeight();
		int width = unscaledFoto.getWidth();

		double transformationFactor = 400.0 / (width > height ? width : height);

		BufferedImage scaledFoto = new BufferedImage((int) Math.round(width
				* transformationFactor), (int) Math.round(height
				* transformationFactor), BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics = scaledFoto.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		AffineTransform transform = AffineTransform.getScaleInstance(
				transformationFactor, transformationFactor);
		graphics.drawRenderedImage(unscaledFoto, transform);

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		ImageIO.write(scaledFoto, "PNG", result);
		return result.toByteArray();
	}

	private void persist(Item item) throws SQLException {
		String[] autoKey = new String[] { "id" };
		Connection connection = ds.getConnection();
		PreparedStatement statment = connection
				.prepareStatement(
						"INSERT into item (title, description, price, foto, seller_id) VALUES (?,?,?,?,?)",
						autoKey);

		statment.setString(1, item.getTitle());
		statment.setString(2, item.getDescription());
		statment.setDouble(3, item.getPrice());
		statment.setBytes(4, item.getFoto());
		statment.setLong(5, item.getSeller().getId());

		statment.executeUpdate();

		ResultSet keys = statment.getGeneratedKeys();
		while (keys.next()) {
			item.setId(keys.getLong(1));
		}

		connection.close();
	}

}
