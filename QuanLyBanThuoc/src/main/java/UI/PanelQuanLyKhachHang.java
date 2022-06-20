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
import java.time.LocalDate;
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

import DAO.KhachHangDAO;
import Model.KhachHang;
import Model.NhanVien;
import Service.KhachHangService;

public class PanelQuanLyKhachHang extends JPanel implements ActionListener, MouseListener {

	private JPanel pKhachHang, pTTKhachHang, pDSKhachHang, pXuLy, pButton, pTimKiem;
	//
	private JTextField txtMa, txtTen, txtNamSinh, txtSoDT, txtDiaChi, txtEmail;
	private JComboBox<String> cbGioiTinh;
	//
	private DefaultTableModel tableModel;
	private JTable tbDSKhachHang;
	private JScrollPane scrollPane;
	//
	private JButton btnThem, btnCapNhat, btnXoa, btnLamMoi;
	//
	private JTextField txtTheoTen, txtTheoEmail, txtTheoSDT;
	private JComboBox<String> cbTheoGioiTinh;
	private JButton btnTim;
	//
	private List<KhachHang> khachHangs;
	private KhachHangService khachHangDAO = new KhachHangDAO();

	public PanelQuanLyKhachHang() {
		setLayout(new BorderLayout());
		khachHangs = new ArrayList<KhachHang>();

		taoPanelKhachHang();
		taoPanelXuLy();

		themListener();

		this.add(pKhachHang);
		this.add(pXuLy, BorderLayout.EAST);

		lamMoiUI();
	}

	public void taoPanelKhachHang() {
		taoPanelTTKhachHang();
		taoPanelDSKhachHang();

		pKhachHang = new JPanel();
		pKhachHang.setBackground(new Color(0, 128, 128));
		pKhachHang.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);

		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0.5;
		pKhachHang.add(pTTKhachHang, c);
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pKhachHang.add(pDSKhachHang, c);
	}

	public void taoPanelTTKhachHang() {
		JLabel lblMa, lblTen, lblNamSinh, lblEmail, lblSoDT, lblDiaChi;
		lblMa = new JLabel("Mã khách hàng:");
		lblMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblNamSinh = new JLabel("Năm sinh:");
		lblNamSinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblSoDT = new JLabel("Số điện thoại:");
		lblSoDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		txtMa = new JTextField(30);
		txtMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtMa.setEditable(false);
		txtTen = new JTextField(30);
		txtTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtNamSinh = new JTextField(30);
		txtNamSinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtEmail = new JTextField(30);
		txtEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtSoDT = new JTextField(30);
		txtSoDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtDiaChi = new JTextField(30);
		txtDiaChi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

//		String[] gioitinh = { "Nam", "Nữ" };
//		cbGioiTinh = new JComboBox<String>(gioitinh);
//		cbGioiTinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		pTTKhachHang = new JPanel();
		pTTKhachHang.setBackground(new Color(0, 128, 128));
		pTTKhachHang.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 5;
		c.insets = new Insets(5, 15, 5, 15);

		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblMa, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtMa, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblTen, c);
		c.gridx = 3;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtTen, c);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblNamSinh, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtNamSinh, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblEmail, c);
		c.gridx = 3;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtEmail, c);
		c.gridy = 2;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblSoDT, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtSoDT, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pTTKhachHang.add(lblDiaChi, c);
		c.gridx = 3;
		c.weightx = 1;
		c.weighty = 0;
		pTTKhachHang.add(txtDiaChi, c);
	}

	public void taoPanelDSKhachHang() {
		String[] column = { "Mã khách hàng", "Tên", "Năm sinh", "Email" ,"Số điện thoại","Địa chỉ" };
		tableModel = new DefaultTableModel(column, 35);

		tbDSKhachHang = new JTable(tableModel);
		tbDSKhachHang.setBackground(Color.white);
		tbDSKhachHang.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 14));
		tbDSKhachHang.setRowHeight(20);

		JTableHeader tableHeader = tbDSKhachHang.getTableHeader();
		tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		scrollPane = new JScrollPane(tbDSKhachHang);
		scrollPane.setBackground(new Color(173, 216, 230));
		scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Danh sách khách hàng", TitledBorder.LEFT,
				TitledBorder.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 16)));
		scrollPane.setMinimumSize(new Dimension(700, 380));

		pDSKhachHang = new JPanel();
		pDSKhachHang.setBackground(new Color(0, 128, 128));
		pDSKhachHang.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pDSKhachHang.setLayout(new GridLayout(1, 1));
		pDSKhachHang.add(scrollPane);
	}

	public void taoPanelXuLy() {
		taoPanelButton();
		taoPanelTimKiem();

		pXuLy = new JPanel();
		pXuLy.setBackground(new Color(0, 128, 128));
		pXuLy.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
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
		taoButtonChoPanelButton();

		pButton = new JPanel();
		pButton.setBackground(new Color(0, 128, 128));
		pButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pButton.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
		c.ipadx = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridy = 0;
		c.gridx = 0;
		pButton.add(btnThem, c);
		c.gridy = 1;
		pButton.add(btnCapNhat, c);
		c.gridy = 2;
		pButton.add(btnXoa, c);
		c.gridy = 3;
		pButton.add(btnLamMoi, c);
	}

	public void taoButtonChoPanelButton() {
		btnThem = new JButton("Thêm");
		btnThem.setPreferredSize(new Dimension(300, 30));
		btnThem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnThem.setBackground(Color.white);
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnCapNhat.setBackground(Color.white);
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnXoa.setBackground(Color.white);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi.setBackground(Color.white);
		btnLamMoi = new JButton("Reset");
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi.setBackground(Color.white);
	}

	public void taoPanelTimKiem() {
		JLabel lblTimKiem, lblTheoMa, lblTheoTen, lblTheoNamSinh, lblTheoEmail, lblTheoSDT;
		lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
		lblTimKiem.setOpaque(true);
		lblTimKiem.setBackground(new Color(173, 216, 230));
		lblTimKiem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		lblTheoTen = new JLabel("Theo tên:");
		lblTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoEmail = new JLabel("Theo Email:");
		lblTheoEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoSDT = new JLabel("Theo SĐT:");
		lblTheoSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		txtTheoTen = new JTextField(20);
		txtTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoEmail = new JTextField(20);
		txtTheoEmail.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoSDT = new JTextField(20);
		txtTheoSDT.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnTim.setBackground(Color.white);

		pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(0, 128, 128));
		pTimKiem.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		pTimKiem.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 5;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridy = 0;
		c.gridx = 0;
		pTimKiem.add(lblTimKiem, c);
		c.gridy = 1;
		pTimKiem.add(lblTheoTen, c);
		c.gridy = 4;
		pTimKiem.add(txtTheoTen, c);
		c.gridy = 5;
		pTimKiem.add(lblTheoEmail, c);
		c.gridy = 6;
		pTimKiem.add(txtTheoEmail, c);
		c.gridy = 7;
		pTimKiem.add(lblTheoSDT, c);
		c.gridy = 10;
		pTimKiem.add(txtTheoSDT, c);
		c.gridy = 11;
		pTimKiem.add(btnTim, c);
	}

	public void themListener() {
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		tbDSKhachHang.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi))
			lamMoiUI();
		else if (o.equals(btnThem))
			xuLyThemKH();
		else if (o.equals(btnCapNhat))
			xuLyCapNhat();
		else if (o.equals(btnXoa))
			xuLyXoa();
		else if (o.equals(btnTim))
			xuLyTim();
	}

	public void lamMoiUI() {
		lamMoiDSKH();
		lamMoiBangKH();
		lamMoiPanelTTKH();
		lamMoiPanelTimKiem();
	}

	public void lamMoiDSKH() {
		List<KhachHang> temp = khachHangDAO.layDanhSachKhachHang();
		khachHangs.clear();
		khachHangs.addAll(temp);
	}
	public void xoaRow() {
		((DefaultTableModel)tbDSKhachHang.getModel()).setRowCount(0);
	}

	public void lamMoiBangKH() {
		tableModel.getDataVector().removeAllElements();
		Vector<String> vector;
		for (KhachHang khachHang : khachHangs) {
			vector = new Vector<String>();
			vector.add(khachHang.getMaKH());
			vector.add(khachHang.getTenKH());
			vector.add(khachHang.getNamSinh() + "");
			vector.add(khachHang.getEmail());
			vector.add(khachHang.getSoDT());
			vector.add(khachHang.getDiaChi());
			tableModel.addRow(vector);
		}
		tbDSKhachHang.updateUI();
		tbDSKhachHang.clearSelection();
	}

	public void lamMoiPanelTTKH() {
		txtMa.setText("");
		txtTen.setText("");
		txtNamSinh.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");
		txtEmail.setText("");
	}

	public void lamMoiPanelTimKiem() {
		txtTheoTen.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
	}

	public void xuLyThemKH() {
		if (kiemTraDuLieu() == false)
			return;
		KhachHang khachHang = chuyenSangKhachHang();
		khachHang.setMaKH(khachHangDAO.setCodeEmployees());
		if (khachHang == null)
			return;
		if (khachHangDAO.themKhachHang(khachHang)) {
			JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
			lamMoiUI();
		} else
			JOptionPane.showMessageDialog(this, "Thêm khách hàng không thành công!");
	}

	public boolean kiemTraDuLieu() {
		if (kiemTraMaKH(txtMa.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không hợp lệ!");
			return false;
		} else if (txtTen.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không được bỏ trống!");
			return false;
		} else if(!KiemTraTheoTen(txtTen.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng định dạng không đúng!");
			return false;
		}		
		else if (txtNamSinh.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Năm sinh không được bỏ trống!");
			return false;
		} else if (kiemTraKieuSo(txtNamSinh.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Năm sinh phải là kiểu số!");
			return false;
		} else if (kiemTraNamSinh(txtNamSinh.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Năm sinh phải phải trước năm hiện tại và lớn hơn 1900!");
			return false;
		} else if (!txtSoDT.getText().trim().equals("") && kiemTraSDT(txtSoDT.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Số điện thoại có 10 chữ số!");
			return false;
		}
		return true;

	}

	public boolean kiemTraMaKH(String ma) {
		String reg = "^KH[0-9]{1,10}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(ma);
		return matcher.matches();
	}

	public Boolean KiemTraTheoTen(String ten) {
		String regexTen = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)$";
		Pattern pattern = Pattern.compile(regexTen);
		Matcher matcher = pattern.matcher(ten);
		return matcher.matches();
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ten = scan.nextLine();
		String regexTen = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)$";
//		Pattern pattern = Pattern.compile(regexTen);
//		Matcher matcher = pattern.matcher(ten);
		if(ten.matches(regexTen)==true) {
			System.out.println("true");
		} else System.out.println("false");
	}
	
	public boolean kiemTraKieuSo(String input) {
		String reg = "^[0-9]+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public boolean kiemTraNamSinh(String namsinh) {
		if (kiemTraKieuSo(namsinh) == false)
			return false;
		int namSinh = Integer.parseInt(namsinh);
		if (namSinh >= LocalDate.now().getYear() || namSinh < 1900)
			return false;
		return true;
	}

	public boolean kiemTraSDT(String sdt) {
		String regex = "^[0][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sdt);
		return matcher.matches();
	}

	public KhachHang chuyenSangKhachHang() {
		String ma = txtMa.getText().trim();
		String ten = txtTen.getText().trim();
		int namsinh = Integer.parseInt(txtNamSinh.getText().trim());
		String email = txtEmail.getText().trim();
		String sdt = txtSoDT.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		KhachHang khachHang = new KhachHang(ma, ten, namsinh, sdt, email, diachi);
		return khachHang;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbDSKhachHang.getSelectedRow();
		txtMa.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 0)));
		txtTen.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 1)));
		txtNamSinh.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 2)));
		txtEmail.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 3)));
		txtSoDT.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 4)));
		txtDiaChi.setText(String.valueOf(tbDSKhachHang.getValueAt(row, 5)));
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

	public void xuLyCapNhat() {
		if (tbDSKhachHang.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần cập nhật!");
			return;
		}
		if (kiemTraDuLieu() == false)
			return;
		KhachHang khachHang = chuyenSangKhachHang();
		//khachHang.setMaKH(khachHangDAO.setCodeEmployees());
		if (khachHang == null)
			return;
		if (khachHangDAO.suaKhachHang(khachHang)) {
			JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công!");
			lamMoiUI();
		} else
			JOptionPane.showMessageDialog(this, "Cập nhật khách hàng không thành công!");
	}

	public void xuLyXoa() {
		if (tbDSKhachHang.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
			return;
		}
		int xoa = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng","Thông báo",JOptionPane.YES_NO_OPTION);
		if(xoa == JOptionPane.YES_OPTION) {
			if (khachHangDAO.xoaKhachHang(txtMa.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
				lamMoiUI();
			} else
				JOptionPane.showMessageDialog(this, "Xóa khách hàng không thành công!");
		}
	}

	public void xuLyTim() {
		if (!txtTheoSDT.getText().trim().equals("") && kiemTraSDT(txtTheoSDT.getText().trim())==false && txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			return;
		}
		else if(!txtTheoSDT.getText().trim().equals("") && kiemTraSDT(txtTheoSDT.getText().trim())==true && txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals("")) {
			timTheoSDT(txtTheoSDT.getText().trim());
		}
		else if (txtTheoSDT.getText().trim().equals("") && KiemTraTheoTen(txtTheoTen.getText().trim())==false && !txtTheoTen.getText().trim().equals("") && txtTheoEmail.getText().trim().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Tên không hợp lệ!");
			return;
		}
		else if (!txtTheoTen.getText().trim().equals("") && txtTheoSDT.getText().trim().equals("") && KiemTraTheoTen(txtTheoTen.getText().trim())==true && txtTheoEmail.getText().trim().equals(""))
			timTheoTen(txtTheoTen.getText().trim());
		else if(txtTheoSDT.getText().trim().equals("") && kiemTraKieuSo(txtTheoEmail.getText().trim())==true && txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			return;
		}
		else if(txtTheoSDT.getText().trim().equals("") && kiemTraKieuSo(txtTheoEmail.getText().trim())==false && txtTheoTen.getText().trim().equals("") && !txtTheoEmail.getText().trim().equals("")) {
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
	public void LamMoi(List<KhachHang> dskh) {
		khachHangs.clear();
		khachHangs.addAll(dskh);
	}
	public void timTheoSDT(String sdt) {
		List<KhachHang> dskh = khachHangDAO.layDanhSachKhachHangTheoSDT(sdt);
		if(dskh != null) {
			xoaRow();
			LamMoi(dskh);
			lamMoiBangKH();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy khách hàng!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}
	public void timTheoTen(String ten) {
		List<KhachHang> dskh = khachHangDAO.layDanhSachKhachHangTheoTen(ten);
		if(!dskh.isEmpty()) {
			xoaRow();
			LamMoi(dskh);
			lamMoiBangKH();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy khách hàng!", "Thông báo",JOptionPane.CANCEL_OPTION);

	}
	public void timKiemTheoEmail(String email) {
		List<KhachHang> dskh = khachHangDAO.timKhachHangTheoMail(email);
		if(!dskh.isEmpty()) {
			xoaRow();
			LamMoi(dskh);
			lamMoiBangKH();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy khách hàng!", "Thông báo",JOptionPane.CANCEL_OPTION);

	}
//
//	public void timTheoMa() {
//		String maKH = txtTheoMa.getText().trim();
//		if (kiemTraMaKH(maKH) == false) {
//			JOptionPane.showMessageDialog(this, "Mã khách hàng không hợp lê!!");
//			return;
//		}
//		KhachHang khachHang = KhachHangDAO.timTheoMa(maKH);
//		if (khachHang == null) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng có mã: " + maKH);
//			return;
//		}
//		lamMoiDSKH();
//		lamMoiBangKH();
//		int index = khachHangs.indexOf(khachHang);
//		if (index != -1) {
//			tbDSKhachHang.setRowSelectionInterval(index, index);
//			fillForm();
//		}
//	}
//
//	public void timTheoTenGioiTinhNamSinhSoDT() {
//		String tenKH = txtTheoTen.getText().trim();
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenGioiTinhNamSinhSoDT(tenKH, gioiTinh, namSinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenGioiTinhNamSinh() {
//		String tenKH = txtTheoTen.getText().trim();
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenGioiTinhNamSinh(tenKH, gioiTinh, namSinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenGioiTinhSoDT() {
//		String tenKH = txtTheoTen.getText().trim();
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenGioiTinhSoDT(tenKH, gioiTinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenNamSinhSoDT() {
//		String tenKH = txtTheoTen.getText().trim();
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenNamSinhSoDT(tenKH, namSinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoGioiTinhNamSinhSoDT() {
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoGioiTinhNamSinhSoDT(gioiTinh, namSinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenGioiTinh() {
//		String tenKH = txtTheoTen.getText().trim();
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenGioiTinh(tenKH, gioiTinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenNamSinh() {
//		String tenKH = txtTheoTen.getText().trim();
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenNamSinh(tenKH, namSinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTenSoDT() {
//		String tenKH = txtTheoTen.getText().trim();
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTenSoDT(tenKH, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoGioiTinhNamSinh() {
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoGioiTinhNamSinh(gioiTinh, namSinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoGioiTinhSoDT() {
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoGioiTinhSoDT(gioiTinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoNamSinhSoDT() {
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//		String soDT = txtTheoSDT.getText().trim();
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoNamSinhSoDT(namSinh, soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoTen() {
//		String tenKH = txtTheoTen.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoTen(tenKH);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoGioiTinh() {
//		String gioiTinh = cbTheoGioiTinh.getSelectedItem() + "";
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoGioiTinh(gioiTinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoNamSinh() {
//		int namSinh = Integer.parseInt(txtTheoNamSinh.getText().trim());
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoNamSinh(namSinh);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void timTheoSoDT() {
//		String soDT = txtTheoSDT.getText().trim();
//
//		ArrayList<KhachHang> temp = KhachHangDAO.timTheoSoDT(soDT);
//		if (temp.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng thích hợp!");
//			return;
//		}
//		lamMoiBangKHKhiTim(temp);
//	}
//
//	public void lamMoiBangKHKhiTim(ArrayList<KhachHang> temp) {
//		lamMoiPanelTTKH();
//		khachHangs.clear();
//		khachHangs.addAll(temp);
//		lamMoiBangKH();
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
//		int index = tbDSKhachHang.getSelectedRow();
//		KhachHang khachHang = khachHangs.get(index);
//		txtMa.setText(khachHang.getMaKH());
//		txtTen.setText(khachHang.getTenKH());
//		txtNamSinh.setText(khachHang.getNamSinh() + "");
//		txtSoDT.setText(khachHang.getSoDT());
//		txtDiaChi.setText(khachHang.getDiaChi());
//		cbGioiTinh.setSelectedItem(khachHang.getGioiTinh());
//
//	}
}
