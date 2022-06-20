package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiaLogAbout extends JDialog {

	private JPanel pTitle, pAnh, pChinh;

	public DiaLogAbout(Frame owner) {
		super(owner);
		this.setTitle("Thông tin về phần mềm");
		taoPanelTitle();
		taoPanelAnh();
		taoPanelChinh();

		this.add(pTitle, BorderLayout.NORTH);
		this.add(pAnh, BorderLayout.WEST);
		this.add(pChinh, BorderLayout.CENTER);

		this.setResizable(false);
		this.setSize(800, 290);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void taoPanelTitle() {
		pTitle = new JPanel();
		pTitle.setBackground(new Color(173, 216, 230));
		pTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pTitle.setLayout(new GridLayout(1, 1));

		JLabel lblTitle = new JLabel("Phần mềm quản lý nhà thuốc Hoàng Kim", JLabel.CENTER);
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		pTitle.add(lblTitle);
	}

	public void taoPanelAnh() {
		pAnh = new JPanel();
		pAnh.setBackground(Color.white);
		pAnh.setPreferredSize(new Dimension(270, 200));

		BufferedImage imageBufferedImage = null;
		try {
			imageBufferedImage = ImageIO.read(new File("hinh/JPG1493351807.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = imageBufferedImage.getScaledInstance(270, 200, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);

		JLabel lblAnh = new JLabel(icon);
		pAnh.add(lblAnh);
	}

	public void taoPanelChinh() {
		pChinh = new JPanel();
		pChinh.setBackground(Color.white);
		pChinh.setLayout(new GridBagLayout());

		JLabel lbl1, lbl2, lbl3;
		lbl1 = new JLabel("Những người thực hiện: ");
		lbl1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lbl2 = new JLabel("Võ Quốc Khánh-18058521");
		lbl2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lbl3 = new JLabel("Trương Tuấn Phúc-18058511");
		lbl3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);

		c.gridx = 0;
		c.gridy = 0;
		pChinh.add(lbl1, c);
		c.gridx = 1;
		pChinh.add(lbl2, c);
		c.gridx = 0;
		c.gridy = 1;
		pChinh.add(Box.createRigidArea(new Dimension(0, 0)));
		c.gridx = 1;
		pChinh.add(lbl3, c);

	}
}
