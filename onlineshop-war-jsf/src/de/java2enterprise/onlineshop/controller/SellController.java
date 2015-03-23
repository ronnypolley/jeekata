package de.java2enterprise.onlineshop.controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import de.java2enterprise.onlineshop.model.Customer;
import de.java2enterprise.onlineshop.model.Item;

@Named
@RequestScoped
public class SellController implements Serializable {

	private static final long serialVersionUID = 1002757825928083377L;

	public static final int MAX_IMAGE_LENGTH = 400;

	private static final Logger log = Logger.getLogger(SellController.class
			.getName());

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction ut;

	private Part part;

	@Inject
	private Item item;

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void persit(SigninController signinController) {
		try {
			ut.begin();
			EntityManager em = emf.createEntityManager();
			InputStream input = part.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
			item.setFoto(scale(output.toByteArray()));

			Customer customer = signinController.getCustomer();
			customer = em.find(Customer.class, customer.getId());

			item.setSeller(customer);
			em.persist(item);
			ut.commit();

			log.info("Offered item: " + item);

			FacesContext.getCurrentInstance().addMessage(
					"sellForm",
					new FacesMessage("Succesfully saved item",
							"You offered the item " + item));
		} catch (Exception e) {
			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(
					"sellForm",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							e.getMessage(), e.getCause().getMessage()));
		}

	}

	private byte[] scale(byte[] byteArray) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteArray);
		BufferedImage originalBufferedImage = ImageIO
				.read(byteArrayInputStream);

		double width = originalBufferedImage.getWidth();
		double height = originalBufferedImage.getHeight();

		double relevantLength = width > height ? width : height;

		double scale = MAX_IMAGE_LENGTH / relevantLength;
		int tWidth = (int) Math.round(scale * width);
		int tHeight = (int) Math.round(scale * height);

		BufferedImage bufferedImage = new BufferedImage(tWidth, tHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		AffineTransform affineTransform = AffineTransform.getScaleInstance(
				scale, scale);
		g2d.drawRenderedImage(originalBufferedImage, affineTransform);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "PNG", outputStream);

		return outputStream.toByteArray();
	}

}
