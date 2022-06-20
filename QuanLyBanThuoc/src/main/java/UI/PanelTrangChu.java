package UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTrangChu extends JPanel {

	private JLabel lblAnh;

	public PanelTrangChu(JFrame frame) {
		setLayout(new GridLayout(1, 1));
		setBackground(new Color(173, 216, 230));

		BufferedImage imageBufferedImage = null;
		try {
			imageBufferedImage = ImageIO.read(new File("hinh/medicine.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = imageBufferedImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);

		lblAnh = new JLabel(icon);
		this.add(lblAnh);
	}

}
