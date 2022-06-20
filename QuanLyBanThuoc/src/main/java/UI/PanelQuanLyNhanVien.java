package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import DAO.DinhDangDate;
import DAO.NhanVienDAO;
import Model.NhanVien;
import Service.NhanVienService;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class PanelQuanLyNhanVien extends JPanel implements ActionListener, MouseListener {
	private String checkdate = "";
	private UtilDateModel jdnamsinh = new UtilDateModel();
	private UtilDateModel jdtimns = new UtilDateModel();
	private DefaultTableModel model;
	private JTable table;
	private JLabel lbmanv, lbtennv, lbnamsinh, lbsdt, lbemail, lbcmnd, lbdiachi, lbchucvu, lbmatkhau, lbtrangthai;
	private JPasswordField txtmatkhau;
	private JTextField txtmanv, txttennv, txtnamsinh, txtsdt, txtemail, txtcmnd, txtmk, txtdiachi, txttim, txtmess,
			txttimns, txttimsdt,txtcmnd2;
	private JButton btnthem, btnsua, btnxoa, btnxoatrang, btntim;
	private JComboBox<String> cbchucvu, cbtrangthai, cbtim, cbtimgt;
	private JPanel contentPane;
	private NhanVienService nhanVienDao = new NhanVienDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelQuanLyNhanVien frame = new PanelQuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanelQuanLyNhanVien() {
//		setTitle("Quáº£n lÃ½ nhÃ¢n viÃªn");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setResizable(false);
//		setSize(1900, 1000);
//
//		JMenuBar menuBar = new JMenuBar();
//		menuBar.setBackground(new Color(220, 220, 220));
//		setJMenuBar(menuBar);
//
//		JMenu mnTrangChu = new JMenu("Trang chá»§");
//		mnTrangChu.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		menuBar.add(mnTrangChu);
//
//		JMenu mnQunL = new JMenu("Quáº£n lÃ½");
//		mnQunL.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		menuBar.add(mnQunL);
//
//		JMenuItem mntmBnHng = new JMenuItem("BÃ¡n hÃ ng");
//		mntmBnHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		mnQunL.add(mntmBnHng);
//		mntmBnHng.addActionListener(this);
//
//		JMenuItem mntmThuc = new JMenuItem("Thuá»‘c");
//		mntmThuc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		mnQunL.add(mntmThuc);
//
//		JMenuItem mntmKhchHng = new JMenuItem("KhÃ¡ch hÃ ng");
//	mntmKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		mnQunL.add(mntmKhchHng);
//
//		JMenuItem mntmNhnVin = new JMenuItem("NhÃ¢n viÃªn");
//		mntmNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		mnQunL.add(mntmNhnVin);
//
//		JMenuItem mntmNhSnXut = new JMenuItem("NhÃ  sáº£n xuáº¥t");
//		mntmNhSnXut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		mnQunL.add(mntmNhSnXut);
//
//		JMenu mnThngK = new JMenu("Thá»‘ng kÃª");
//		mnThngK.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		menuBar.add(mnThngK);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		this.setBackground(new Color(0, 128, 128));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));

		JPanel pw = new JPanel();
		pw.setBackground(new Color(0, 128, 128));
		Box bw = Box.createVerticalBox();
		Box bw1 = Box.createHorizontalBox();
		bw1.add(Box.createHorizontalStrut(10));
		bw1.add(lbmanv = new JLabel("Mã nhân viên:"));
		lbmanv.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbmanv.setForeground(Color.WHITE);
		bw1.add(Box.createHorizontalStrut(10));
		bw1.add(txtmanv = new JTextField());
		txtmanv.setForeground(new Color(255, 255, 255));
		txtmanv.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtmanv.setBackground(new Color(0, 128, 128));
		txtmanv.setEditable(false);

		Box bw2 = Box.createHorizontalBox();
		bw2.add(Box.createHorizontalStrut(10));
		bw2.add(lbtennv = new JLabel("Tên nhân viên:"));
		lbtennv.setForeground(Color.WHITE);
		lbtennv.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		bw2.add(Box.createHorizontalStrut(10));
		bw2.add(txttennv = new JTextField(6));
		txttennv.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw3 = Box.createHorizontalBox();
		bw3.add(Box.createHorizontalStrut(10));
		bw3.add(lbnamsinh = new JLabel("Ngày Sinh:"));
		lbnamsinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbnamsinh.setForeground(Color.WHITE);
		// bw3.add(Box.createHorizontalStrut(10));
		bw3.add(Box.createHorizontalStrut(10));
		bw3.add(txtnamsinh = new JTextField(6));
		txtnamsinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Properties jdp = new Properties();
		jdp.put("text.today", "Today");
		jdp.put("text.month", "Month");
		jdp.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(jdnamsinh, jdp);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DinhDangDate());
//		datePicker.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 16));
		// bw3.add(datePicker);

		Box bw4 = Box.createHorizontalBox();
		bw4.add(Box.createHorizontalStrut(10));
		bw4.add(lbsdt = new JLabel("Số điện thoại:"));
		lbsdt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbsdt.setForeground(Color.WHITE);
		bw4.add(Box.createHorizontalStrut(10));
		bw4.add(txtsdt = new JTextField(10));
		txtsdt.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw5 = Box.createHorizontalBox();
		bw5.add(Box.createHorizontalStrut(10));
		bw5.add(lbemail = new JLabel("Email:"));
		lbemail.setForeground(Color.WHITE);
		lbemail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		bw5.add(Box.createHorizontalStrut(10));
		bw5.add(txtemail = new JTextField(10));
		txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw6 = Box.createHorizontalBox();
		bw6.add(Box.createHorizontalStrut(10));
		bw6.add(lbdiachi = new JLabel("Địa Chỉ:"));
		lbdiachi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbdiachi.setForeground(Color.WHITE);
		bw6.add(Box.createHorizontalStrut(10));
		bw6.add(txtdiachi = new JTextField(10));
		txtdiachi.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw7 = Box.createHorizontalBox();
		bw7.add(Box.createHorizontalStrut(10));
		bw7.add(lbcmnd = new JLabel("CMND:"));
		lbcmnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbcmnd.setForeground(Color.WHITE);
		bw7.add(Box.createHorizontalStrut(10));
		bw7.add(txtcmnd = new JTextField(10));
		txtcmnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw8 = Box.createHorizontalBox();
		bw8.add(Box.createHorizontalStrut(10));
		bw8.add(lbchucvu = new JLabel("Chức Vụ:"));
		lbchucvu.setForeground(Color.WHITE);
		lbchucvu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		bw8.add(Box.createHorizontalStrut(10));
		bw8.add(cbchucvu = new JComboBox<String>());
		cbchucvu.setBackground(Color.WHITE);
		cbchucvu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbchucvu.addItem("Quản lý");
		cbchucvu.addItem("Nhân viên");

		Box bw9 = Box.createHorizontalBox();
		bw9.add(Box.createHorizontalStrut(10));
		bw9.add(lbmatkhau = new JLabel("Mật khẩu:"));
		lbmatkhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbmatkhau.setForeground(Color.WHITE);
		bw9.add(Box.createHorizontalStrut(10));
		bw9.add(txtmatkhau = new JPasswordField(10));
		txtmatkhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		Box bw10 = Box.createHorizontalBox();
		bw10.add(Box.createHorizontalStrut(10));
		bw10.add(lbtrangthai = new JLabel("Trạng thái:"));
		lbtrangthai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbtrangthai.setForeground(new Color(255, 255, 255));
		bw10.add(Box.createHorizontalStrut(10));
		bw10.add(cbtrangthai = new JComboBox<String>());
		cbtrangthai.setBackground(new Color(255, 255, 255));
		cbtrangthai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbtrangthai.addItem("Làm việc");
		cbtrangthai.addItem("Nghỉ làm");

		Box bw11 = Box.createHorizontalBox();
		bw11.add(Box.createHorizontalStrut(20));
		bw11.add(btnthem = new JButton("Thêm"));
		btnthem.addActionListener(this);
		btnthem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnthem.setBackground(new Color(245, 255, 250));
		bw11.add(Box.createHorizontalStrut(20));
		bw11.add(btnsua = new JButton("Cập nhật"));
		btnsua.addActionListener(this);
		btnsua.setBackground(new Color(245, 255, 250));
		btnsua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bw11.add(Box.createHorizontalStrut(20));
		bw11.add(btnxoa = new JButton("Xóa"));
		btnxoa.addActionListener(this);
		btnxoa.setBackground(new Color(245, 255, 250));
		btnxoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bw11.add(Box.createHorizontalStrut(20));
		bw11.add(btnxoatrang = new JButton("Làm mới"));
		btnxoatrang.addActionListener(this);
		btnxoatrang.setBackground(new Color(245, 255, 250));
		btnxoatrang.setFont(new Font("Times New Roman", Font.BOLD, 16));

		lbchucvu.setPreferredSize(lbtennv.getPreferredSize());
		lbdiachi.setPreferredSize(lbtennv.getPreferredSize());
		lbemail.setPreferredSize(lbtennv.getPreferredSize());
		lbtrangthai.setPreferredSize(lbtennv.getPreferredSize());
		lbmatkhau.setPreferredSize(lbtennv.getPreferredSize());
		lbnamsinh.setPreferredSize(lbtennv.getPreferredSize());
		lbsdt.setPreferredSize(lbtennv.getPreferredSize());
		lbcmnd.setPreferredSize(lbtennv.getPreferredSize());

		Box bw0 = Box.createHorizontalBox();
		JLabel label = new JLabel("THÔNG TIN NHÂN VIÊN");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 26));
		bw0.add(Box.createHorizontalStrut(30));
		bw0.add(label);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw0);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw1);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw2);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw3);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw4);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw5);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw6);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw7);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw8);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw9);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw10);
		bw.add(Box.createVerticalStrut(30));
		bw.add(bw11);
		bw.add(Box.createVerticalStrut(20));
		bw.add(txtmess = new JTextField());
		txtmess.setBackground(new Color(0, 128, 128));
		txtmess.setForeground(new Color(255, 182, 193));
		txtmess.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		txtmess.setEditable(false);
		txtmess.setBorder(null);

		pw.add(bw);

		bw.setPreferredSize(new Dimension(450, 690));
		this.add(pw, BorderLayout.EAST);

		JPanel pc = new JPanel();
		pc.setBackground(new Color(173, 216, 230));
		Box bc = Box.createVerticalBox();
		Box bc1 = Box.createHorizontalBox();
		Box bc2 = Box.createHorizontalBox();

		bc2.add(cbtim = new JComboBox<String>());
		cbtim.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		cbtim.setBackground(new Color(255, 255, 255));
		bc2.add(Box.createHorizontalStrut(80));
		cbtim.addItem("Tên");
		cbtim.addItem("Số điện thoại");
		cbtim.addActionListener(this);
		bc2.add(txttim = new JTextField(6));
		txttim.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bc2.add(Box.createHorizontalStrut(20));

		JLabel label_1 = new JLabel("CMND:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bc2.add(label_1);
		bc2.add(Box.createHorizontalStrut(20));
		bc2.add(txtcmnd2 = new JTextField(10));
		txtcmnd2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
//		Properties jdp1 = new Properties();
//		jdp1.put("text.today", "Today");
//		jdp1.put("text.month", "Month");
//		jdp1.put("text.year", "Year");
//		
//		JDatePanelImpl datePanel1 = new JDatePanelImpl(jdtimns, jdp1);
//		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new DinhDangDate());
//		datePicker1.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 13));
//	bc2.add(datePicker1);

//		JLabel label_2 = new JLabel("Giới tính:");
//		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
//		bc2.add(label_2);
//		bc2.add(Box.createHorizontalStrut(10));
//		bc2.add(cbtimgt = new JComboBox<String>());
//		cbtimgt.setBackground(new Color(255, 255, 255));
//		cbtimgt.setFont(new Font("Times New Roman", Font.PLAIN, 13));
//		cbtimgt.addItem("Nam");
//		cbtimgt.addItem("Nữ");
//		bc2.add(Box.createHorizontalStrut(20));

		bc2.add(btntim = new JButton("Tìm Kiếm"));
		btntim.addActionListener(this);
		btntim.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btntim.setBackground(new Color(245, 255, 250));

		String[] header = { "Mã Nhân Viên", "Họ Tên", "Năm sinh", "SDT", "Email", "Địa chỉ", "CMND", "Chức vụ",
				"Mật Khẩu", "Trạng Thái" };
		model = new DefaultTableModel(header, 0);
		JScrollPane pane = new JScrollPane(table = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(890, 630));
		bc1.add(pane);
		bc2.setPreferredSize(new Dimension(880, 25));
		bc.add(bc2);
		bc.add(Box.createVerticalStrut(10));
		bc.add(bc1);
		// bc.setPreferredSize(new Dimension());
		pc.add(bc);
		this.add(pc, BorderLayout.CENTER);

		// Thao tac
		table.addMouseListener(this);
		docfile();
		// txtmanv.setText(NhanVienDAO.setCodeEmployees());
	}

	public void docfile() {
		String tt = "";
		String cv = "";
		for (NhanVien a : nhanVienDao.layDanhSachNhanVien()) {
			if (a.getTrangThai().equals("1")) {
				tt = "Làm việc";
			} else
				tt = "Nghỉ làm";

			if (a.getRole().equals("EMPLOYEE")) {
				cv = "Nhân Viên";
			} else
				cv = "Quản lý";
			String[] row = { a.getMaNV(), a.getTenNV(), a.getNgaySinh(), a.getSoDT(), a.getEmail(), a.getDiaChi(),
					a.getCmnd(), cv, a.getMatKhau(), tt };
			model.addRow(row);
		}
	}
	public void docMotNhanVien(NhanVien a) {
		
			String tt = "";
			String cv = "";
			//NhanVien a = nhanVienDao.timNhanVienTheoCMND(cmnd);
				if (a.getTrangThai().equals("1")) {
					tt = "Làm việc";
				} else
					tt = "Nghỉ làm";

				if (a.getRole().equals("EMPLOYEE")) {
					cv = "Nhân Viên";
				} else
					cv = "Quản lý";
				String[] row = { a.getMaNV(), a.getTenNV(), a.getNgaySinh(), a.getSoDT(), a.getEmail(), a.getDiaChi(),
						a.getCmnd(), cv, a.getMatKhau(), tt };
				model.addRow(row);
			
		
	}
	public void docNhieuNhanVien(List<NhanVien> dsnv) {
		String tt = "";
		String cv = "";
		for (NhanVien a : dsnv) {
			if (a.getTrangThai().equals("1")) {
				tt = "Làm việc";
			} else
				tt = "Nghỉ làm";

			if (a.getRole().equals("EMPLOYEE")) {
				cv = "Nhân Viên";
			} else
				cv = "Quản lý";
			String[] row = { a.getMaNV(), a.getTenNV(), a.getNgaySinh(), a.getSoDT(), a.getEmail(), a.getDiaChi(),
					a.getCmnd(), cv, a.getMatKhau(), tt };
			model.addRow(row);
		}
	}

	public void xoaTrang() {

		txtmanv.setText("");
		txttennv.setText("");
		txtnamsinh.setText("");
		txtsdt.setText("");
		cbtrangthai.setSelectedItem("");
		txtdiachi.setText("");
		cbchucvu.setSelectedItem("");
		txtmatkhau.setText("");
		cbtrangthai.setSelectedItem("");
		txttim.setText("");
		txtcmnd.setText("");
//		for (int i = model.getRowCount()-1;i>=0; i--) {
//			model.removeRow(i);

		// }
//		docfile();
//		txtmess.setText("");
	}

	public void xoaNhanVien() {
		int row = table.getSelectedRow();
		if (row != -1) {
			int hoi = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên này!", "Thông báo xóa",
					JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				if (nhanVienDao.xoaNhanVien(table.getValueAt(row, 0).toString())) {
					model.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa Nhân Viên Thành Công!");
				} else
					JOptionPane.showMessageDialog(this, "Nhân Viên Không tồn tại!");
			}
		} else
			JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nhân Viên Cần Xóa!");
	}

	public void xoaRow() {
		//((DefaultTableModel) table.getModel()).setRowCount(0);
			for (int i = model.getRowCount()-1; i>=0; i--) {
				model.removeRow(i);
			}
	}
//	
	public void suaNhanVien() throws ParseException {
		// NhanVien(String maNV, String tenNV, String ngaySinh, String soDT, String
		// email, String diaChi, String cmnd,
		// String role, String matKhau, String trangThai) {

		// jdnamsinh.getYear()+"/"+m+"/"+jdnamsinh.getDay(),txtsdt.getText(),cbTrangThai.getSelectedItem().toString(),txtdiachi.getText(),cbchucvu.getSelectedItem().toString(),txtmatkhau.getText(),cbtrangthai.getSelectedItem().toString()
		String tt = "0";
		if (cbtrangthai.getModel().getSelectedItem().toString().equals("Làm Việc")) {
			tt = "1";
		}

		int row = table.getSelectedRow();
		if (row != -1) {
			int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa nhân viên không?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (hoi == JOptionPane.YES_OPTION) {
				if (checkdata()) {
					int m = jdnamsinh.getMonth() + 1;
					NhanVien nv = new NhanVien(txtmanv.getText(), txttennv.getText(),
							jdnamsinh.getYear() + "-" + m + "-" + jdnamsinh.getDay(), txtsdt.getText(),
							txtemail.getText(), txtdiachi.getText(), txtcmnd.getText(),
							cbchucvu.getModel().getSelectedItem().toString(), txtmatkhau.getText(), tt);
					if (nhanVienDao.suaNhanVien(nv)) {
//				for (int i = model.getRowCount()-1;i>=0; i--) {
//				model.removeRow(i);
						xoaRow();
						docfile();
					} else
						JOptionPane.showMessageDialog(this, "Nhân viên không tồn tại để sửa!");
//				docfile();
//				txtmess.setText("");
				}
				// else JOptionPane.showMessageDialog(this,"Kiểm tra lại định dạng!");
			}
		}
	}

	public void themNhanVien() throws ParseException {
		NhanVien nv;
		String tt = "0";
		if (cbtrangthai.getModel().getSelectedItem().toString().equals("Làm Việc")) {
			tt = "1";
		}
		String cv="ADMIN";
		if(cbchucvu.getModel().getSelectedItem().toString().equals("Nhân viên")) {
			cv="EMPLOYEE";
		}
		int hoi=JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn thêm Nhân Viên?","Thông báo",JOptionPane.YES_NO_OPTION);
		if(hoi==JOptionPane.YES_OPTION) {
		if(checkdata()) {
			//int m=jdnamsinh.getMonth()+1;
//			nv = new NhanVien(txtmanv.getText(), txttennv.getText(), jdnamsinh.getYear()+"/"+m+"/"+jdnamsinh.getDay(), txtsdt.getText(), txtemail.getText(), txtdiachi.getText(), txtcmnd.getText(),cv, txtmatkhau.getText(), tt);
			nv = new NhanVien(txtmanv.getText(), txttennv.getText(),txtnamsinh.getText(), txtsdt.getText(), txtemail.getText(), txtdiachi.getText(), txtcmnd.getText(),cv, txtmatkhau.getText(), tt);
			nv.setMaNV(nhanVienDao.setCodeEmployees(nv, nv.getRole()));
		if(nhanVienDao.themNhanVien(nv))
			{		
				xoaRow();
				docfile();
			}
		else JOptionPane.showMessageDialog(this,"Thêm Không Thành Công!");
		txtmanv.setText(nhanVienDao.setCodeEmployees(nv, nv.getRole()));
		txtmess.setText("");
			}
		}
		
	}
	public void setsearch() {
		if(cbtim.getSelectedIndex()==1) {
		cbtimgt.setEnabled(false);
		txttimsdt.setEditable(false);
		}
		if(cbtim.getSelectedIndex()==0) {
			cbtimgt.setEnabled(true);
			txttimsdt.setEditable(true);
		}
		}
	public boolean checkdata() {
		// String regexTen =
		// "^([a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+)$";
		String regexTen = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)$";
		if (!(txttennv.getText().length() > 0 && txttennv.getText().trim().matches(regexTen))) {
			// txtmess.setText("Vui lòng nhập tên đúng định dạng hoặc không để trống!");
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đúng định dạng hoặc không để trống!");
			return false;
		}
		LocalDate checkdate = LocalDate.of(jdnamsinh.getYear(), jdnamsinh.getMonth() + 1, jdnamsinh.getDay());
		if (checkdate.isAfter(LocalDate.now())) {
			// txtmess.setText("!");
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng ngày!");
			return false;
		}
		if (!(txtsdt.getText().length() > 0 && txtsdt.getText().matches("^0[1-9][0-9]{8}"))) {
			// txtmess.setText("Nhập đúng số điện thoại!");
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số điện thoại! hoặc không để trống");
			return false;
		}
		// if(!(txtdiachi.getText().length()>0&&txtdiachi.getText().matches("[aAÃ Ã€áº£áº¢Ã£ÃƒÃ¡Ã�áº¡áº ÄƒÄ‚áº±áº°áº³áº²áºµáº´áº¯áº®áº·áº¶Ã¢Ã‚áº§áº¦áº©áº¨áº«áºªáº¥áº¤áº­áº¬bBcCdDÄ‘Ä�eEÃ¨Ãˆáº»áººáº½áº¼Ã©Ã‰áº¹áº¸ÃªÃŠá»�á»€á»ƒá»‚á»…á»„áº¿áº¾á»‡á»†fFgGhHiIÃ¬ÃŒá»‰á»ˆÄ©Ä¨Ã­Ã�á»‹á»ŠjJkKlLmMnNoOÃ²Ã’á»�á»ŽÃµÃ•Ã³Ã“á»�á»ŒÃ´Ã”á»“á»’á»•á»”á»—á»–á»‘á»�á»™á»˜Æ¡Æ á»�á»œá»Ÿá»žá»¡á» á»›á»šá»£á»¢pPqQrRsStTuUÃ¹Ã™á»§á»¦Å©Å¨ÃºÃšá»¥á»¤Æ°Æ¯á»«á»ªá»­á»¬á»¯á»®á»©á»¨á»±á»°vVwWxXyYá»³á»²á»·á»¶á»¹á»¸Ã½Ã�á»µá»´zZ0-9
		// ]+"))) {txtmess.setText("*/ Ä�á»‹a chá»‰ khÃ´ng há»£p lá»‡ !"); return
		// false;}
		if (!(txtmatkhau.getText().length() >= 8 && txtmatkhau.getText().matches("[A-Za-z0-9]{8,}"))) {
			// txtmess.setText("Mật khẩu chữ và số ít nhất là 8 ký tự!");
			JOptionPane.showMessageDialog(this, "Mật khẩu chữ hoặc số ít nhất là 8 ký tự!");
			return false;
		}
		if(txtnamsinh.getText().length()>0 && txtnamsinh.getText().matches("[0-9][-/][0-9]{2}[-/][0-9]{4}")) {
			JOptionPane.showMessageDialog(this, "Năm sinh chưa đúng định dạng, cách nhau bằng dấu '-' hoặc '/'!");
			return false;
		}
		if(nhanVienDao.timNhanVienTheoMail(txtemail.getText().trim())!=null) {
			JOptionPane.showMessageDialog(this, "Email đã tồn tại!");
		}
		return true;
	}

//	public static void main(String[] args) {
//		//String regexTen = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)|([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+[\\s][a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+)$";
//		Scanner scan = new Scanner(System.in);
//		String ten = scan.nextLine();
//		if(ten.matches(regexTen)) {
//			System.out.println("true");
//		} else System.out.println("false");
//	}
//
//	public void timNhanVien() {
////		if(cbtim.getSelectedIndex()==1)
////		{	if(!(txttim.getText().length()>0&&txttim.getText().matches("[A-Za-z0-9]+"))) {txtmess.setText("*/ MÃ£ hoáº·c sá»‘ Ä‘iá»‡n thoáº¡i khÃ´ng há»£p lá»‡ !"); return;}
////			NhanVien nv=NhanVienDAO.timNhanVienTheoMa(txttim.getText());
////			if(nv!=null){
////				
////				for (int i = model.getRowCount()-1;i>=0; i--) {
////					model.removeRow(i);}
////				
////				String[] row= {nv.getMaNV(),nv.getTenNV(),nv.getNamSinh()+"",nv.getSoDT(),nv.getGioiTinh(),nv.getDiaChi(),nv.getChucVu(),nv.getTrangThai()};
////				model.addRow(row);
////				
////				
////				txtmanv.setText(nv.getMaNV());
////				txttennv.setText(nv.getTenNV());
////				jdnamsinh.setDate(nv.getNamSinh().getYear(),nv.getNamSinh().getMonth().getValue()-1,nv.getNamSinh().getDayOfMonth());
////				jdnamsinh.setSelected(true);
////				txtsdt.setText(nv.getSoDT());
////				cbTrangThai.setSelectedItem(nv.getGioiTinh());
////				txtdiachi.setText(nv.getDiaChi());
////				cbchucvu.setSelectedItem(nv.getChucVu());
////				txtmatkhau.setText(nv.getMatKhau());;
////				cbtrangthai.setSelectedItem(nv.getTrangThai());
////				txtmess.setText("");
////				
////			}else JOptionPane.showMessageDialog(this,"KhÃ´ng tÃ¬m tháº¥y");
////			
////		}
////		if(cbtim.getSelectedIndex()==0) {
////			LocalDate checkdate1=LocalDate.of(jdtimns.getYear(),jdtimns.getMonth()+1,jdtimns.getDay());
////			if(checkdate1.equals(LocalDate.now()))checkdate=""; else checkdate=checkdate1.toString();
////			//if(!(txttim.getText().length()>0&&txttim.getText().matches("[aAÃ Ã€áº£áº¢Ã£ÃƒÃ¡Ã�áº¡áº ÄƒÄ‚áº±áº°áº³áº²áºµáº´áº¯áº®áº·áº¶Ã¢Ã‚áº§áº¦áº©áº¨áº«áºªáº¥áº¤áº­áº¬bBcCdDÄ‘Ä�eEÃ¨Ãˆáº»áººáº½áº¼Ã©Ã‰áº¹áº¸ÃªÃŠá»�á»€á»ƒá»‚á»…á»„áº¿áº¾á»‡á»†fFgGhHiIÃ¬ÃŒá»‰á»ˆÄ©Ä¨Ã­Ã�á»‹á»ŠjJkKlLmMnNoOÃ²Ã’á»�á»ŽÃµÃ•Ã³Ã“á»�á»ŒÃ´Ã”á»“á»’á»•á»”á»—á»–á»‘á»�á»™á»˜Æ¡Æ á»�á»œá»Ÿá»žá»¡á» á»›á»šá»£á»¢pPqQrRsStTuUÃ¹Ã™á»§á»¦Å©Å¨ÃºÃšá»¥á»¤Æ°Æ¯á»«á»ªá»­á»¬á»¯á»®á»©á»¨á»±á»°vVwWxXyYá»³á»²á»·á»¶á»¹á»¸Ã½Ã�á»µá»´zZ0-9 ]+"))) {txtmess.setText("*/ ThÃ´ng tin tÃ¬m kiáº¿m khÃ´ng há»£p lá»‡ !"); return;}
////			ArrayList<NhanVien>ds=NhanVienDAO.timNhanVienTheoTieuChi(txttim.getText(),cbtimgt.getSelectedItem().toString(),txttimsdt.getText(),checkdate);
////			for (int i = model.getRowCount()-1;i>=0; i--) {
////				model.removeRow(i);}
////			for (NhanVien nhanVien : ds) {
////				String[] row2= {nhanVien.getMaNV(),nhanVien.getTenNV(),nhanVien.getNamSinh()+"",nhanVien.getSoDT(),nhanVien.getGioiTinh(),nhanVien.getDiaChi(),nhanVien.getChucVu(),nhanVien.getTrangThai()};
////				model.addRow(row2);
////			}
////			txtmess.setText("");
////		}
//	}
//	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnthem))
			try {
				 themNhanVien();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if (o.equals(btnxoa))
			xoaNhanVien();
		if (o.equals(btnsua))
			try {
				suaNhanVien();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(o.equals(btntim))
			timNhanVien();
		// if(o.equals(cbtim))setsearch();
		if (o.equals(btnxoatrang))
			xoaTrang();

	}
	
	public void timNhanVienTheoCMND(String cmnd) {
			try {
				long parCMND = Long.parseLong(cmnd);
				if(parCMND >0) {
					NhanVien nv = nhanVienDao.timNhanVienTheoCMND(cmnd);
					if(nv!=null) {
						xoaRow();
						docMotNhanVien(nv);
					}
					else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhân viên!", "Thông báo" ,JOptionPane.CANCEL_OPTION);
				
				}
				
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this,"Định dạng cmnd không đúng!", "Thông báo" ,JOptionPane.CANCEL_OPTION);
			}
		}
	
	public void timNhanVienTheoSDT(String sdt) {
		NhanVien nv = nhanVienDao.layDanhSachNhanVienTheoSDT(sdt);
		if(nv!=null) {
			xoaRow();
			docMotNhanVien(nv);
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhân viên!","Thông báo" ,JOptionPane.CANCEL_OPTION);
	}
	
	public void timNhanVienTheoTen(String ten) {
		List<NhanVien> dsnv = nhanVienDao.layDanhSachNhanVienTheoTen(ten);
		if(!dsnv.isEmpty()) {
			xoaRow();
			docNhieuNhanVien(dsnv);
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy nhân viên!", "Thông báo",JOptionPane.CANCEL_OPTION);
	}
	
	public void timNhanVien() {
		
		if(cbtim.getSelectedItem().toString().equals("Tên") && !txttim.getText().trim().equals("") && txtcmnd2.getText().trim().equals("")) {
			timNhanVienTheoTen(txttim.getText().trim());
		}
		else if(txttim.getText().trim().equals("") && cbtim.getSelectedItem().toString().equals("Tên") && txtcmnd2.getText().trim().equals("")) {
			
			JOptionPane.showConfirmDialog(this,"Bạn chưa nhập tên tìm kiếm!", "Thông Báo",JOptionPane.CANCEL_OPTION);
		}
		else if(!cbtim.getSelectedItem().toString().equals("Tên") && !txttim.getText().trim().equals("") && txtcmnd2.getText().trim().equals("")) {
			timNhanVienTheoSDT(txttim.getText().trim());
		}
		else if(!cbtim.getSelectedItem().toString().equals("Tên") && txttim.getText().trim().equals("") && txtcmnd2.getText().trim().equals("")) {
			JOptionPane.showConfirmDialog(this,"Bạn chưa nhập số điện thoại tìm kiếm!", "Thông Báo",JOptionPane.CANCEL_OPTION);
		}
		else if(txttim.getText().trim().equals("") && !txtcmnd2.getText().trim().equals("")) {
			
			timNhanVienTheoCMND(txtcmnd2.getText().trim());
		}
		else if(!txttim.getText().trim().equals("") && !txtcmnd2.getText().trim().equals(""))
			JOptionPane.showConfirmDialog(this,"Bạn chưa nhập ô tìm kiếm hoặc bạn đang tìm kiếm nhiều kiểu!", "Thông Báo",JOptionPane.CANCEL_OPTION);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtmanv.setText(String.valueOf(table.getValueAt(row, 0)));
		txttennv.setText(String.valueOf(table.getValueAt(row, 1)));
		txtnamsinh.setText(String.valueOf(table.getValueAt(row, 2)));
		txtsdt.setText(String.valueOf(table.getValueAt(row, 3)));
		txtemail.setText(String.valueOf(table.getValueAt(row, 4)));
		txtdiachi.setText(String.valueOf(table.getValueAt(row, 5)));
		txtcmnd.setText(String.valueOf(table.getValueAt(row, 6)));
		cbchucvu.getModel().setSelectedItem(String.valueOf(table.getValueAt(row, 7)));
		txtmatkhau.setText(String.valueOf(table.getValueAt(row, 8)));
		cbtrangthai.getModel().setSelectedItem(String.valueOf(table.getValueAt(row, 9)));

//		NhanVien nv=NhanVienDAO.timNhanVienTheoMa(table.getValueAt(row,0).toString());
//		txtmanv.setText(nv.getMaNV());
//		txttennv.setText(nv.getTenNV());
//		//jdnamsinh.setDate(nv.getNamSinh().getYear(),nv.getNamSinh().getMonth().getValue()-1,nv.getNamSinh().getDayOfMonth());
//		//jdnamsinh.setSelected(true);
//		txtsdt.setText(nv.getSoDT());
//		cbTrangThai.setSelectedItem(nv.getGioiTinh());
//		txtdiachi.setText(nv.getDiaChi());
//		cbchucvu.setSelectedItem(nv.getChucVu());
//		txtmatkhau.setText(nv.getMatKhau());;
//		cbtrangthai.setSelectedItem(nv.getTrangThai());

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
}