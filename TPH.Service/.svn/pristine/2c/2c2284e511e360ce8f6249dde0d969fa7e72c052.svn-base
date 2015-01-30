package com.artica.telesalud.tph.server.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.artica.telesalud.tph.lightweightmodel.HallazgoData;

/**
 * Esta clase procesa la imagen del muñeco y le pone los números
 * 
 * @author Sebastian
 * 
 */
public class ProcesarImagen {

	/**
	 * Este método arma la imagen con el listado de hallazgos y la devuelve
	 * 
	 * @param hallazgos
	 * @return
	 */
	public File processImage(ArrayList<HallazgoData> hallazgos, String urlBody) {
		File finalImage = null;
		try {
			BufferedImage bi = ImageIO.read(new File(urlBody));
			BufferedImage buffer = new BufferedImage(bi.getWidth(),
					bi.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = (Graphics2D) buffer.getGraphics();
			graphic.drawImage(bi, 0, 0, null);
			graphic.setColor(Color.RED);
			graphic.setFont(new Font("Arial", Font.BOLD, 15));
			for (HallazgoData hallazgo : hallazgos) {
				graphic.drawString(hallazgo.getOrden() + "", hallazgo.getX().intValue(),
						hallazgo.getY().intValue());
			}
			// finalImage = ImageIO.getCacheDirectory();
			finalImage = File.createTempFile("img", ".png");
			ImageIO.write(buffer, "PNG", finalImage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return finalImage;
	}

}