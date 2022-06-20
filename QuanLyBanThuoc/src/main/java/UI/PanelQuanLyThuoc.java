package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
//
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DAO.DinhDangDate;
import DAO.LoaiThuocDAO;
import DAO.NhaSanXuatDAO;
import DAO.ThuocDAO;
import Model.KhachHang;
import Model.LoThuoc;
import Model.NhomThuoc;
import Model.NhaCungCap;
import Model.Thuoc;
import Service.LoaiThuocService;
import Service.NhaSanXuatService;
import Service.ThuocService;
import dto.ThuocDTO;

public class PanelQuanLyThuoc extends JPanel implements ActionListener, MouseListener {

	private JFrame frame;
	private JPanel pThuoc, pThongTinThuoc, pDanhSachThuoc, pXuLy, pButton, pTimKiem;

	private ThuocService thuocDAO = new ThuocDAO();
	private LoaiThuocService loaiThuocDAO = new LoaiThuocDAO();
	private NhaSanXuatService nhaSanXuatDAO = new NhaSanXuatDAO();
	// Component for Panel Thong tin thuoc
	private SqlDateModel model;
	private Properties properties;
	private JDatePanelImpl panelImpl;
	private JDatePickerImpl datePickerImpl;
	
	private SqlDateModel model1;
	private Properties properties1;
	private JDatePanelImpl panelImpl1;
	private JDatePickerImpl datePickerImpl1;
	private JTextField txtMaLoThuoc ,txtXuatXu,txtNgaySanXuat, txtGiaBanChan, txtGiaBanLe,txtHoatChatChinh, txtLieuLuongDung, txtSoLuongTon, txtTyLeQuyDoi, txtHSD, txtVat,txtMoTa;	
	private JComboBox<String> cbLoai, cbNhaSX, cbDonVi, cbDonViQuyDoi, cbTrangThai;
	private List<ThuocDTO> thuocs;
	private List<LoThuoc> loThuocs;
	private List<NhomThuoc> loaiThuocs;
	private List<NhaCungCap> nhaSanXuats;
	private Vector<String> donVi = new Vector<String>(thuocDAO.layDanhSachDVT());
	private Vector<String> donViQuyDoi = new Vector<String>(thuocDAO.layDanhSachDVQD());
	private JButton btnLoaiThuocConfig, btnNSXConfig;
	private DefaultTableModel tableModel;
	private JTable tbDSThuoc;
	private JScrollPane scrollPane;
	// Component for Panel button
	private JComboBox<String> cbXuLy;
	private JButton btnXuLy, btnXoa, btnLamMoi, btnCapNhat;
	// Component for Panel tÃ¬m kiáº¿m
	private JTextField txtTheoMa, txtTheoTen, txtTheoHoatChat;
	private JComboBox<String> cbTheoLoai, cbTheoNSX,cbTenThuoc;
	private JButton btnTim;
	
	public PanelQuanLyThuoc(JFrame frame) {
		this.frame = frame;
		setLayout(new BorderLayout());
		this.setBackground(new Color(0, 128, 128));
		thuocs = new ArrayList<ThuocDTO>();
		loThuocs = new ArrayList<LoThuoc>();
		loaiThuocs = new ArrayList<NhomThuoc>();
		nhaSanXuats = new ArrayList<NhaCungCap>();
		taoPanelThuoc();
		taoPanelXuLy();

		themListener();

		lamMoiUI();

		this.add(pThuoc);
		this.add(pXuLy, BorderLayout.EAST);
	}

	public void taoPanelThuoc() {
		taoPanelTTThuoc();
		taoPanelDSThuoc();
		pThuoc = new JPanel();
		pThuoc.setBackground(new Color(0, 128, 128));
		pThuoc.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridx = 0;
		c.gridy = 0;
		pThuoc.add(pThongTinThuoc, c);
		c.gridy = 1;
		pThuoc.add(pDanhSachThuoc, c);
	}

	public void taoPanelTTThuoc() {
		JLabel lblMaLoThuoc, lblTenThuoc, lblLoai, lblXuatXu, lblNgaySanXuat, lblNhaCC ,lblMoTa, lblDonViQuyDoi, lblGiaNhap,
		lblTrangThai,lblGiaBanChan, lblTyLeQuyDoi, lblGiaBanLe, lblHoatChatChinh, lblLieuLuongDung, lblSoLuongTon, lblDonViTinh, lblGiaTriQuyDoi, lblHSD, lblVat;
		lblMaLoThuoc = new JLabel("Mã lô thuốc:");
		lblMaLoThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblLoai = new JLabel("Loại thuốc:");
		lblLoai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblXuatXu = new JLabel("Xuất xứ:");
		lblXuatXu.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblNgaySanXuat = new JLabel("Ngày sản xuất:");
		lblNgaySanXuat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblNhaCC = new JLabel("Nhà cung cấp:");
		lblNhaCC.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblDonViQuyDoi = new JLabel("Đơn vị quy đổi:");
		lblDonViQuyDoi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTyLeQuyDoi = new JLabel("Tỷ lệ quy đổi:");
		lblTyLeQuyDoi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblGiaBanChan = new JLabel("Giá bán chẵn:");
		lblGiaBanChan.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblGiaBanLe = new JLabel("Giá bán lẻ:");
		lblGiaBanLe.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblHoatChatChinh = new JLabel("Hoạt chất chính:");
		lblHoatChatChinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblLieuLuongDung = new JLabel("Liệu lượng dùng:");
		lblLieuLuongDung.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblSoLuongTon = new JLabel("Số lượng tồn:");
		lblSoLuongTon.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblDonViTinh = new JLabel("Đơn vị tính");
		lblDonViTinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblVat = new JLabel("Vat:");
		lblVat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblHSD = new JLabel("Hạn sử dụng:");
		lblHSD.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		
		model = new SqlDateModel();
		properties = new Properties();
		properties.put("text.day", "Day");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		panelImpl = new JDatePanelImpl(model, properties);
		panelImpl.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		datePickerImpl = new JDatePickerImpl(panelImpl, new DinhDangDate());
		datePickerImpl.setBackground(new Color(0, 128, 128));
		datePickerImpl.getModel().setSelected(true);
		datePickerImpl.getJFormattedTextField().setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		
		model1 = new SqlDateModel();
		properties1 = new Properties();
		properties1.put("text.day", "Day");
		properties1.put("text.month", "Month");
		properties1.put("text.year", "Year");
		panelImpl1 = new JDatePanelImpl(model1, properties);
		panelImpl1.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		datePickerImpl1 = new JDatePickerImpl(panelImpl1, new DinhDangDate());
		datePickerImpl1.setBackground(new Color(0, 128, 128));
		datePickerImpl1.getModel().setSelected(true);
		datePickerImpl1.getJFormattedTextField().setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		
		txtMaLoThuoc = new JTextField(30);
		txtMaLoThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtMaLoThuoc.setEditable(false);
		txtMaLoThuoc.setMinimumSize(new Dimension(250, 20));
		cbTenThuoc = new JComboBox<String>();
		cbTenThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		cbLoai = new JComboBox<String>();
		txtXuatXu = new JTextField(30);
		txtXuatXu.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		txtNgaySanXuat = new JTextField(30);
//		txtNgaySanXuat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		txtNhaCC = new JTextField(30);
//		txtNhaCC.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		txtDonViQuyDoi = new JTextField(30);
//		txtDonViQuyDoi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTyLeQuyDoi = new JTextField(30);
		txtTyLeQuyDoi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));	
		txtGiaBanChan = new JTextField(30);
		txtGiaBanChan.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtGiaBanLe = new JTextField(30);
		txtGiaBanLe.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtHoatChatChinh = new JTextField(30);
		txtHoatChatChinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtLieuLuongDung = new JTextField(30);
		txtLieuLuongDung.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtSoLuongTon = new JTextField(30);
		txtSoLuongTon.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		txtDonViTinh = new JTextField(30);
//		txtDonViTinh.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtVat = new JTextField(30);
		txtVat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
	
//		txtHSD = new JTextField(30);
//		txtHSD.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtMoTa = new JTextField(30);
		txtMoTa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
//		cbTrangThai = new JTextField(30);
//		cbTrangThai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		taoCBBoxChoTTThuoc();

		btnLoaiThuocConfig = new JButton();
		btnLoaiThuocConfig.setBackground(Color.white);
		btnLoaiThuocConfig.setToolTipText("TÃ¹y chá»‰nh loáº¡i thuá»‘c");
		btnNSXConfig = new JButton();
		btnNSXConfig.setBackground(Color.white);
		btnNSXConfig.setToolTipText("TÃ¹y chá»‰nh NSX");

		ImageIcon icon = new ImageIcon("hinh/config_icon.png");
		Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		btnLoaiThuocConfig.setIcon(icon);
		btnNSXConfig.setIcon(icon);

		pThongTinThuoc = new JPanel();
		pThongTinThuoc.setBackground(new Color(0, 128, 128));
		pThongTinThuoc.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 0;
		c.ipady = 5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblMaLoThuoc, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtMaLoThuoc, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblTenThuoc, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbTenThuoc, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblLoai, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbLoai, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblXuatXu, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtXuatXu, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 2;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblNgaySanXuat, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(datePickerImpl, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblHSD, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(datePickerImpl1, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 3;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblDonViQuyDoi, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbDonViQuyDoi, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblTyLeQuyDoi, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtTyLeQuyDoi, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 4;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblGiaBanChan, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtGiaBanChan, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblGiaBanLe, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtGiaBanLe, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 5;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblHoatChatChinh, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtHoatChatChinh, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblLieuLuongDung, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtLieuLuongDung, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 6;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblSoLuongTon, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtSoLuongTon, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblDonViTinh, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbDonVi, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 7;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblVat, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtVat, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblNhaCC, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbNhaSX, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridy = 8;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblMoTa, c);
		c.gridx = 1;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(txtMoTa, c);
		c.gridx = 2;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(Box.createRigidArea(new Dimension(0, 0)), c);
		c.gridx = 3;
		c.weightx = 0;
		c.weighty = 0;
		pThongTinThuoc.add(lblTrangThai, c);
		c.gridx = 4;
		c.weightx = 1;
		c.weighty = 1;
		pThongTinThuoc.add(cbTrangThai, c);
		c.gridx = 5;
		c.weightx = 0;
		c.weighty = 0;
	}

	public void taoCBBoxChoTTThuoc() {
		//
		cbLoai = new JComboBox<String>();
		cbLoai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		//
		cbNhaSX = new JComboBox<String>();
		cbNhaSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		//
		cbDonVi = new JComboBox<String>(donVi);
		cbDonVi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		//
		cbDonViQuyDoi = new JComboBox<String>(donViQuyDoi);
		cbDonViQuyDoi.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		String[] tt = { "Còn bán", "Ngừng bán" };
		cbTrangThai = new JComboBox<String>(tt);
		cbTrangThai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
	}

	public void taoPanelDSThuoc() {
		pDanhSachThuoc = new JPanel(new GridLayout(1, 1));
		pDanhSachThuoc.setBackground(new Color(0, 128, 128));
		pDanhSachThuoc.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		String[] columnName = { "Mã lô", "Tên thuốc", "Nhóm thuốc","HCC" ,"NCC", "Xuất xứ", "NSX",
				"HSD", "ĐVT", "ĐVQĐ","TLQĐ","Giá bán chẵn","Giá bán lẻ","Liều lượng dùng","Mô tả","Vat","SL tồn", "TT" };
		tableModel = new DefaultTableModel(columnName, 30);

		tbDSThuoc = new JTable(tableModel);
//		tbDSThuoc.setBackground(new Color(173, 216, 230));
		tbDSThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 14));
		tbDSThuoc.setRowHeight(20);
		tbDSThuoc.setAutoscrolls(true);

		JTableHeader tableHeader = tbDSThuoc.getTableHeader();
		tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		scrollPane = new JScrollPane(tbDSThuoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(new Color(173, 216, 230));
		scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Danh sách thuốc", TitledBorder.LEFT,
				TitledBorder.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 16)));
		scrollPane.setMinimumSize(new Dimension(700, 380));

		pDanhSachThuoc.add(scrollPane);
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
		c.insets = new Insets(5, 5, 0, 5);

		c.gridy = 0;
		c.gridx = 0;
		pXuLy.add(pButton, c);
		c.gridy = 1;
		pXuLy.add(pTimKiem, c);

	}

	public void taoPanelButton() {
		taoButtonChoPanelButton();

		String[] option = { "Nháº­p thuá»‘c", "ThÃªm thuá»‘c", "Cáº­p nháº­t" };
		cbXuLy = new JComboBox<String>(option);
		cbXuLy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		pButton = new JPanel();
		pButton.setBackground(new Color(0, 128, 128));
		pButton.setLayout(new GridBagLayout());
		pButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridy = 0;
		c.gridx = 1;
		pButton.add(cbXuLy, c);
		c.gridy = 2;
		pButton.add(btnXuLy, c);
		c.gridy = 3;
		pButton.add(btnXoa, c);
		c.gridy = 4;
		pButton.add(btnCapNhat, c);
		c.gridy = 5;
		pButton.add(btnLamMoi, c);

	}

	public void taoButtonChoPanelButton() {
		btnXuLy = new JButton("Nhập thuốc");
		btnXuLy.setBackground(Color.white);
		btnXuLy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnXoa.setBackground(Color.white);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi.setBackground(Color.white);
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnCapNhat.setBackground(Color.white);

	}

	public void taoPanelTimKiem() {
		JLabel lblTimKiem, lblTheoTen, lblTheoLoaiThuoc, lblTheoHoatChat, lblTheoNSX;

		lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
		lblTimKiem.setOpaque(true);
		lblTimKiem.setBackground(new Color(173, 216, 230));
		lblTimKiem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
//		lblTheoMa = new JLabel("Theo mÃ£:");
//		lblTheoMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoTen = new JLabel("Theo tên:");
		lblTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoLoaiThuoc = new JLabel("Theo loại thuốc:");
		lblTheoLoaiThuoc.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoHoatChat = new JLabel("Theo hoạt chất chính:");
		lblTheoHoatChat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		lblTheoNSX = new JLabel("Theo nhà cung cấp:");
		lblTheoNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

//		txtTheoMa = new JTextField(20);
//		txtTheoMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoTen = new JTextField(20);
		txtTheoTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtTheoHoatChat = new JTextField(20);
		txtTheoHoatChat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		cbTheoNSX = new JComboBox<String>();
		cbTheoNSX.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		cbTheoLoai = new JComboBox<String>();
		cbTheoLoai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnTim.setBackground(Color.white);

		pTimKiem = new JPanel(new GridBagLayout());
		pTimKiem.setBackground(new Color(0, 128, 128));
		pTimKiem.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridy = 0;
		c.gridx = 0;
		pTimKiem.add(lblTimKiem, c);
		c.gridy = 1;
		pTimKiem.add(lblTheoTen, c);
		c.gridy = 2;
		pTimKiem.add(txtTheoTen, c);
		c.gridy = 3;
		pTimKiem.add(lblTheoLoaiThuoc, c);
		c.gridy = 4;
		pTimKiem.add(cbTheoLoai, c);
		c.gridy = 5;
		pTimKiem.add(lblTheoHoatChat, c);
		c.gridy = 6;
		pTimKiem.add(txtTheoHoatChat, c);
		c.gridy = 7;
		pTimKiem.add(lblTheoNSX, c);
		c.gridy = 8;
		pTimKiem.add(cbTheoNSX, c);
		c.gridy = 9;
		pTimKiem.add(btnTim, c);

	}

	public void themListener() {
		btnLamMoi.addActionListener(this);
		cbDonVi.addActionListener(this);
		cbXuLy.addActionListener(this);
		btnXuLy.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		tbDSThuoc.addMouseListener(this);
		btnLoaiThuocConfig.addActionListener(this);
		btnNSXConfig.addActionListener(this);
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbDSThuoc.getSelectedRow();
		
		if(row >=0) {
			txtMaLoThuoc.setText(String.valueOf(tableModel.getValueAt(row, 0)));
			cbTenThuoc.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 1)));
			cbLoai.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 2)));
			txtHoatChatChinh.setText(String.valueOf(tableModel.getValueAt(row, 3)));
			cbNhaSX.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 4)));
			txtXuatXu.setText(String.valueOf(tableModel.getValueAt(row, 5)));
			String[] date1 = String.valueOf(tableModel.getValueAt(row, 6)).split("-");
			String[] date11 = date1[2].split(" ");
			model.setDate(Integer.parseInt(date1[0]),Integer.parseInt(date1[1])-1,Integer.parseInt(date11[0]));
			model.setSelected(true);
			
			String[] date2 = String.valueOf(tableModel.getValueAt(row, 7)).split("-");
			String[] date22 = date2[2].split(" ");
			model1.setDate(Integer.parseInt(date2[0]),Integer.parseInt(date2[1])-1,Integer.parseInt(date22[0]));
			model1.setSelected(true);
			cbDonVi.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 8)));
			cbDonViQuyDoi.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 9)));
			txtTyLeQuyDoi.setText(String.valueOf(tableModel.getValueAt(row, 10)));
			txtGiaBanChan.setText(String.valueOf(tableModel.getValueAt(row, 11)));
			txtGiaBanLe.setText(String.valueOf(tableModel.getValueAt(row, 12)));
			txtLieuLuongDung.setText(String.valueOf(tableModel.getValueAt(row, 13)));
			txtMoTa.setText(String.valueOf(tableModel.getValueAt(row, 14)));
			txtVat.setText(String.valueOf(tableModel.getValueAt(row, 15)));
			txtSoLuongTon.setText(String.valueOf(tableModel.getValueAt(row, 16)));
			cbTrangThai.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 17)));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object.equals(btnLamMoi))
			lamMoiUI();
//		else if (object.equals(cbDonVi))
//			xuLyCBDonVi();
//		else if (object.equals(cbXuLy))
//			xuLyCBXuLy();
//		else if (object.equals(btnXuLy) && cbXuLy.getSelectedIndex() == 1)
//			xuLyThemThuoc();
//		else if (object.equals(btnLoaiThuocConfig))
//			moDialogQLLoaiThuoc();
//		else if (object.equals(btnNSXConfig))
//			moDialogQLNSX();
//		else if (object.equals(btnCapNhat))
//			moDialogThuHoiThuocHetHan();
//		else if (object.equals(btnXoa))
//			xuLyXoaThuoc();
//		else if (object.equals(btnXuLy) && cbXuLy.getSelectedIndex() == 2)
//			xuLyCapNhatThuoc();
		else if (object.equals(btnXuLy))
			xuLyNhapThuoc();
		else if (object.equals(btnTim))
			xuLyTim();
	}

	public void lamMoiUI() {
		lamMoiDSThuoc();
		lamMoiBangThuoc();
		lamMoiDSLoaiThuoc();
		lamMoiDSTenThuoc();
		lamMoiCBBoxLoaiThuocVaCBBoxTheoLoaiThuoc();
		lamMoiDSNSX();
		lamMoiCBBoxNSXVaCBBoxTheoNSX();
		searchCbLoaiThuoc();
		searchCbTenThuoc();
		searchCbNhaCC();
		searchCbTheoNCC();
		searchCbTheoLoai();
		editCBO();
		clearAll();
		updateUI();
	}
	public void editCBO() {
		cbDonViQuyDoi.setEditable(true);
		cbDonVi.setEditable(true);
		cbTheoNSX.setEditable(true);
		cbTheoLoai.setEditable(true);
		txtSoLuongTon.setEnabled(false);
	}

	public void searchCbTheoNCC() {
		AutoCompleteDecorator.decorate(cbTheoNSX);
		cbTheoNSX.setEditable(true);
	}
	
	public void searchCbTheoLoai() {
		AutoCompleteDecorator.decorate(cbTheoLoai);
		cbTheoLoai.setEditable(true);
	}
	
	public void searchCbNhaCC() {
		AutoCompleteDecorator.decorate(cbNhaSX);
		cbNhaSX.setEditable(true);
	}
	public void searchCbTenThuoc() {

		cbTenThuoc.setEditable(true);
	}
	public void searchCbLoaiThuoc() {
		AutoCompleteDecorator.decorate(cbLoai);
		cbLoai.setEditable(true);
	}

	public void lamMoiDSTenThuoc() {
		cbTenThuoc.removeAllItems();
		for (String loaiThuoc : thuocDAO.layDanhSachTenThuoc()) {
			cbTenThuoc.addItem(loaiThuoc);
		}
		
		
	}
	
	public void lamMoiDSThuoc() {
		List<ThuocDTO> temp = thuocDAO.layDanhSachThuoc();
		thuocs.clear();
		thuocs.addAll(temp);

	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
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

	public void lamMoiBangThuoc() {
		tableModel.getDataVector().removeAllElements();
		Vector<String> vector;
		for (ThuocDTO thuoc : thuocs) {
			vector = new Vector<String>();
			vector.add(thuoc.getMaLoThuoc());
			vector.add(thuoc.getTenThuoc());
			vector.add(thuoc.getTenLoaiThuoc());
			vector.add(thuoc.getHoatChatChinh());
			vector.add(thuoc.getTenNCC());
			vector.add(thuoc.getXuatXu());
			vector.add(thuoc.getNgaySanXuat());
			vector.add(thuoc.getHanSuDung());
			vector.add(thuoc.getDonViTinh());
			vector.add(thuoc.getDonViQuyDoi());
			vector.add(String.valueOf(thuoc.getTyLeQuyDoi()));
			vector.add(String.valueOf(thuoc.getGiaBanChan()));
			vector.add(String.valueOf(thuoc.getGiaBanLe()));
			vector.add(thuoc.getLieuLuongDung());
			vector.add(thuoc.getMoTa());
			vector.add(String.valueOf(thuoc.getVat()));
			vector.add(String.valueOf(thuoc.getSoLuongTon()));
			if (thuoc.getTrangThai() == true)
				vector.add("Còn bán");
			else
				vector.add("Ngừng bán");
			tableModel.addRow(vector);
		}
		tbDSThuoc.updateUI();
		tbDSThuoc.clearSelection();
	}
	public void xoaRow() {
		((DefaultTableModel)tbDSThuoc.getModel()).setRowCount(0);
	}
	
	public void LamMoiBT(List<ThuocDTO> dsthuoc) {
		thuocs.clear();
		thuocs.addAll(dsthuoc);
	}
	
	public void lamMoiDSLoaiThuoc() {
		List<NhomThuoc> arrayList = loaiThuocDAO.layDanhSachLoaiThuoc();
		loaiThuocs.clear();
		loaiThuocs.addAll(arrayList);
	}

	public void lamMoiCBBoxLoaiThuocVaCBBoxTheoLoaiThuoc() {
		cbLoai.removeAllItems();
		cbTheoLoai.removeAllItems();
		for (NhomThuoc loaiThuoc : loaiThuocs) {
			cbLoai.addItem(loaiThuoc.getTenNhomThuoc());
			cbTheoLoai.addItem(loaiThuoc.getTenNhomThuoc());
		}
	}

	public void lamMoiDSNSX() {
		List<NhaCungCap> arrayList = nhaSanXuatDAO.layDanhSachNSX();
		nhaSanXuats.clear();
		nhaSanXuats.addAll(arrayList);
	}

	public void lamMoiCBBoxNSXVaCBBoxTheoNSX() {
		cbNhaSX.removeAllItems();
		cbTheoNSX.removeAllItems();
		for (NhaCungCap nhaSanXuat : nhaSanXuats) {
			cbNhaSX.addItem(nhaSanXuat.getTenNCC());
			cbTheoNSX.addItem(nhaSanXuat.getTenNCC());
		}
	}
//
	public void clearAll() {
		lamMoiPanelTTThuoc();
		lamMoiPanelXuLy();
	}

	public void lamMoiPanelTTThuoc() {
		txtMaLoThuoc.setText("");
		cbTenThuoc.setSelectedItem(null);
		cbLoai.setSelectedItem(null);
		txtHoatChatChinh.setText("");
		cbNhaSX.setSelectedItem(null);
		txtXuatXu.setText("");
		cbDonVi.setSelectedItem(null);
		cbDonViQuyDoi.setSelectedItem(null);
		txtTyLeQuyDoi.setText("");
		txtGiaBanChan.setText("");
		txtGiaBanLe.setText("");
		txtLieuLuongDung.setText("");
		txtMoTa.setText("");
		txtVat.setText("");
		txtSoLuongTon.setText("");
		cbTrangThai.setSelectedItem(null);
		datePickerImpl.getJFormattedTextField().setText("");
		datePickerImpl1.getJFormattedTextField().setText("");
		
	}

	public void lamMoiPanelXuLy() {
		cbXuLy.setSelectedIndex(0);
		//txtTheoMa.setText("");
		txtTheoTen.setText("");
		txtTheoHoatChat.setText("");
	}

//	public void xuLyCBDonVi() {
//		if (cbXuLy.getSelectedIndex() == 0 || cbXuLy.getSelectedIndex() == 3) {
//			cbDonVi.setEnabled(false);
//			cbDonViQuyDoi.setEnabled(false);
//			cbTrangThai.setEnabled(false);
//			return;
//		}
//		cbDonVi.setEnabled(true);
//		String selectedItem = (String) cbDonVi.getSelectedItem();
//		if (selectedItem.equals("Lá»�") || selectedItem.equals("ViÃªn") || selectedItem.equals("GÃ³i")) {
//			cbDonViQuyDoi.setSelectedItem("");
//			cbDonViQuyDoi.setEnabled(false);
//			txtGiaTriQuyDoi.setText("");
//			txtGiaTriQuyDoi.setEditable(false);
//			cbDonViQuyDoi.setBorder(BorderFactory.createEmptyBorder());
//			txtGiaTriQuyDoi.setBorder(BorderFactory.createEmptyBorder());
//		} else {
//			cbDonViQuyDoi.setEnabled(true);
//			txtGiaTriQuyDoi.setEditable(true);
//			cbDonViQuyDoi.setSelectedIndex(0);
//			cbDonViQuyDoi.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtGiaTriQuyDoi.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//		}
//
//	}
//
//	public void xuLyCBXuLy() {
//		String selectedItem = (String) cbXuLy.getSelectedItem();
//		btnXuLy.setText(selectedItem);
//		if (selectedItem.equals("ThÃªm thuá»‘c")) {
//			unlockForNhapThuoc();
//
//			txtSoLuong.setText("");
//			txtSoLuong.setEditable(false);
//			cbTrangThai.setSelectedIndex(0);
//			cbTrangThai.setEnabled(false);
////			datePickerImpl.getJFormattedTextField().setText("");
////			datePickerImpl.getComponent(1).setEnabled(false);
//
//			cbDonVi.setEnabled(true);
//			cbDonVi.setSelectedIndex(cbDonVi.getSelectedIndex());
//
//			txtTenThuoc.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbLoai.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDangBaoChe.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDuongDung.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbNhaSX.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtHoatChat.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtHamLuong.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtGiaBan.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDonVi.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//
//			//datePickerImpl.setBorder(BorderFactory.createEmptyBorder());
//			txtSoLuong.setBorder(BorderFactory.createEmptyBorder());
//			cbTrangThai.setBorder(BorderFactory.createEmptyBorder());
//			lamMoiPanelTTThuoc();
//			lamMoiDSThuoc();
//			lamMoiBangThuoc();
//		} else if (selectedItem.equals("Cáº­p nháº­t")) {
//			unlockForNhapThuoc();
//
//			txtSoLuong.setText("");
//			txtSoLuong.setEditable(false);
//			cbTrangThai.setSelectedIndex(0);
//			cbTrangThai.setEnabled(true);
////			datePickerImpl.getJFormattedTextField().setText("");
////			datePickerImpl.getComponent(1).setEnabled(false);
//
//			cbDonVi.setEnabled(true);
//			cbDonVi.setSelectedIndex(cbDonVi.getSelectedIndex());
//
//			txtTenThuoc.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbLoai.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDangBaoChe.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDuongDung.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbNhaSX.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtHoatChat.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtHamLuong.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtGiaNhap.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			txtGiaBan.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbDonVi.setBorder(BorderFactory.createLineBorder(Color.green, 2));
////			txtSoLuong.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//			cbTrangThai.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//
//			txtSoLuong.setBorder(BorderFactory.createEmptyBorder());
//			//datePickerImpl.setBorder(BorderFactory.createEmptyBorder());
//			int selectedRow = tbDSThuoc.getSelectedRow();
//			lamMoiDSThuoc();
//			lamMoiBangThuoc();
//			if (selectedRow != -1)
//				tbDSThuoc.setRowSelectionInterval(selectedRow, selectedRow);
//		} else if (selectedItem.equals("Nháº­p thuá»‘c")) {
//			txtSoLuong.setText("");
//			txtSoLuong.setEditable(true);
//			cbTrangThai.setSelectedIndex(0);
//			cbTrangThai.setEnabled(true);
////			datePickerImpl.getJFormattedTextField().setText("");
////			datePickerImpl.getComponent(1).setEnabled(true);
//
//			lockForNhapThuoc();
//			cbDonVi.setEnabled(false);
//			cbDonViQuyDoi.setEnabled(false);
//			cbTrangThai.setEnabled(false);
//
//			txtSoLuong.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//		//	datePickerImpl.setBorder(BorderFactory.createLineBorder(Color.green, 2));
//
//			txtTenThuoc.setBorder(BorderFactory.createEmptyBorder());
//			cbLoai.setBorder(BorderFactory.createEmptyBorder());
//			cbDangBaoChe.setBorder(BorderFactory.createEmptyBorder());
//			cbDuongDung.setBorder(BorderFactory.createEmptyBorder());
//			cbNhaSX.setBorder(BorderFactory.createEmptyBorder());
//			txtHoatChat.setBorder(BorderFactory.createEmptyBorder());
//			txtHamLuong.setBorder(BorderFactory.createEmptyBorder());
//			txtGiaNhap.setBorder(BorderFactory.createEmptyBorder());
//			txtGiaBan.setBorder(BorderFactory.createEmptyBorder());
//			cbDonVi.setBorder(BorderFactory.createEmptyBorder());
//			cbDonViQuyDoi.setBorder(BorderFactory.createEmptyBorder());
//			txtGiaTriQuyDoi.setBorder(BorderFactory.createEmptyBorder());
//			cbTrangThai.setBorder(BorderFactory.createEmptyBorder());
//			int selectedRow = tbDSThuoc.getSelectedRow();
//			lamMoiDSThuoc();
//			lamMoiBangThuoc();
//			if (selectedRow != -1)
//				tbDSThuoc.setRowSelectionInterval(selectedRow, selectedRow);
//		}
//	}
//
//	public void lockForNhapThuoc() {
//		txtTenThuoc.setEditable(false);
//		txtHoatChat.setEditable(false);
//		txtHamLuong.setEditable(false);
//		txtGiaNhap.setEditable(false);
//		txtGiaBan.setEditable(false);
//		txtGiaTriQuyDoi.setEditable(false);
//
//		cbLoai.setEnabled(false);
//		cbNhaSX.setEnabled(false);
//		cbDangBaoChe.setEnabled(false);
//		cbDuongDung.setEnabled(false);
//
//	}
//
//	public void unlockForNhapThuoc() {
//		txtTenThuoc.setEditable(true);
//		txtHoatChat.setEditable(true);
//		txtHamLuong.setEditable(true);
//		txtGiaNhap.setEditable(true);
//		txtGiaBan.setEditable(true);
//		txtGiaTriQuyDoi.setEditable(true);
//
//		cbLoai.setEnabled(true);
//		cbNhaSX.setEnabled(true);
//		cbDangBaoChe.setEnabled(true);
//		cbDuongDung.setEnabled(true);
//	}
//
//	public void moDialogQLLoaiThuoc() {
//		DiaLogLoaiThuoc loaiThuoc = new DiaLogLoaiThuoc(frame);
//		loaiThuoc.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				lamMoiDSLoaiThuoc();
//				lamMoiCBBoxLoaiThuocVaCBBoxTheoLoaiThuoc();
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				lamMoiDSLoaiThuoc();
//				lamMoiCBBoxLoaiThuocVaCBBoxTheoLoaiThuoc();
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		loaiThuoc.setVisible(true);
//	}
//
//	public void moDialogQLNSX() {
//		JDialog dialog = new JDialog(frame);
//		dialog.add(new PanelQuanLyNSX());
//		dialog.setSize(1300, 590);
//		dialog.setResizable(false);
//		dialog.setLocationRelativeTo(null);
//
//		dialog.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				lamMoiDSNSX();
//				lamMoiCBBoxNSXVaCBBoxTheoNSX();
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				lamMoiDSNSX();
//				lamMoiCBBoxNSXVaCBBoxTheoNSX();
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		dialog.setVisible(true);
//	}
//
//	public void moDialogThuHoiThuocHetHan() {
//		JDialog dialog = new JDialog(frame);
//		dialog.add(new PanelThuHoiThuocHetHan());
//		dialog.setSize(1200, 500);
//		dialog.setResizable(false);
//		dialog.setLocationRelativeTo(null);
//
//		dialog.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				lamMoiDSThuoc();
//				lamMoiBangThuoc();
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				lamMoiDSThuoc();
//				lamMoiBangThuoc();
//			}
//
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		dialog.setVisible(true);
//	}
//
//	public void xuLyThemThuoc() {
//		if (kiemTraDuLieuThuoc() == false)
//			return;
//		Thuoc thuoc = chuyenDuLieuSangThuoc();
//		if (thuoc == null)
//			return;
//		if (ThuocDAO.themThuoc(thuoc)) {
//			JOptionPane.showMessageDialog(this, "ThÃªm thuá»‘c thÃ nh cÃ´ng!");
//			lamMoiDSThuoc();
//			lamMoiBangThuoc();
//			int rs = JOptionPane.showOptionDialog(this, "Báº¡n muá»‘n tiáº¿p tá»¥c thÃªm thuá»‘c má»›i khÃ´ng?", "ThÃªm thuá»‘c má»›i",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//			if (rs == JOptionPane.YES_OPTION)
//				lamMoiPanelTTThuoc();
//			else
//				tbDSThuoc.setRowSelectionInterval(thuocs.indexOf(thuoc), thuocs.indexOf(thuoc));
//		} else
//			JOptionPane.showMessageDialog(this, "ThÃªm thuá»‘c khÃ´ng thÃ nh cÃ´ng!");
//	}
//
//	public Thuoc chuyenDuLieuSangThuoc() {
//		boolean kq;
//		String ma = txtMaThuoc.getText().trim();
//		String ten = txtTenThuoc.getText().trim();
//		String dangBaoChe = (String) cbDangBaoChe.getSelectedItem();
//		String duongDung = (String) cbDuongDung.getSelectedItem();
//		String maLoai = loaiThuocs.get(cbLoai.getSelectedIndex()).getMaLoai();
//		String maNSX = nhaSanXuats.get(cbNhaSX.getSelectedIndex()).getMaNSX();
//		String hoatChat = txtHoatChat.getText().trim();
//		String hamLuong = txtHamLuong.getText().trim();
//		double giaNhap = Double.parseDouble(txtGiaNhap.getText());
//		double giaBan = Double.parseDouble(txtGiaBan.getText());
//		String donVi = (String) cbDonVi.getSelectedItem();
//		String trangThai = (String) cbTrangThai.getSelectedItem();
//		String donViQuyDoi;
//		int giaTriQuyDoi;
//		if (kiemTraQuyDoi() == false) {
//			donViQuyDoi = null;
//			giaTriQuyDoi = 0;
//		} else {
//			donViQuyDoi = (String) cbDonViQuyDoi.getSelectedItem();
//			if (kiemTraKieuSo(txtGiaTriQuyDoi.getText()) == false) {
//				JOptionPane.showMessageDialog(this, "GiÃ¡ trá»‹ quy Ä‘á»•i pháº£i lÃ  kiá»ƒu sá»‘!");
//				return null;
//			}
//			giaTriQuyDoi = Integer.parseInt(txtGiaTriQuyDoi.getText());
//		}
//		Thuoc thuoc = new Thuoc(ma, ten, new NhomThuoc(maLoai), giaNhap, giaBan, dangBaoChe, hoatChat, hamLuong,
//				new NhaCungCap(maNSX), donVi, donViQuyDoi, giaTriQuyDoi, duongDung, trangThai);
//		return thuoc;
//	}
//
	public boolean kiemTraDuLieuThuoc() {
		if (cbTenThuoc.getSelectedItem().toString() == null) {
			JOptionPane.showMessageDialog(this, "Tên thuốc không để trống!");
			cbTenThuoc.requestFocus();
			return false;
		} else if (kiemTraTenThuoc(cbTenThuoc.getSelectedItem().toString())==false) {
			JOptionPane.showMessageDialog(this, "Tên thuốc phải là chữ!");
			cbTenThuoc.requestFocus();
			return false;
		}
		else if (kiemTraHCC(txtHoatChatChinh.getText().trim())==false) {
			JOptionPane.showMessageDialog(this, "Hoạt chất chính phải là chữ!");
			txtHoatChatChinh.requestFocus();
			return false;
		}
		
		else if (txtHoatChatChinh.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Hoạt chất chính không để trống!");
			txtHoatChatChinh.requestFocus();
			return false;
		}
		else if (kiemTraNgaySanXuat()==false) {
			JOptionPane.showMessageDialog(this, "Ngày sản xuất phải nhỏ hơn ngày hiện tại!");
			datePickerImpl.requestFocus();
			return false;
		}
		 else if (kiemTraHSD()==false) {
				JOptionPane.showMessageDialog(this, "Hạn sử dụng phải lớn hơn ngày hiện tại!");
				datePickerImpl1.requestFocus();
				return false;
			} 
		 else if (kiemTraMoTa(txtMoTa.getText().trim())==false) {
				JOptionPane.showMessageDialog(this, "Mô tả phải là chữ!");
				txtMoTa.requestFocus();
				return false;
			} 
		else if (kiemTraLLD(txtLieuLuongDung.getText().trim())==false) {
			JOptionPane.showMessageDialog(this, "Liều lượng dùng phải là chữ!");
			txtLieuLuongDung.requestFocus();
			return false;
		} 
		else if (txtSoLuongTon.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng tồn không để trống!");
			txtSoLuongTon.requestFocus();
			return false;
		}
		else if (kiemTraVAT(txtVat.getText().trim())==false) {
			JOptionPane.showMessageDialog(this, "VAT phải là số!");
			txtVat.requestFocus();
			return false;
		}

		else if (txtVat.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "VAT không để trống!");
			txtVat.requestFocus();
			return false;
		}

		else if (kiemTraKieuSo(txtTyLeQuyDoi.getText().trim())==false) {
			JOptionPane.showMessageDialog(this, "Tỷ lệ quy đổi phải là số!");
			txtTyLeQuyDoi.requestFocus();
			return false;
		}
		else if (txtTyLeQuyDoi.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tỷ lệ quy đổi không để trống!");
			txtTyLeQuyDoi.requestFocus();
			return false;
		}
		else if ( kiemTraDVQD(cbDonViQuyDoi.getSelectedItem().toString())==false) {
			JOptionPane.showMessageDialog(this, "Đơn vị quy đổi phải là chữ!");
			cbDonVi.requestFocus();
			return false;
		}
		
		else if (cbDonViQuyDoi.getSelectedItem().toString() ==null) {
			JOptionPane.showMessageDialog(this, "Đơn vị quy đổi không để trống!");
			cbDonViQuyDoi.requestFocus();
			return false;
		}
		else if ( kiemTraDVT(cbDonVi.getSelectedItem().toString())==false) {
			JOptionPane.showMessageDialog(this, "Đơn vị tính phải là chữ!");
			cbDonVi.requestFocus();
			return false;
		}
		
		else if (cbDonVi.getSelectedItem().toString() ==null) {
			JOptionPane.showMessageDialog(this, "Đơn vị tính không để trống!");
			cbDonVi.requestFocus();
			return false;
		} else if (KiemTraKieuSoDouble(txtGiaBanChan.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Giá bán chẵn phải là số!");
			txtGiaBanChan.requestFocus();
			return false;
		} else if (txtGiaBanChan.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giá bán chẵn không để trống!");
			txtGiaBanChan.requestFocus();
			return false;
		}
		else if (KiemTraKieuSoDouble(txtGiaBanLe.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Giá bán lẻ phải là số!");
			txtGiaBanLe.requestFocus();
			return false;
		} else if (txtGiaBanLe.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giá bán lẻ không để trống!");
			txtGiaBanLe.requestFocus();
			return false;
		}
		else
			return true;

	}

	public boolean kiemTraTenThuoc(String ten) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";						
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ten);
		return matcher.matches();
	}

	public boolean kiemTraKieuSo(String input) {
		String reg = "^[0-9]+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public boolean KiemTraKieuSoDouble(String input) {
		String regex = "^[0-9]+.?[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public boolean kiemTraHCC(String hcc) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";						
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(hcc);
		return matcher.matches();
	}
	
	public boolean kiemTraVAT(String vat) {
		String regex = "^[0-9]+.?[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(vat);
		return matcher.matches();
	}

	public boolean kiemTraMoTa(String mota) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";				
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mota);
		return matcher.matches();
	}
	
	public boolean kiemTraTimTheoTen(String ten) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";				
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ten);
		return matcher.matches();
	}
	
	public boolean kiemTraTimTheoHCC(String hcc) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";				
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(hcc);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
//		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";
//		if(mota.matches(regex)==true) {
//			System.out.println("true");
//		} else
//			System.out.println("false");				
	}
	
	public boolean kiemTraLLD(String lld) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lld);
		return matcher.matches();
	}
	
	public boolean kiemTraDVQD(String dvqd) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";				
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dvqd);
		return matcher.matches();
	}
	
	public boolean kiemTraDVT(String dvt) {
		String regex = "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+)|([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ:,%+-]+[\\s]*)+$";				
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dvt);
		return matcher.matches();
	}
	
	public boolean kiemTraNgaySanXuat() {
		LocalDate hsd = LocalDate.of(datePickerImpl.getModel().getYear(), datePickerImpl.getModel().getMonth() + 1,
				datePickerImpl.getModel().getDay());
		if (hsd.isBefore(LocalDate.now()))
			return true;
		else
			return false;
	}
	
	public boolean kiemTraHSD() {
		LocalDate hsd = LocalDate.of(datePickerImpl1.getModel().getYear(), datePickerImpl1.getModel().getMonth() + 1,
				datePickerImpl1.getModel().getDay());
		if (hsd.isAfter(LocalDate.now()))
			return true;
		else
			return false;
	}
	

//	public void xuLyXoaThuoc() {
//		int index = tbDSThuoc.getSelectedRow();
//		if (index == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lÃ²ng chá»�n thuá»‘c cáº§n xÃ³a");
//			return;
//		}
//		int ans = JOptionPane.showOptionDialog(this, "Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a thuá»‘c?", "XÃ³a thuá»‘c",
//				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//		if (ans == JOptionPane.NO_OPTION)
//			return;
//		boolean kq = ThuocDAO.xoaThuoc(txtMaThuoc.getText());
//		if (kq) {
//			JOptionPane.showMessageDialog(this, "XÃ³a thuá»‘c thÃ nh cÃ´ng!");
//			lamMoiUI();
//		} else
//			JOptionPane.showMessageDialog(this, "XÃ³a thuá»‘c khÃ´ng thÃ nh cÃ´ng!");
//	}
//
//	public void xuLyCapNhatThuoc() {
//		int index = tbDSThuoc.getSelectedRow();
//		if (index == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lÃ²ng chá»�n thuá»‘c cáº§n cáº­p nháº­t");
//			return;
//		}
//		if (kiemTraDuLieuThuoc() == false)
//			return;
//		Thuoc thuoc = chuyenDuLieuSangThuoc();
//		if (thuoc == null)
//			return;
//		int ans = JOptionPane.showOptionDialog(this, "Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n cáº­p nháº­t thuá»‘c?", "Cáº­p nháº­t thuá»‘c",
//				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//		if (ans == JOptionPane.NO_OPTION)
//			return;
//		if (ThuocDAO.capNhatThuoc(thuoc)) {
//			JOptionPane.showMessageDialog(this, "Cáº­p nháº­t thuá»‘c thÃ nh cÃ´ng!");
//			lamMoiDSThuoc();
//			lamMoiBangThuoc();
//			tbDSThuoc.setRowSelectionInterval(index, index);
//		} else
//			JOptionPane.showMessageDialog(this, "Cáº­p nháº­t thuá»‘c khÃ´ng thÃ nh cÃ´ng!");
//	}
//
	public void xuLyNhapThuoc() {
		int soluong = Integer.parseInt(txtSoLuongTon.getText().trim());
		double vat = Double.parseDouble(txtVat.getText().trim());
		double gbc = Double.parseDouble(txtGiaBanChan.getText().trim());
		double gbl = Double.parseDouble(txtGiaBanLe.getText().trim());
		int tylequydoi = Integer.parseInt(txtTyLeQuyDoi.getText().trim());
		
		if (tylequydoi < 0) {
			JOptionPane.showMessageDialog(this, "Tỷ lệ quy đổi không để âm!");
			return;
		}
		
		if (gbc < 0) {
			JOptionPane.showMessageDialog(this, "Giá bán chẵn không để âm!");
			return;
		}
		if (gbl < 0) {
			JOptionPane.showMessageDialog(this, "Giá bán lẻ không để âm!");
			return;
		}
		if (vat < 0) {
			JOptionPane.showMessageDialog(this, "VAT không để âm!");
			return;
		}
		
		if (soluong < 0) {
			JOptionPane.showMessageDialog(this, "Số lượng tồn không để âm!");
			return;
		}
		
		if(kiemTraDuLieuThuoc()==false) {
			return;
		}
		if(thuocDAO.timThuocTheoTen(cbTenThuoc.getSelectedItem().toString()) !=null) {
			if (thuocDAO.themLoThuoc(getLoThuocMoi())==true) {
				JOptionPane.showMessageDialog(this, "Nhập lô thuốc thành công!");
				lamMoiUI();
			} else
				JOptionPane.showMessageDialog(this, "Nhập thuốc không thành công!");

		} else {
			if (thuocDAO.themThuoc(getThuocMoi())==true && thuocDAO.themLoThuoc(getLoThuocMoi())==true) {
				JOptionPane.showMessageDialog(this, "Nhập thuốc thành công!");
				lamMoiUI();
			} else
				JOptionPane.showMessageDialog(this, "Nhập thuốc không thành công!");
		}
	
	}

	public Thuoc getThuocMoi() {
		//
		NhomThuoc nt = loaiThuocDAO.timNhomThuocTheoTen(cbLoai.getSelectedItem().toString());
		//
		String maT = thuocDAO.getCodeMaThuoc();
		String tenT = cbTenThuoc.getSelectedItem().toString();
		String loaiT = "True";
		String hcc = txtHoatChatChinh.getText().trim();
		String tt = "";
		if(cbTrangThai.getSelectedItem().toString().equals("Còn bán")) {
			tt = "True";
		} 
		else tt ="False";
		Thuoc t = new Thuoc(maT, tenT, loaiT, maT, tt);
		t.setNhomThuoc(nt);
		return t;
	}
	
	public LoThuoc getLoThuocMoi() {
		//
		Thuoc thuoc = thuocDAO.timThuocTheoTen(cbTenThuoc.getSelectedItem().toString());
		//
		NhaCungCap ncc = nhaSanXuatDAO.timNSXTheoTen(cbNhaSX.getSelectedItem().toString());
		//		
		String maLoThuoc = thuocDAO.getCodeMaLoThuoc();
		String xx = txtXuatXu.getText().trim();
		LocalDate nsx = LocalDate.of(datePickerImpl.getModel().getYear(), datePickerImpl.getModel().getMonth()+1, datePickerImpl.getModel().getDay());
		LocalDate hsd = LocalDate.of(datePickerImpl1.getModel().getYear(), datePickerImpl1.getModel().getMonth()+1, datePickerImpl1.getModel().getDay());
		String dvt = cbDonVi.getSelectedItem().toString();
		String dvqd = cbDonViQuyDoi.getSelectedItem().toString();
		int tyQD = Integer.parseInt(txtTyLeQuyDoi.getText().trim());
		double gbc = Double.parseDouble(txtGiaBanChan.getText().trim());
		double gbl = Double.parseDouble(txtGiaBanLe.getText().trim());
		String lld = txtLieuLuongDung.getText().trim();
		String mota = txtMoTa.getText().trim();
		double vat = Double.parseDouble(txtVat.getText().trim());
		int slt = Integer.parseInt(txtTyLeQuyDoi.getText().trim());
		LoThuoc lt = new LoThuoc(maLoThuoc, xx, hsd, nsx, dvt, dvqd, tyQD, gbc, gbl, lld, mota, vat, slt);
		if(thuoc !=null) {
			lt.setMaThuoc(thuoc);
		}
		else {
			lt.setMaThuoc(getThuocMoi());
		}
		lt.setNhaCungCap(ncc);
		return lt;
	}

	public void xuLyTim() {
		if(kiemTraTimTheoHCC(txtTheoHoatChat.getText().trim())==false) {
			JOptionPane.showConfirmDialog(this, "Hoạt chất chính phải là chữ");
			return;
		}
		if(kiemTraTimTheoTen(txtTheoTen.getText().trim())==false) {
			JOptionPane.showConfirmDialog(this, "Tên thuốc phải là chữ");
			return;
		}
		if(!txtTheoTen.getText().trim().equals("") && cbTheoLoai.getSelectedIndex() != 0
				&& !txtTheoHoatChat.getText().trim().equals("") && cbTheoNSX.getSelectedItem() !=null)
			xuLyTimTheoTenLoaiHoatChatVaNSX();
		else if (!txtTheoTen.getText().trim().equals("") && cbTheoLoai.getSelectedItem() !=null
				&& !txtTheoHoatChat.getText().trim().equals(""))
			xuLyTimTheoTenLoaiVaHoatChat();
		else if (!txtTheoTen.getText().trim().equals("") && cbTheoLoai.getSelectedItem() != null
				&& cbTheoNSX.getSelectedItem() !=null)
			xuLyTimTheoTenLoaiVaNSX();
		else if (!txtTheoTen.getText().trim().equals("") && !txtTheoHoatChat.getText().trim().equals("")
				&& cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoTenHoatChatVaNSX();
		else if (cbTheoLoai.getSelectedItem() != null && !txtTheoHoatChat.getText().trim().equals("")
				&& cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoLoaiHoatChatVaNSX();
		else if (!txtTheoTen.getText().trim().equals("") && cbTheoLoai.getSelectedItem() != null)
			xuLyTimTheoTenVaLoai();
		else if (!txtTheoTen.getText().trim().equals("") && !txtTheoHoatChat.getText().trim().equals(""))
			xuLyTimTheoTenVaHoatChat();
		else if (!txtTheoTen.getText().trim().equals("") && cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoTenVaNSX();
		else if (cbTheoLoai.getSelectedItem() != null && !txtTheoHoatChat.getText().trim().equals(""))
			xuLyTimTheoLoaiVaHoatChat();
		else if (cbTheoLoai.getSelectedItem() != null && cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoLoaiVaNSX();
		else if (!txtTheoHoatChat.getText().trim().equals("") && cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoHoatChatVaNSX();
		else if (!txtTheoTen.getText().trim().equals(""))
			xuLyTimTheoTen();
		else if (cbTheoLoai.getSelectedItem() != null)
			xuLyTimTheoLoai();
		else if (!txtTheoHoatChat.getText().trim().equals(""))
			xuLyTimTheoHoatChat();
		else if (cbTheoNSX.getSelectedItem() != null)
			xuLyTimTheoNSX();
	}


	public void xuLyTimTheoTenLoaiHoatChatVaNSX() {
		String ten = txtTheoTen.getText().trim();
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String hoatChat = txtTheoHoatChat.getText().trim();
		String tenNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenAndLoaiAndHoatChatAndNSX(ten, tenloai, hoatChat, tenNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenLoaiVaHoatChat() {
		String ten = txtTheoTen.getText().trim();
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String hoatChat = txtTheoHoatChat.getText().trim();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenLoaiVaHoatChat(ten, tenloai, hoatChat);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenLoaiVaNSX() {
		String ten = txtTheoTen.getText().trim();
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String tenNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenLoaiVaNSX(ten, tenloai, tenNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenHoatChatVaNSX() {
		String ten = txtTheoTen.getText().trim();
		String hoatChat = txtTheoHoatChat.getText().trim();
		String maNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenHoatChatVaNSX(ten, hoatChat, maNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoLoaiHoatChatVaNSX() {
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String hoatChat = txtTheoHoatChat.getText().trim();
		String maNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoLoaiHoatChatVaNSX(tenloai, hoatChat, maNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenVaLoai() {
		String ten = txtTheoTen.getText().trim();
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenVaLoai(ten, tenloai);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenVaHoatChat() {
		String ten = txtTheoTen.getText().trim();
		String hoatChat = txtTheoHoatChat.getText().trim();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenVaHoatChat(ten, hoatChat);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTenVaNSX() {
		String ten = txtTheoTen.getText().trim();
		String maNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoTenVaNSX(ten, maNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoLoaiVaHoatChat() {
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String hoatChat = txtTheoHoatChat.getText().trim();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoLoaiVaHoatChat(tenloai, hoatChat);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoLoaiVaNSX() {
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		String maNSX = cbTheoNSX.getSelectedItem().toString();				
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoLoaiVaNSX(tenloai, maNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoHoatChatVaNSX() {
		String hoatChat = txtTheoHoatChat.getText().trim();
		String maNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.xuLyTimTheoHoatChatVaNSX(hoatChat, maNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoTen() {
		String ten = txtTheoTen.getText().trim();
		List<ThuocDTO> dsthuoc = thuocDAO.layDanhSachThuocTheoTenThuoc(ten);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoLoai() {
		String tenloai = cbTheoLoai.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.layDanhSachThuocTheoLoaiThuoc(tenloai);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoHoatChat() {
		String hcc = txtHoatChatChinh.getText().trim();
		List<ThuocDTO> dsthuoc = thuocDAO.layDanhSachThuocTheoHoatChatChinh(hcc);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

	public void xuLyTimTheoNSX() {
		String tenNSX = cbTheoNSX.getSelectedItem().toString();
		List<ThuocDTO> dsthuoc = thuocDAO.timThuocTheoNCC(tenNSX);
		if(dsthuoc != null) {
			xoaRow();
			LamMoiBT(dsthuoc);
			lamMoiBangThuoc();
		}
		else JOptionPane.showConfirmDialog(this,"Không tìm thấy danh sách thuốc!","Thông báo" ,JOptionPane.CANCEL_OPTION);

	}

//	public void lamMoiBangThuocKhiTim(ArrayList<Thuoc> temp) {
//		lamMoiPanelTTThuoc();
//		thuocs.clear();
//		thuocs.addAll(temp);
//		lamMoiBangThuoc();
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
//		int index = tbDSThuoc.getSelectedRow();
//
//		Thuoc thuoc = thuocs.get(index);
//		txtMaThuoc.setText(thuoc.getMaThuoc());
//		txtTenThuoc.setText(thuoc.getTenThuoc());
//		cbLoai.setSelectedIndex(loaiThuocs.indexOf(new NhomThuoc(thuoc.getLoai().getMaLoai())));
//		cbDangBaoChe.setSelectedItem(thuoc.getDangBaoChe());
//		cbDuongDung.setSelectedItem(thuoc.getDuongDung());
//		cbNhaSX.setSelectedIndex(nhaSanXuats.indexOf(new NhaCungCap(thuoc.getNhaSanXuat().getMaNSX())));
//		txtHoatChat.setText(thuoc.getHoatChat());
//		txtHamLuong.setText(thuoc.getHamLuong());
////		txtGiaNhap.setText(String.format("%.0f", thuoc.getGiaNhap()));
////		txtGiaBan.setText(String.format("%.0f", thuoc.getGiaBan()));
//		cbDonVi.setSelectedItem(thuoc.getDonVi());
//		if (thuoc.getDonViQuyDoi() != null) {
//			txtGiaNhap.setText(String.format("%.0f", thuoc.getGiaNhap() * thuoc.getGiaTriQuyDoi()));
//			txtGiaBan.setText(String.format("%.0f", thuoc.getGiaBan() * thuoc.getGiaTriQuyDoi()));
//			cbDonViQuyDoi.setSelectedItem(thuoc.getDonViQuyDoi());
//			txtGiaTriQuyDoi.setText(String.valueOf(thuoc.getGiaTriQuyDoi()));
//		} else {
//			txtGiaNhap.setText(String.format("%.0f", thuoc.getGiaNhap()));
//			txtGiaBan.setText(String.format("%.0f", thuoc.getGiaBan()));
//			txtGiaTriQuyDoi.setText("");
//			cbDonViQuyDoi.setSelectedItem("");
//		}
////		if (!cbXuLy.getSelectedItem().equals("Nháº­p thuá»‘c"))
////			txtSoLuong.setText(String.valueOf(thuoc.getSoLuong()));
////		else
//		txtSoLuong.setText("");
//		cbTrangThai.setSelectedItem(thuoc.getTrangThai());
//	}

}
