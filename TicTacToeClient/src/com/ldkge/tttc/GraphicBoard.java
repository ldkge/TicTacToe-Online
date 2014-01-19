package com.ldkge.tttc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicBoard extends JPanel {
	private BufferedImage image;
	/**
	 * Create the panel.
	 */
	public GraphicBoard() {
		try {                
			image = ImageIO.read(new File("board.png"));
		} 
		catch (IOException ex) {

		}
		setOpaque(false);
		setBounds(0, 0, 500, 500);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}


}
