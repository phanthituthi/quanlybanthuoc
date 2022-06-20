package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DAO.NhaSanXuatDAO;
import Model.KhachHang;
import Model.NhaCungCap;
import Service.NhaSanXuatService;

public class PanelQuanLyNSX extends JPanel implements ActionListener, MouseListener {

	private JPanel pNSX, pTTNSX, pDSNSX, pXuLy, pButton, pTimKiem;

	//
	private JTextField txtMaNSX, txtTenNSX, txtSDT, txtDiaChi,txtEmail;
	private JComboBox<String> cboTrangThai;
	//
	private DefaultTableModel tableModel;
	private JTable tbDSNSX;
	private JScrollPane scrollPane;
	//
	private JButton btnThem, btnCapNhat, btnXoa, btnLamMoi;
	//
	private JTextField txtTheoEmail, txtTheoTen, txtTheoSDT;
	private JButton btnTim;
	//
	private List<NhaCungCap> nhaSanXuats;
	private NhaSanXuatService nhaSanXuatDao = new NhaSanXuatDAO();

	public PanelQuanLyNSX() {
		this.setLayout(new BorderLayout());
		nhaSanXuats = new ArrayList<NhaCungCap>();
		taoPanelNSX();
		taoPanelXuLy();

		this.add(pNSX, BorderLayout.CENTER);
		this.add(pXuLy, BorderLayout.EAST);

		themListener();
		lamMoiUI();
	}

	public void taoPanelNSX() {
		taoPanelTTNSX();
		taoPanelDSNSX();

		pNSX = new JPanel();
		pNSX.setBackground(new Color(0, 128, 128));
		pNSX.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridx = 0;
		c.gridy = 0;
		pNSX.add(pTTNSX, c);
		c.gridy = 1;
		pNSX.add(pDSNSX, c);
	}

	public void taoPanelTTNSX() {
		JLabel lblMaNSX, lblTenNSX, lblDiaChi, lblSDT, lblEmail, lblTrangThai;

		lblMaNSX = new JLabel("Mã NCC:");
		lblMaNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTenNSX = new JLabel("Tên NCC:");
		lblTenNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		

		txtMaNSX = new JTextField(30);
		txtMaNSX.setEditable(false);
		txtMaNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTenNSX = new JTextField(30);
		txtTenNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtDiaChi = new JTextField(30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtSDT = new JTextField(30);
		txtSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtEmail = new JTextField(30);
		txtEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		cboTrangThai.addItem("Dừng Hợp Tác");
		cboTrangThai.addItem("Hợp Tác");

		pTTNSX = new JPanel();
		pTTNSX.setBackground(new Color(0, 128, 128));
		pTTNSX.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 10;
		c.ipadx = 0;
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		pTTNSX.add(lblMaNSX, c);
		c.gridx = 1;
		c.weightx = 1;
		pTTNSX.add(txtMaNSX, c);
		c.gridx = 2;
		c.weightx = 0;
		pTTNSX.add(lblTenNSX, c);
		c.gridx = 3;
		c.weightx = 1;
		pTTNSX.add(txtTenNSX, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		pTTNSX.add(lblDiaChi, c);
		c.gridx = 1;
		c.weightx = 1;
		pTTNSX.add(txtDiaChi, c);
		c.gridx = 2;
		c.weightx = 0;
		pTTNSX.add(lblSDT, c);
		c.gridx = 3;
		c.weightx = 1;
		pTTNSX.add(txtSDT, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		pTTNSX.add(lblEmail, c);
		c.gridx = 1;
		c.weightx = 1;
		pTTNSX.add(txtEmail, c);
		c.gridx = 2;
		c.weightx = 0;
		pTTNSX.add(lblTrangThai, c);
		c.gridx = 3;
		c.weightx = 1;
		pTTNSX.add(cboTrangThai, c);

	}

	public void taoPanelDSNSX() {
		String[] column = { "Mã NCC", "Tên NCC", "Địa chỉ", "Sđt","Email","Trạng thái" };
		tableModel = new DefaultTableModel(column, 35);
		tbDSNSX = new JTable(tableModel);
		tbDSNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 14));
		tbDSNSX.setBackground(Color.white);
		tbDSNSX.setRowHeight(20);

		JTableHeader tableHeader = tbDSNSX.getTableHeader();
		tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		scrollPane = new JScrollPane(tbDSNSX, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(new Color(173, 216, 230));
		scrollPane.setMinimumSize(new Dimension(700, 450));
		scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Danh sách nhà sản xuất", TitledBorder.LEFT,
				TitledBorder.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 16)));

		pDSNSX = new JPanel();
		pDSNSX.setBackground(new Color(0, 128, 128));
		pDSNSX.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pDSNSX.setLayout(new GridLayout(1, 1));
		pDSNSX.add(scrollPane);

	}

	public void taoPanelXuLy() {
		taoPanelButton();
		taoPaneTimKiem();

		pXuLy = new JPanel();
		pXuLy.setBackground(new Color(0, 128, 128));
		pXuLy.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 10;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 0, 5);

		c.gridy = 0;
		c.gridx = 0;
		pXuLy.add(pButton, c);
		c.gridy = 1;
		pXuLy.add(pTimKiem, c);
	}

	public void taoPanelButton() {
		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.white);
		btnThem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(Color.white);
		btnCapNhat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.white);
		btnXoa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.white);
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		pButton = new JPanel();
		pButton.setBackground(new Color(0, 128, 128));
		pButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pButton.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 10;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridx = 0;
		c.gridy = 0;
		pButton.add(btnThem, c);
		c.gridy = 1;
		pButton.add(btnCapNhat, c);
		c.gridy = 2;
		pButton.add(btnXoa, c);
		c.gridy = 3;
		pButton.add(btnLamMoi, c);
	}

	public void taoPaneTimKiem() {
		JLabel lblTimKiem, lblTheoEmail, lblTheoTen, lblTheoSDT;
		lblTimKiem = new JLabel("Tìm Kiếm Nhà Cung Cấp", JLabel.CENTER);
		lblTimKiem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		lblTimKiem.setBackground(new Color(173, 216, 230));
		lblTimKiem.setOpaque(true);
//		lblTheoMa = new JLabel("Theo mã:");
//		lblTheoMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoTen = new JLabel("Theo tên:");
		lblTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoEmail = new JLabel("Theo Email:");
		lblTheoEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoSDT = new JLabel("Theo số điện thoại:");
		lblTheoSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

//		txtTheoMa = new JTextField(20);
//		txtTheoMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoTen = new JTextField(20);
		txtTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoEmail = new JTextField(20);
		txtTheoEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoSDT = new JTextField(20);
		txtTheoSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		
		btnTim = new JButton("Tìm");
		btnTim.setBackground(Color.white);
		btnTim.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(0, 128, 128));
		pTimKiem.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pTimKiem.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 10;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridx = 0;
		c.gridy = 0;
		pTimKiem.add(lblTimKiem, c);
		c.gridy = 1;
//		pTimKiem.add(lblTheoMa, c);
//		c.gridy = 2;
//		pTimKiem.add(txtTheoMa, c);
//		c.gridy = 3;
		pTimKiem.add(lblTheoTen, c);
		c.gridy = 4;
		pTimKiem.add(txtTheoTen, c);
		c.gridy = 5;
		pTimKiem.add(lblTheoEmail, c);
		c.gridy = 6;
		pTimKiem.add(txtTheoEmail, c);
		c.gridy = 7;
		pTimKiem.add(lblTheoSDT, c);
		c.gridy = 8;
		pTimKiem.add(txtTheoSDT, c);
		c.gridy = 9;
		pTimKiem.add(btnTim, c);
	}

	public void themListener() {
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		tbDSNSX.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi))
			lamMoiUI();
		else if (o.equals(btnThem))
			xuLyThemNSX();
		else if (o.equals(btnCapNhat))
			xuLyCapNhat();
		else if (o.equals(btnXoa))
			xuLyXoa();
		else if (o.equals(btnTim))
			xuLyTim();
	}

	public void lamMoiUI() {
		txtTheoEmail.setText("");
		txtTheoTen.setText("");
		txtTheoSDT.setText("");
		lamMoiPanelTTNSX();
		lamMoiDSNSX();
		lamMoiBangNSX();
	}

	public void lamMoiPanelTTNSX() {
		txtMaNSX.setText("");
		txtTenNSX.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		cboTrangThai.setSelectedItem("");
	}

	public void lamMoiDSNSX() {
		List<NhaCungCap> temp = nhaSanXuatDao.layDanhSachNSX();
		nhaSanXuats.clear();
		nhaSanXuats.addAll(temp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbDSNSX.getSelectedRow();
		if(row >=0) {
			txtMaNSX.setText(String.valueOf(tableModel.getValueAt(row, 0)));
			txtTenNSX.setText(String.valueOf(tableModel.getValueAt(row, 1)));
			txtDiaChi.setText(String.valueOf(tableModel.getValueAt(row, 2)));
			txtSDT.setText(String.valueOf(tableModel.getValueAt(row, 3)));
			txtEmail.setText(String.valueOf(tableModel.getValueAt(row, 4)));
			cboTrangThai.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 5)));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void lamMoiBangNSX() {
		tableModel.getDataVector().removeAllElements();
		Vector<String> vector;
		String tt= "Dừng hợp tác";
		for (NhaCungCap nhaSanXuat : nhaSanXuats) {
			vector = new Vector<String>();
			vector.add(nhaSanXuat.getMaNCC());
			vector.add(nhaSanXuat.getTenNCC());
			vector.add(nhaSanXuat.getDiaChi());
			vector.add(nhaSanXuat.getSdt());
			vector.add(nhaSanXuat.getEmail());
			if(nhaSanXuat.getTrangThai().equals("True")) {
				tt="Hợp tác";
			}
			vector.add(tt);
			tableModel.addRow(vector);
		}
		tbDSNSX.updateUI();
		tbDSNSX.clearSelection();
	}

	public void xuLyThemNSX() {
		if (kiemTraDuLieu() == false)
			return;
		
		txtMaNSX.setText(nhaSanXuatDao.setCodeEmployees());
		NhaCungCap nhaSanXuat = chuyenDoiSangNSX();
		
		boolean kq = nhaSanXuatDao.themNSX(nhaSanXuat);
		if (kq) {
			JOptionPane.showMessageDialog(this, "Thêm cung cấp mới thành công!");
			lamMoiUI();
		} else
			JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp mới không thành công!");
	}

	public boolean kiemTraDuLieu() {
		if (kiemTraMaNSX(txtMaNSX.getText()) == false) {
			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không hợp lệ!");
			return false;
		} else if (txtTenNSX.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được bỏ trống!");
			return false;
		}
		 else if (!kiemTraTenNCC(txtTenNSX.getText().trim())) {
				JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không đúng đinh dạng!");
				return false;
		}
		 else if (!kiemTraSDT(txtSDT.getText().trim())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng đinh dạng!");
				return false;
		}
		
		return true;
	}

	public boolean kiemTraMaNSX(String ma) {
		String reg = "^NCC[0-9]{3}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(ma);
		return matcher.matches();
	}
	
	public boolean kiemTraTenNCC(String ten) {
		String reg = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(ten);
		return matcher.matches();
	}

	public boolean kiemTraSDT(String sdt) {
		String regex = "^[0][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sdt);
		return matcher.matches();
	}
	
	public boolean kiemTraEmail(String email) {
		String regex = "^[a-zA-Z-][a-zA-Z0-9-]+@[a-zA-Z]+[.][a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ten = scan.nextLine();
		String regex = "^[a-zA-Z-][a-zA-Z0-9-]+@[a-zA-Z]+[.][a-zA-Z]+$";
			if(ten.matches(regex)==true) {
			System.out.println("true");
		} else System.out.println("false");
	}
	
	public NhaCungCap chuyenDoiSangNSX() {
		String mansx = txtMaNSX.getText().trim();
		String tennsx = txtTenNSX.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String sdt = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String tt = "False";
		if(cboTrangThai.getModel().getSelectedItem().toString().equals("Hợp tác")) {
			tt = "True";
		}
		NhaCungCap nhaSanXuat = new NhaCungCap(mansx, tennsx, diachi, sdt, email, tt);
		return nhaSanXuat;
	}

	public void xuLyCapNhat() {
		if (tbDSNSX.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà sản xuất cần cập nhật!");
			return;
		}

		int ans = JOptionPane.showOptionDialog(this, "Bạn có chắc chắn muốn cập nhật nhà sản xuất?",
				"Thông báo cập nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
		if (ans == JOptionPane.NO_OPTION)
			return;
		if (kiemTraDuLieu() == false)
			return;

		NhaCungCap nhaSanXuat = new NhaCungCap();
		nhaSanXuat.setMaNCC(txtMaNSX.getText().trim());
		nhaSanXuat.setTenNCC(txtTenNSX.getText());
		nhaSanXuat.setDiaChi(txtDiaChi.getText().trim());
		nhaSanXuat.setSdt(txtSDT.getText().trim());
		nhaSanXuat.setEmail(txtEmail.getText().trim());
		nhaSanXuat.setTrangThai("False");
		if(cboTrangThai.getModel().getSelectedItem().toString().equals("Hợp tác")) {
			nhaSanXuat.setTrangThai("True");
		}
		boolean kq = nhaSanXuatDao.suaNSX(nhaSanXuat);
		if (kq) {
			JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!");
			lamMoiUI();
		} else
			JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp không thành công!");
	}

	public void xuLyXoa() {
		if (tbDSNSX.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà sản xuất cần xóa!");
			return;
		}

		int ans = JOptionPane.showOptionDialog(this, "Bạn có chắc chắn muốn xóa nhà cung cấp?", "Xóa nhà cung cấp",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
		if (ans == JOptionPane.NO_OPTION)
			return;
		boolean kq = nhaSanXuatDao.xoaNSX(txtMaNSX.getText().trim());
		if (kq) {
			JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
			lamMoiUI();
		} else
			JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp không thành công!");
	}

	public void xuLyTim() {
		if (!txtTheoSDT.getText().trim().equals("") && kiemTraSDT(txtTheoSDT.getText().trim())==false && txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			return;
		}
		else if(!txtTheoSDT.getText().trim().equals("") && kiemTraSDT(txtTheoSDT.getText().trim())==true && txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals("")) {
			timTheoSDT(txtTheoSDT.getText().trim());
		}
		else if (txtTheoSDT.getText().trim().equals("") && kiemTraTenNCC(txtTheoTen.getText().trim())==false && !txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Tên không hợp lệ!");
			return;
		}
		else if (!txtTheoTen.getText().trim().equals("") && txtTheoSDT.getText().trim().equals("") && kiemTraTenNCC(txtTheoTen.getText().trim())==true && txtTheoEmail.getText().trim().equals(""))
			timTheoTen(txtTheoTen.getText().trim());
		else if(txtTheoSDT.getText().trim().equals("") && kiemTraEmail(txtTheoEmail.getText().trim())==false && txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			return;
		}
		else if(txtTheoSDT.getText().trim().equals("") && kiemTraEmail(txtTheoEmail.getText().trim())==true && txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			timKiemTheoEmail(txtTheoEmail.getText().trim());
		}
		else if(!txtTheoSDT.getText().trim().equals("") && !txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chỉ tìm kiếm theo số điện thoại hoặc tên hoặc email!");
			return;
		}
		else if(!txtTheoSDT.getText().trim().equals("") && !txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chỉ tìm kiếm theo số điện thoại hoặc tên!");
			return;
		}
		else if(!txtTheoSDT.getText().trim().equals("") && txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chỉ tìm kiếm theo số điện thoại hoặc email!");
			return;
		}
		else if(txtTheoSDT.getText().trim().equals("") && !txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Chỉ tìm kiếm theo email hoặc tên!");
			return;
		}
	}

	public void xoaRow() {
		((DefaultTableModel)tableModel).setRowCount(0);
	}
	
	public void LamMoi(List<NhaCungCap> dsncc) {
		nhaSanXuats.clear();
		nhaSanXuats.addAll(dsncc);
	}
	
	public void timTheoSDT(String sdt) {
		List<NhaCungCap> dsncc = nhaSanXuatDao.layDanhSachNSXTheoSDT(sdt);
		if(dsncc.isEmpty()) {
			xoaRow();
			LamMoi(dsncc);
			lamMoiBangNSX();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhà cung cấp!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}
	public void timTheoTen(String ten) {
		List<NhaCungCap> dsncc = nhaSanXuatDao.layDanhSachNSXTheoTen(ten);
		if(!dsncc.isEmpty()) {
			xoaRow();
			LamMoi(dsncc);
			lamMoiBangNSX();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhà cung cấp!", "Thông báo",JOptionPane.CANCEL_OPTION);

	}
	public void timKiemTheoEmail(String email) {
		List<NhaCungCap> dsncc = nhaSanXuatDao.timNSXTheoMail(email);
		if(!dsncc.isEmpty()) {
			xoaRow();
			LamMoi(dsncc);
			lamMoiBangNSX();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhà cung cấp!", "Thông báo",JOptionPane.CANCEL_OPTION);

	}
//	public void xuLyTimTheoMa() {
//		String ma = txtTheoMa.getText().trim();
//		if (kiemTraMaNSX(ma) == false) {
//			JOptionPane.showMessageDialog(this, "Mã nhà sản xuất không hợp lệ!");
//			return;
//		}
//		NhaCungCap nhaSanXuat = NhaSanXuatDAO.timTheoMa(ma);
//		if (nhaSanXuat == null) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy nhà sản xuất có mã: " + ma);
//			return;
//		}
//
//		lamMoiDSNSX();
//		lamMoiBangNSX();
//		int index = nhaSanXuats.indexOf(nhaSanXuat);
//		if (index != -1) {
//			tbDSNSX.setRowSelectionInterval(index, index);
//			fillForm();
//		}
//	}
//
//	public void xuLyTimTheoTenVaQuocGia() {
//		String ten = txtTheoTen.getText().trim();
//		String quocgia = txtTheoQuocGia.getText().trim();
//		ArrayList<NhaCungCap> temp = NhaSanXuatDAO.timTheoTenVaQuocGia(ten, quocgia);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy nhà sản xuất có tên: " + ten);
//			return;
//		}
//		lamMoiPanelTTNSX();
//		nhaSanXuats.clear();
//		nhaSanXuats.addAll(temp);
//		lamMoiBangNSX();
//	}
//
//	public void xuLyTimTheoTen() {
//		String ten = txtTheoTen.getText().trim();
//		ArrayList<NhaCungCap> temp = NhaSanXuatDAO.timTheoTen(ten);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy nhà sản xuất có tên: " + ten);
//			return;
//		}
//		lamMoiPanelTTNSX();
//		nhaSanXuats.clear();
//		nhaSanXuats.addAll(temp);
//		lamMoiBangNSX();
//	}
//
//	public void xuLyTimTheoQuocGia() {
//		String quocgia = txtTheoQuocGia.getText().trim();
//		ArrayList<NhaCungCap> temp = NhaSanXuatDAO.timTheoQuocGia(quocgia);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy nhà sản xuất thuộc quốc gia: " + quocgia);
//			return;
//		}
//		lamMoiPanelTTNSX();
//		nhaSanXuats.clear();
//		nhaSanXuats.addAll(temp);
//		lamMoiBangNSX();
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		fillForm();
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void fillForm() {
//		int selectedrow = tbDSNSX.getSelectedRow();
//		NhaCungCap nhaSanXuat = nhaSanXuats.get(selectedrow);
//		txtMaNSX.setText(nhaSanXuat.getMaNSX());
//		txtTenNSX.setText(nhaSanXuat.getTenNSX());
//		txtQuocGiaSX.setText(nhaSanXuat.getQuocGiaSX());
//		txtCoSoSX.setText(nhaSanXuat.getCoSoSX());
//	}
}
