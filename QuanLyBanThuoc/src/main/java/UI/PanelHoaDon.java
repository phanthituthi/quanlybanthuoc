package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import DAO.HoaDonDAO;
import DAO.NhanVienDAO;
import Model.ChiTietHoaDon;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhomThuoc;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.Thuoc;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelHoaDon extends JPanel implements ActionListener, MouseListener, KeyListener {
	private String getjd = "";
	private String[] spljd = {};
	private NhanVien nhanVien;
	int soluong = 1, gtqd = 1, stt = 0, soluongquydoi = 1;
	double dongia = 0, thanhtien = 0, tongthanhtien = 0, VAT = 5, tientra = 0, tongthanhtoan = 0, tiendu = 0;
	private DefaultTableModel model, model1;
	private JTable table, table1;
	private JTextField txttimmakh, txttimtenkh, txttimnsx, txttiml, txttimhc, txtmess, txtsdt, txttim, txthd, txtnl,
			txtmakh, txttenkh, txtsdtkh, txtmanv, txttennv, txtmathuoc, txttenthuoc, txtsoluong, txttongthanhtien,
			txttienkh, txttienthanhtoan, txtthue, txttiendu, txtchuandoan, txtdbc, txthl;
	private JButton btntim, btnxn, btnthem, btnsua, btnxoa, btnxoarong, btnthanhtoan, btnkhoiphuc, btnjdnv, btnlammoi;
	private JComboBox<String> cbtim, cbloc1, cbloc2, cbdonvi, cbtimnsx, cbjdnv;
	private JLabel lbloai, lbdactinh, lbhd, lbnl, lbmakh, lbtenkh, lbsdtkh, lbmanv, lbtennv, lbtenthuoc, lbmathuoc,
			lbsoluong, lbdonvi, lbtongthanhtien, lbtienkh, lbtienthanhtoan, lbthue, lbtiendu, lbchuandoan, lbjdnv;
	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<ChiTietHoaDon> cthd = null;
	int saverow = 0, rowoftable1 = -1;
	private ArrayList<Thuoc> dsthuoc = new ArrayList<Thuoc>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
			//	try {
			//		PanelHoaDon frame = new PanelHoaDon();
			//		frame.setVisible(true);
			//	} catch (Exception e) {
			//		e.printStackTrace();
			//	}
		//	}
		//}/);
//	}

	/**
	 * Create the frame.
	 */
	public PanelHoaDon() {
		//setTitle("Tạo hóa đơn");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setSize(1800, 850);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setResizable(false);

		//JMenuBar menuBar = new JMenuBar();
		////menuBar.setBackground(new Color(220, 220, 220));
		//setJMenuBar(menuBar);

		//JMenu mnTrangCh = new JMenu("Trang chủ");
		//mnTrangCh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//menuBar.add(mnTrangCh);

		//JMenu mnQunL = new JMenu("Quản lý");
		//mnQunL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//menuBar.add(mnQunL);

		//JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nhân viên");
		//mntmNewMenuItem_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmNewMenuItem_1);

		//JMenuItem mntmKhchHng = new JMenuItem("Khách hàng");
		//mntmKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmKhchHng);

	//	JMenuItem mntmThuc = new JMenuItem("Thuốc");
	//	mntmThuc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmThuc);

		//JMenuItem mntmNewMenuItem = new JMenuItem("Nhà sản xuất");
		//mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmNewMenuItem);

	//	JMenuItem mntmHan = new JMenuItem("Hóa đơn");
	//	mntmHan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmHan);

		//JMenu mnThngK = new JMenu("Thống kê");
		//mnThngK.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//menuBar.add(mnThngK);
		contentPane = new JPanel();
	contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pn = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();

		Box bn = Box.createVerticalBox();
		Box bn1 = Box.createHorizontalBox();

		Box bn2full = Box.createHorizontalBox();
		Box bn2 = Box.createVerticalBox();
		Box bn21 = Box.createVerticalBox();

		Box bn3 = Box.createVerticalBox();
		pn2.setBackground(new Color(176, 224, 230));
		pn3.setBackground(new Color(176, 224, 230));

		JLabel label_4 = new JLabel("Số điện thoại:");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bn1.add(label_4);
		bn1.add(txtsdt = new JTextField(15));
		txtsdt.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtsdt.setBackground(Color.WHITE);
		txtsdt.setForeground(new Color(0, 0, 0));
		txtsdt.selectAll();
		txtsdt.requestFocus();
		bn1.add(Box.createHorizontalStrut(5));

		JLabel label_2 = new JLabel("Mã khách hàng:");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bn1.add(label_2);
		bn1.add(txttimmakh = new JTextField(15));
		txttimmakh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bn1.add(Box.createHorizontalStrut(5));
		JLabel label_3 = new JLabel("Tên khách hàng:");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bn1.add(label_3);
		bn1.add(txttimtenkh = new JTextField(15));
		txttimtenkh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		bn1.add(Box.createHorizontalStrut(5));

		bn1.add(btnxn = new JButton("Xác nhận"));
		btnxn.addActionListener(this);
		btnxn.setForeground(new Color(0, 0, 0));
		btnxn.setBackground(new Color(245, 255, 250));
		btnxn.setFont(new Font("Times New Roman", Font.BOLD, 14));

		// bn2.add(lbhd=new JLabel("Mã hóa đơn:"));
		// lbhd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		// bn2.add(txthd=new JTextField(10));
		// txthd.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		// txthd.setEditable(false);
		// bn2.add(lbnl=new JLabel("Ngày lập:"));
		// lbnl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		// bn2.add(txtnl=new JTextField(10));
		// txtnl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// txtnl.setEditable(false);
		bn2.add(Box.createVerticalStrut(20));
		bn2.add(lbmanv = new JLabel("Mã nhân viên:"));
		lbmanv.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn2.add(txtmanv = new JTextField(10));
		txtmanv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtmanv.setEditable(false);
		bn2.add(lbtennv = new JLabel("Tên nhân viên:"));
		lbtennv.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn2.add(txttennv = new JTextField(10));
		txttennv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txttennv.setEditable(false);

		bn2.add(lbmakh = new JLabel("Mã khách hàng:"));
		lbmakh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn2.add(txtmakh = new JTextField(10));
		txtmakh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtmakh.setEditable(false);
		bn2.add(lbtenkh = new JLabel("Tên khách hàng"));
		lbtenkh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn2.add(txttenkh = new JTextField(10));
		txttenkh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txttenkh.setEditable(false);
		bn2.add(lbsdtkh = new JLabel("Số điện thoại:"));
		lbsdtkh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn2.add(txtsdtkh = new JTextField(10));
		txtsdtkh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtsdtkh.setEditable(false);

		bn2.add(Box.createVerticalStrut(20));

		Box bn21a = Box.createHorizontalBox();
		Box bn21b = Box.createHorizontalBox();
		Box bn21c = Box.createHorizontalBox();
		Box bn21d = Box.createHorizontalBox();
		Box bn21e = Box.createHorizontalBox();

		bn21a.add(Box.createHorizontalStrut(10));
		bn21a.add(lbmathuoc = new JLabel("Mã thuốc:"));
		lbmathuoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn21a.add(Box.createHorizontalStrut(10));
		bn21a.add(txtmathuoc = new JTextField(10));
		txtmathuoc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtmathuoc.setEditable(false);

		bn21b.add(Box.createHorizontalStrut(10));
		bn21b.add(lbtenthuoc = new JLabel("Tên thuốc:"));
		lbtenthuoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn21b.add(Box.createHorizontalStrut(10));
		bn21b.add(txttenthuoc = new JTextField(10));
		txttenthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txttenthuoc.setEditable(false);

		Box bn21bb = Box.createHorizontalBox();
		bn21bb.add(Box.createHorizontalStrut(10));
		bn21bb.add(lbchuandoan = new JLabel("Chuẩn đoán:"));
		lbchuandoan.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn21bb.add(Box.createHorizontalStrut(10));
		bn21bb.add(txtchuandoan = new JTextField(10));
		txtchuandoan.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		bn21c.add(Box.createHorizontalStrut(10));
		bn21c.add(lbsoluong = new JLabel("Số lượng:"));
		lbsoluong.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn21c.add(Box.createHorizontalStrut(10));
		bn21c.add(txtsoluong = new JTextField(10));
		txtsoluong.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		bn21d.add(Box.createHorizontalStrut(10));
		bn21d.add(lbdonvi = new JLabel("Đơn vị:"));
		lbdonvi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bn21d.add(Box.createHorizontalStrut(10));
		bn21d.add(cbdonvi = new JComboBox<String>());
		cbdonvi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbdonvi.setBackground(Color.WHITE);
		cbdonvi.setForeground(Color.BLACK);
		cbdonvi.addActionListener(this);

		lbmathuoc.setPreferredSize(lbchuandoan.getPreferredSize());
		lbsoluong.setPreferredSize(lbchuandoan.getPreferredSize());
		lbdonvi.setPreferredSize(lbchuandoan.getPreferredSize());
		lbtenthuoc.setPreferredSize(lbchuandoan.getPreferredSize());

		bn21e.add(btnthem = new JButton("Thêm"));
		bn21e.add(Box.createHorizontalStrut(10));
		btnthem.addActionListener(this);
		btnthem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnthem.setForeground(Color.BLACK);
		btnthem.setBackground(new Color(245, 255, 250));
		bn21e.add(btnsua = new JButton("Sửa"));
		bn21e.add(Box.createHorizontalStrut(10));
		btnsua.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnsua.setForeground(Color.BLACK);
		btnsua.setBackground(new Color(245, 255, 250));
		btnsua.addActionListener(this);
		bn21e.add(btnxoa = new JButton("Xóa"));
		bn21e.add(Box.createHorizontalStrut(10));
		btnxoa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnxoa.setForeground(Color.BLACK);
		btnxoa.setBackground(new Color(245, 255, 250));
		btnxoa.addActionListener(this);
		bn21e.add(btnxoarong = new JButton("Xóa rỗng"));
		btnxoarong.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnxoarong.setForeground(Color.BLACK);
		btnxoarong.setBackground(new Color(245, 255, 250));
		btnxoarong.addActionListener(this);

		bn21.add(Box.createVerticalStrut(20));
		bn21.add(bn21a);
		bn21.add(Box.createVerticalStrut(10));
		bn21.add(bn21b);
		bn21.add(Box.createVerticalStrut(10));
		bn21.add(bn21bb);
		bn21.add(Box.createVerticalStrut(10));
		bn21.add(bn21d);
		bn21.add(Box.createVerticalStrut(10));
		bn21.add(bn21c);
		bn21.add(Box.createVerticalStrut(15));
		bn21.add(bn21e);
		bn21.add(Box.createVerticalStrut(15));
		bn21.add(txtmess = new JTextField(""));
		txtmess.setForeground(Color.RED);
		txtmess.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		txtmess.setBorder(null);
		txtmess.setEditable(false);

		Box b12 = Box.createHorizontalBox();
		Box b12a = Box.createHorizontalBox();
		Box b13 = Box.createHorizontalBox();

		b12.add(cbtim = new JComboBox<String>());
		cbtim.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		cbtim.setBackground(Color.WHITE);
		cbtim.addItem("Tên");
		cbtim.addItem("Mã");
		cbtim.addActionListener(this);
		b12.add(Box.createHorizontalStrut(1));
		b12.add(txttim = new JTextField(11));
		txttim.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		b12.add(Box.createHorizontalStrut(10));
		b12.add(lbdactinh = new JLabel("Hoạt chất:"));
		lbdactinh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		b12.add(Box.createHorizontalStrut(23));
		b12.add(txttimhc = new JTextField(10));
		txttimhc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		// b12.add(cbloc2=new JComboBox<String>());
		// cbloc2.setBackground(Color.WHITE);
		// cbloc2.setForeground(new Color(0, 0, 0));
		// cbloc2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		// cbloc2.addItem("Hạ sốt");
		b12.add(Box.createHorizontalStrut(10));
		JLabel label_6 = new JLabel("Hàm lượng:");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		b12.add(label_6);
		b12.add(Box.createHorizontalStrut(10));
		b12.add(txthl = new JTextField(10));
		txthl.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		// cbtimnsx.setBackground(new Color(255, 255, 255));
		// cbtimnsx.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		// cbtimnsx.addItem("Trung Quốcccccccccccccccccccccccccc");
		// b12.add(Box.createHorizontalStrut(10));
		// b12.add(btntim = new JButton("Tìm kiếm"));
		// btntim.addActionListener(this);
		// btntim.setBackground(new Color(255, 255, 255));
		// btntim.setFont(new Font("Times New Roman", Font.BOLD, 13));

		b12.add(Box.createHorizontalStrut(1));
		b12.add(btnkhoiphuc = new JButton("Khôi phục"));
		btnkhoiphuc.addActionListener(this);
		btnkhoiphuc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnkhoiphuc.setBackground(Color.WHITE);

		// b12a.add(Box.createHorizontalStrut(10));
		b12a.add(lbloai = new JLabel("Loại:"));
		lbloai.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		b12a.add(Box.createHorizontalStrut(21));
		b12a.add(cbloc1 = new JComboBox<String>());
		// b12.add(txttiml=new JTextField(10));
		cbloc1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cbloc1.setBackground(Color.WHITE);
		cbloc1.addItem("");

//		for (NhomThuoc ds : HoaDonDAO.getDSLoaiThuoc()) {
//			cbloc1.addItem(ds.getTenLoai());
//		}
		cbloc1.setPreferredSize(new Dimension(196, 13));
		b12a.add(Box.createHorizontalStrut(10));

		JLabel label_5 = new JLabel("Dạng bào chế:");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		b12a.add(label_5);
		b12a.add(Box.createHorizontalStrut(1));
		b12a.add(txtdbc = new JTextField(10));
		txtdbc.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		b12a.add(Box.createHorizontalStrut(10));
		JLabel label_1 = new JLabel("Nhà sản xuất:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		b12a.add(label_1);
		b12a.add(Box.createHorizontalStrut(1));
		b12a.add(txttimnsx = new JTextField(10));
		txttimnsx.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		b12a.add(Box.createHorizontalStrut(89));

		String[] header = { "Mã thuốc", "Tên thuốc", "Loại", "Dạng bào chế", "Hoạt chất", "Hàm lượng", "Nhà sản xuất",
				"Đơn vị tính", "Số lượng", "Giá bán" };
		model = new DefaultTableModel(header, 0);
		JScrollPane pane = new JScrollPane(table = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(850, 400));
		b13.add(pane);
		bn3.add(b12);
		bn3.add(Box.createVerticalStrut(5));
		bn3.add(b12a);
		bn3.add(Box.createVerticalStrut(10));
		bn3.add(b13);
		pn3.add(bn3);

		TitledBorder tbw = new TitledBorder(BorderFactory.createLineBorder(Color.black), "Thông tin hóa đơn");
		bn2.setBorder(tbw);
		bn.setBackground(new Color(176, 224, 230));
		pn2.add(bn1);
		bn.add(pn2);

		bn2full.add(bn2);

		bn2full.add(bn21);

		bn.add(bn2full);
		TitledBorder tbpn4 = new TitledBorder(BorderFactory.createLineBorder(Color.black), "Tạo danh sách thuốc");
		bn21.setBorder(tbpn4);

		bn.add(pn3);

		pn.add(bn);

		bn21.setPreferredSize(new Dimension(270, 200));
		bn.setPreferredSize(new Dimension(860, 800));
		add(pn, BorderLayout.WEST);

		JPanel pe = new JPanel();
		pe.setBackground(new Color(0, 128, 128));

		Box be = Box.createVerticalBox();

		Box be0 = Box.createHorizontalBox();
		be0.add(Box.createHorizontalStrut(15));
		JLabel label = new JLabel("Chi tiết hóa đơn");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		be0.add(label);

		Box be0a = Box.createHorizontalBox();
		be0a.add(lbhd = new JLabel("Mã hóa đơn:"));
		lbhd.setForeground(new Color(255, 255, 255));
		lbhd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		be0a.add(Box.createHorizontalStrut(5));
		be0a.add(txthd = new JTextField(10));
		txthd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txthd.setForeground(new Color(255, 255, 255));
		txthd.setBackground(new Color(0, 128, 128));
		be0a.add(Box.createHorizontalStrut(10));
		txthd.setBorder(null);
		txthd.setEditable(false);
		be0a.add(lbnl = new JLabel("Ngày lập:"));
		lbnl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbnl.setForeground(new Color(255, 255, 255));
		be0a.add(Box.createHorizontalStrut(5));
		be0a.add(txtnl = new JTextField(10));
		txtnl.setForeground(new Color(255, 255, 255));
		txtnl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtnl.setBackground(new Color(0, 128, 128));
		txtnl.setBorder(null);
		txtnl.setEditable(false);

		Box be1 = Box.createHorizontalBox();
		String[] header1 = { "STT", "Mã thuốc", "Tên thuốc", "Đơn vị", "Số lượng", "Đơn giá", "Thành tiền" };
		model1 = new DefaultTableModel(header1, 0);
		JScrollPane pane1 = new JScrollPane(table1 = new JTable(model1), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		be1.add(pane1);

		Box be2 = Box.createHorizontalBox();
		be2.add(lbtongthanhtien = new JLabel("Tổng thành tiền:"));
		lbtongthanhtien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		be2.add(Box.createHorizontalStrut(10));
		lbtongthanhtien.setForeground(Color.WHITE);
		be2.add(txttongthanhtien = new JTextField(10));
		txttongthanhtien.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txttongthanhtien.setForeground(Color.WHITE);
		txttongthanhtien.setBackground(new Color(0, 128, 128));
		txttongthanhtien.setEditable(false);

		Box be3 = Box.createHorizontalBox();
		be3.add(lbtienkh = new JLabel("Tiền trả:"));
		lbtienkh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		be3.add(Box.createHorizontalStrut(10));
		lbtienkh.setForeground(Color.WHITE);
		be3.add(txttienkh = new JTextField(10));
		txttienkh.setForeground(Color.BLACK);
		txttienkh.addActionListener(this);
		txttienkh.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		Box be4 = Box.createHorizontalBox();
		be4.add(lbtienthanhtoan = new JLabel("Tổng phí thanh toán:"));
		lbtienthanhtoan.setFont(new Font("Times New Roman", Font.BOLD, 14));
		be4.add(Box.createHorizontalStrut(7));
		lbtienthanhtoan.setForeground(Color.WHITE);
		be4.add(txttienthanhtoan = new JTextField(10));
		txttienthanhtoan.setForeground(Color.WHITE);
		txttienthanhtoan.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txttienthanhtoan.setBackground(new Color(0, 128, 128));
		txttienthanhtoan.setEditable(false);

		Box be6 = Box.createHorizontalBox();
		be6.add(lbthue = new JLabel("Thuế:"));
		lbthue.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbthue.setForeground(Color.WHITE);
		be6.add(Box.createHorizontalStrut(10));
		be6.add(txtthue = new JTextField(10));
		txtthue.setText("5%");

		txtthue.setForeground(new Color(255, 255, 255));
		txtthue.setBackground(new Color(0, 128, 128));
		txtthue.setEditable(false);
		txtthue.addActionListener(this);
		txtthue.setFont(new Font("Times New Roman", Font.BOLD, 14));

		Box be7 = Box.createHorizontalBox();
		be7.add(lbtiendu = new JLabel("Số dư:"));
		lbtiendu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		be7.add(Box.createHorizontalStrut(10));
		lbtiendu.setForeground(Color.WHITE);
		be7.add(txttiendu = new JTextField(10));
		txttiendu.setForeground(Color.WHITE);
		txttiendu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txttiendu.setBackground(new Color(0, 128, 128));
		txttiendu.setEditable(false);

		Box be5 = Box.createHorizontalBox();
		be5.add(Box.createHorizontalStrut(15));
		be5.add(btnthanhtoan = new JButton("Thanh toán"));
		btnthanhtoan.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnthanhtoan.setBackground(new Color(245, 255, 250));
		btnthanhtoan.addActionListener(this);

		lbtongthanhtien.setPreferredSize(lbtienthanhtoan.getPreferredSize());
		lbtienkh.setPreferredSize(lbtienthanhtoan.getPreferredSize());
		lbthue.setPreferredSize(lbtienthanhtoan.getPreferredSize());
		lbtiendu.setPreferredSize(lbtienthanhtoan.getPreferredSize());

		be.add(be0);
		be.add(Box.createVerticalStrut(10));
		be.add(be0a);
		be.add(Box.createVerticalStrut(10));
		be.add(be1);
		be1.setPreferredSize(new Dimension(650, 580));
		be.add(Box.createVerticalStrut(10));
		be.add(be2);
		be.add(Box.createVerticalStrut(10));
		be.add(be6);
		be.add(Box.createVerticalStrut(10));
		be.add(be4);
		be.add(Box.createVerticalStrut(10));
		be.add(be3);
		be.add(Box.createVerticalStrut(10));
		be.add(be7);
		be.add(Box.createVerticalStrut(20));

		be.add(be5);
		be.add(Box.createVerticalStrut(30));
		be.setPreferredSize(new Dimension(642, 790));
		pe.add(be);

		add(pe, BorderLayout.EAST);

		// thao tac
		table.addMouseListener(this);
		table1.addMouseListener(this);

		// btnjdnv=new JButton();

		//dsthuoc = HoaDonDAO.layDanhSachThuoc();
		//docfile();

		txttim.addKeyListener(this);
		txttimhc.addKeyListener(this);
		txttimnsx.addKeyListener(this);
		txttim.addKeyListener(this);
		txthl.addKeyListener(this);
		txtdbc.addKeyListener(this);
		cbloc1.addActionListener(this);
		
		
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

//	public void docfile() {
//		DecimalFormat formatter = new DecimalFormat("###,###,###");
//		for (Thuoc a : dsthuoc) {
//			String checkdv = "";
//			if (a.getDonVi().equalsIgnoreCase("Hộp"))
//				checkdv = a.getDonViQuyDoi();
//			else
//				checkdv = a.getDonVi();
//			
//			String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//					a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), checkdv, a.getSoLuong() + "",
//					formatter.format(a.getGiaBan()) };
//			model.addRow(row);
//		}
//
//	}
//
//	public void khoiPhuc() {
//		for (int i = model.getRowCount() - 1; i >= 0; i--) {
//			model.removeRow(i);
//		}
//		docfile();
//		txtmess.setText("");
//		txttim.setText("");
//		txttimhc.setText("");
//		// txttiml.setText("");
//		txttimnsx.setText("");
//	}
//
//	public void setSearch() {
//		if (cbtim.getSelectedIndex() == 1) {
//			txttimhc.setEditable(false);
//			txthl.setEditable(false);
//			txtdbc.setEditable(false);
//			txttimnsx.setEditable(false);
//
//		}
//		if (cbtim.getSelectedIndex() == 0) {
//			txttim.selectAll();
//			txttim.requestFocus();
//			txttim.setText("");
//			txttimhc.setEditable(true);
//			txthl.setEditable(true);
//			txtdbc.setEditable(true);
//			txttimnsx.setEditable(true);
//			docfile();
//		}
//	}
//
//	public void timThuoc() {
//		DecimalFormat formatter = new DecimalFormat("###,###,###");
//
//		if (cbtim.getSelectedIndex() == 1) {
//			if (!(txttim.getText().length() > 0 && txttim.getText().matches("[A-Za-z0-9]+"))) {
//				txtmess.setText("*/ Mã thuốc không hợp lệ !");
//				// txttim.setText("");
//				return;
//			}
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				model.removeRow(i);
//			}
//			txtmess.setText("*/ Không tìm thấy thuốc!");
//			Thuoc a = HoaDonDAO.timThuocTheoMa(txttim.getText());
//			int updatequatity1 = 0;
//			if (a != null) {
//
//				for (Thuoc thuoc : dsthuoc) {
//					if (thuoc.getMaThuoc().equalsIgnoreCase(a.getMaThuoc()))
//						updatequatity1 = thuoc.getSoLuong();
//				}
//				
//				String checkdv1 = "";
//				if (a.getDonVi().equalsIgnoreCase("Hộp"))
//					checkdv1 = a.getDonViQuyDoi();
//				else
//					checkdv1 = a.getDonVi();
//				String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//						a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), checkdv1,
//						updatequatity1 + "", formatter.format(a.getGiaBan()) };
//				model.addRow(row);
//
//				txtmess.setText("");
//
//			} // else
//				// JOptionPane.showMessageDialog(this, "Không tìm thấy hoặc hết hàng");
//		}
//
//		if (cbtim.getSelectedIndex() == 0) {
//
//			ArrayList<Thuoc> ds = HoaDonDAO.timThuocTheoCacTieuChi(txttim.getText(),
//					cbloc1.getSelectedItem().toString(), txttimhc.getText(), txttimnsx.getText(), txthl.getText(),
//					txtdbc.getText());
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				model.removeRow(i);
//			}
//			for (Thuoc a : ds) {
//				int updatequatity2 = 0;
//				for (Thuoc thuoc : dsthuoc) {
//					if (thuoc.getMaThuoc().equalsIgnoreCase(a.getMaThuoc()))
//						updatequatity2 = thuoc.getSoLuong();
//				}
//				String checkdv2 = "";
//				if (a.getDonVi().equalsIgnoreCase("Hộp"))
//					checkdv2 = a.getDonViQuyDoi();
//				else
//					checkdv2 = a.getDonVi();
//				String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//						a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), checkdv2,
//						updatequatity2 + "", formatter.format(a.getGiaBan()) };
//				model.addRow(row);
//			}
//			txtmess.setText("");
//		}
//	}
//
//	public void layThongTinHoaDon() {
//		KhachHang khachhang = traCuuKhachHang1();
//
//		// KhachHang khachhang = HoaDonDAO.layThongTinKhachHang(txttimmakh.getText(),
//		// txttimtenkh.getText(),
//		// txtsdt.getText());
//
//		if (khachhang != null) {
//			 txtmanv.setText(nhanVien.getMaNV());
//			 txttennv.setText(nhanVien.getTenNV());
//			//txtmanv.setText("NV1");
//			//txttennv.setText("Ok");
//			txthd.setText((HoaDonDAO.setCodeOrders() + 1) + "");
//			txtnl.setText(LocalDate.now().toString());
//			txtmakh.setText(khachhang.getMaKH());
//			txttenkh.setText(khachhang.getTenKH());
//			txtsdtkh.setText(khachhang.getSoDT());
//
//		} else {
//			JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
//			txtsdt.selectAll();
//			txtsdt.requestFocus();
//		}
//
//	}
//
//	public void themChiTietHoaDon() {
//		DecimalFormat formatter = new DecimalFormat("###,###,###");
//		int row = table.getSelectedRow();
//		if (txtnl.getText().equalsIgnoreCase("") || txtmathuoc.getText().equalsIgnoreCase("")) {
//			JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng hoặc chưa chọn thuốc");
//			return;
//		}
//		if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//			JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
//			return;
//		}
//		// if (!(txtchuandoan.getText().length() > 0 && txtchuandoan.getText().matches(
//		// "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9
//		// ]+"))) {
//		// JOptionPane.showMessageDialog(this, "Vui lòng nhập chuẩn đoán về khách
//		// hàng");
//		// return;
//		// }
//
//		if (cbdonvi.getSelectedIndex() == 0) {
//			if (Integer.parseInt(txtsoluong.getText()) * gtqd > soluong
//					|| Integer.parseInt(txtsoluong.getText()) == 0) {
//				JOptionPane.showMessageDialog(this, "Không đủ số lượng");
//				return;
//			}
//
//			// soluong=soluong-Integer.parseInt(txtsoluong.getText());
//			// table.setValueAt(soluong,saverow,8);
//
//			for (int i = model1.getRowCount() - 1; i >= 0; i--) {
//				if (txtmathuoc.getText().equalsIgnoreCase(table1.getValueAt(i, 1).toString())) {
//					table1.setValueAt(Integer.parseInt(table1.getValueAt(i, 4).toString())
//							+ Integer.parseInt(txtsoluong.getText()) * gtqd, i, 4);
//					thanhtien = thanhtien + dongia * (Integer.parseInt(txtsoluong.getText()) * gtqd);
//					tongthanhtien = tongthanhtien + dongia * (Integer.parseInt(txtsoluong.getText()) * gtqd);
//					table1.setValueAt(formatter.format(thanhtien), i, 6);
//					txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//					return;
//				}
//			}
//
//			thanhtien = dongia * (Integer.parseInt(txtsoluong.getText()) * gtqd);
//			tongthanhtien = tongthanhtien + thanhtien;
//			String checkdv = "";
//			if (HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getDonVi().equalsIgnoreCase("Hộp"))
//				checkdv = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getDonViQuyDoi();
//			else
//				checkdv = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getDonVi();
//			String[] row0 = { stt + "", txtmathuoc.getText(), txttenthuoc.getText(), checkdv,
//					(Integer.parseInt(txtsoluong.getText())) * gtqd + "", formatter.format(dongia),
//					formatter.format(thanhtien) };
//			model1.addRow(row0);
//			stt++;
//			// for (int i = model.getRowCount()-1;i>=0; i--) {
//			// model.removeRow(i);}
//			// docfile();
//		}
//
//		if (cbdonvi.getSelectedIndex() == 1) {
//			if (Integer.parseInt(txtsoluong.getText()) > soluong || Integer.parseInt(txtsoluong.getText()) == 0) {
//				JOptionPane.showMessageDialog(this, "Không đủ số lượng");
//				return;
//			}
//			soluongquydoi = soluongquydoi - Integer.parseInt(txtsoluong.getText());
//
//			for (int i = model1.getRowCount() - 1; i >= 0; i--) {
//				if (txtmathuoc.getText().equalsIgnoreCase(table1.getValueAt(i, 1).toString())) {
//					// if(table1.getValueAt(i,4).toString().trim().equalsIgnoreCase("Viên"))
//					// {
//					table1.setValueAt(Integer.parseInt(table1.getValueAt(i, 4).toString())
//							+ Integer.parseInt(txtsoluong.getText()), i, 4);
//					thanhtien = thanhtien + dongia * (Integer.parseInt(txtsoluong.getText()));
//					tongthanhtien = tongthanhtien + dongia * (Integer.parseInt(txtsoluong.getText()));
//					table1.setValueAt(formatter.format(thanhtien), i, 6);
//					txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//					return;
//					// }
//
//					// else {
//					// table1.setValueAt(Integer.parseInt(table1.getValueAt(i,3).toString())+Integer.parseInt(txtsoluong.getText()),i,3);
//					// thanhtien=thanhtien+(dongia/gtqd)*(Integer.parseInt(txtsoluong.getText()));
//					// tongthanhtien=tongthanhtien+(dongia/gtqd)*(Integer.parseInt(txtsoluong.getText()));
//					// table1.setValueAt(formatter.format(thanhtien),i,7);
//					// txttongthanhtien.setText(formatter.format(tongthanhtien)+" VNĐ");
//					// return;
//					// }
//				}
//			}
//
//			thanhtien = (dongia / gtqd) * (Integer.parseInt(txtsoluong.getText()));
//			tongthanhtien = tongthanhtien + thanhtien;
//			String[] row1 = { stt + "", txtmathuoc.getText(), txttenthuoc.getText(),
//					cbdonvi.getSelectedItem().toString(), txtsoluong.getText(), formatter.format(dongia),
//					formatter.format(thanhtien) };
//			model1.addRow(row1);
//			stt++;
//			// txtmess.setText(" Quy đổi: "+soluongquydoi+" Viên");
//		}
//
//		double kq = 0;
//		for (int i = 0; i < table1.getRowCount(); i++) {
//			kq = kq + quyDoiTien(table1.getValueAt(i, 6).toString());
//		}
//		tongthanhtien = kq;
//		txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//		// txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//
//	}
//
//	public void capNhatSoLuong() {
//		if (cbdonvi.getSelectedIndex() == 0) {
//
//			if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//				return;
//			}
//			if (Integer.parseInt(txtsoluong.getText()) * gtqd > soluong) {
//				return;
//			}
//			soluong = soluong - Integer.parseInt(txtsoluong.getText()) * gtqd;
//			table.setValueAt(soluong, saverow, 8);
//			for (Thuoc thuoc : dsthuoc) {
//				if (thuoc.getMaThuoc().equalsIgnoreCase(table.getValueAt(saverow, 0).toString()))
//					thuoc.setSoLuong(soluong);
//			}
//		}
//
//		if (cbdonvi.getSelectedIndex() == 1) {
//			if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//				return;
//			}
//			if (Integer.parseInt(txtsoluong.getText()) > soluong) {
//				return;
//			}
//			soluong = soluong - Integer.parseInt(txtsoluong.getText());
//			table.setValueAt(soluong, saverow, 8);
//			for (Thuoc thuoc : dsthuoc) {
//				if (thuoc.getMaThuoc().equalsIgnoreCase(table.getValueAt(saverow, 0).toString()))
//					thuoc.setSoLuong(soluong);
//			}
//		}
//
//	}
////public double tinhTongThanhTien() {
//	// double kq=0;
//	// for (int i = 0; i<model1.getRowCount()-1; i++) {
//	// String a=table1.getValueAt(i,6).toString();
//	// String[] b=a.split(",");
//	// String c="";
//	// for (int j = 0; i < b.length; i++) {
//	// c=c+b[j];
//	// }
//	// kq=kq+Double.parseDouble(c);
////	}
//
////return kq;
//
////}
//	public void tinhTongThanhToan() {
//		// if (!(txtthue.getText().length() > 0 &&
//		// txtthue.getText().matches("[0-9.]+"))) {
//		// JOptionPane.showMessageDialog(this, "Thuế không hợp lệ");
//		// txtthue.selectAll();
//		// txtthue.requestFocus();
//		// txtthue.setText("");
//		// return;
//		// }
//		// if (Double.parseDouble(txtthue.getText()) < 0 ||
//		// Double.parseDouble(txtthue.getText()) > 100) {
//		// JOptionPane.showMessageDialog(this, "Thuế không hợp lệ");
//		// txtthue.selectAll();
//		// txtthue.requestFocus();
//		// return;
//		// }
//		DecimalFormat formatter = new DecimalFormat("###,###,###");
//		// VAT = Double.parseDouble(txtthue.getText());
//		tongthanhtoan = (tongthanhtien + tongthanhtien * (VAT / 100));
//		txttienthanhtoan.setText(formatter.format(tongthanhtoan) + " VNĐ");
//		// txttienkh.selectAll();
//		// txttienkh.requestFocus();
//
//	}
//
//	public void tinhTienDu() {
//		if (!(txttienkh.getText().length() > 0 && txttienkh.getText().matches("[0-9.]+"))) {
//			JOptionPane.showMessageDialog(this, "Tiền trả không hợp lệ");
//			txttienkh.selectAll();
//			txttienkh.requestFocus();
//			return;
//		}
//		if (Double.parseDouble(txttienkh.getText()) < tongthanhtoan) {
//			JOptionPane.showMessageDialog(this, "Tiền trả không đủ");
//			txttienkh.selectAll();
//			txttienkh.requestFocus();
//			return;
//		}
//		DecimalFormat formatter = new DecimalFormat("###,###,###");
//		tientra = Double.parseDouble(txttienkh.getText());
//		// VAT = Double.parseDouble(txtthue.getText());
//		tongthanhtoan = (tongthanhtien + tongthanhtien * (VAT / 100));
//		tiendu = tientra - tongthanhtoan;
//		txttiendu.setText(formatter.format(tiendu) + " VNĐ");
//
//	}
//
//	public void thanhToan() {
//		DecimalFormat formatter = new DecimalFormat("#########");
//		int hoi = JOptionPane.showConfirmDialog(this, "Chắc chắn thanh toán?", "Chú ý", JOptionPane.YES_NO_OPTION);
//		if (hoi == JOptionPane.YES_OPTION) {
//tinhTienDu();
//			if (txttongthanhtien.getText().equalsIgnoreCase("") || txttienthanhtoan.getText().equalsIgnoreCase("")
//					|| txttiendu.getText().equalsIgnoreCase("")) {
//				JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
//				return;
//			}
//
//			if (HoaDonDAO.themHoaDon(LocalDate.now().toString(), txtmanv.getText(), txtmakh.getText(),
//					txtchuandoan.getText(), VAT))
//
//			{
//				for (int i = model1.getRowCount() - 1; i >= 0; i--) {
//
//					Thuoc th = HoaDonDAO.timThuocTheoMa(table1.getValueAt(i, 1).toString());
//					if (th.getGiaTriQuyDoi() == 0)
//						th.setGiaTriQuyDoi(1);
//
//					HoaDonDAO.themChiTietHoaDon(HoaDonDAO.setCodeOrders(), th.getMaThuoc(), th.getGiaBan(),
//
//							Integer.parseInt(table1.getValueAt(i, 4).toString()));
//
//					HoaDonDAO.capNhatSoLuongLo(th.getMaThuoc(),Integer.parseInt(table1.getValueAt(i,4).toString()));
//				}
//
//				for (int i = model.getRowCount() - 1; i >= 0; i--) {
//					HoaDonDAO.capnhatSoLuong(table.getValueAt(i, 0).toString(),
//							Integer.parseInt(table.getValueAt(i, 8).toString()));
//				}
//
//				JOptionPane.showMessageDialog(this, "Thanh toán thành công");
//				// txtmanv.setText("");
//				// txttennv.setText("");
//				// txtnl.setText("");
//				// txtmakh.setText("");
//				// txttenkh.setText("");
//				// txtsdtkh.setText("");
//				txtmathuoc.setText("");
//				txttenthuoc.setText("");
//				txtchuandoan.setText("");
//				txtsoluong.setText("");
//				txttim.setText("");
//				txttimhc.setText("");
//				txttimnsx.setText("");
//				khoiPhuc();
//				cbdonvi.removeAll();
//				// txttongthanhtien.setText("");
//				// txtthue.setText("");
//				// txttienthanhtoan.setText("");
//				// txttienkh.setText("");
//				// txttiendu.setText("");
//				txtsdt.selectAll();
//				txtsdt.requestFocus();
//				dongia = 0;
//				thanhtien = 0;
//				tongthanhtien = 0;
//				VAT = 0;
//				tientra = 0;
//				tongthanhtoan = 0;
//				tiendu = 0;
//
//			}
//
//		}
//	}
//
//	public boolean suaChiTietHoaDon() {
//
//		if (rowoftable1 == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần sửa");
//			return false;
//		}
//
//		int hoi = JOptionPane.showConfirmDialog(this, "Chắc chắn sửa thuốc trong hóa đơn?", "Chú ý",
//				JOptionPane.YES_NO_OPTION);
//		if (hoi == JOptionPane.YES_OPTION) {
//
//			int sl = HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString()).getSoLuong();
//
//			Thuoc tthuoc = HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString());
//			if (tthuoc.getGiaTriQuyDoi() == 0)
//				tthuoc.setGiaTriQuyDoi(1);
//
//			DecimalFormat formatter = new DecimalFormat("###,###,###");
//			int row = table.getSelectedRow();
//			if (txtnl.getText().equalsIgnoreCase("") || txtmathuoc.getText().equalsIgnoreCase("")) {
//				JOptionPane.showMessageDialog(this, "Bạn chưa xác nhận số diện thoại hoặc chưa chọn thuốc");
//				return false;
//			}
//			if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//				JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
//				return false;
//			}
//			// if (!(txtchuandoan.getText().length() > 0 && txtchuandoan.getText().matches(
//			// "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9
//			// ]+"))) {
//			// JOptionPane.showMessageDialog(this, "Vui lòng nhập chuẩn đoán về khách
//			// hàng");
//			// return false;
//			// }
//
//			if (cbdonvi.getSelectedIndex() == 0) {
//				if (Integer.parseInt(txtsoluong.getText()) * gtqd > sl || Integer.parseInt(txtsoluong.getText()) == 0) {
//					JOptionPane.showMessageDialog(this, "Không đủ số lượng");
//					return false;
//				}
//
//				table1.setValueAt(Integer.parseInt(txtsoluong.getText()) * tthuoc.getGiaTriQuyDoi(), rowoftable1, 4);
//				// table1.setValueAt(0, rowoftable1, 3);
//
//				table1.setValueAt(formatter.format(quyDoiTien(table1.getValueAt(rowoftable1, 5).toString())
//						* Integer.parseInt(table1.getValueAt(rowoftable1, 4).toString())), rowoftable1, 6);
//
//			}
//
//			if (cbdonvi.getSelectedIndex() == 1) {
//				if (Integer.parseInt(txtsoluong.getText()) > sl || Integer.parseInt(txtsoluong.getText()) == 0) {
//					JOptionPane.showMessageDialog(this, "Không đủ số lượng");
//					return false;
//				}
//				soluongquydoi = soluongquydoi - Integer.parseInt(txtsoluong.getText());
//
//				table1.setValueAt(Integer.parseInt(txtsoluong.getText()), rowoftable1, 4);
//				// table1.setValueAt(0, rowoftable1, 5);
//
//				table1.setValueAt(formatter.format(quyDoiTien(table1.getValueAt(rowoftable1, 5).toString())
//						* Integer.parseInt(table1.getValueAt(rowoftable1, 4).toString())), rowoftable1, 6);
//
//			}
//
//			double kq = 0;
//			for (int i = 0; i < table1.getRowCount(); i++) {
//				kq = kq + quyDoiTien(table1.getValueAt(i, 6).toString());
//			}
//			tongthanhtien = kq;
//			tongthanhtoan = tongthanhtien + tongthanhtien * (VAT / 100);
//			txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//			txttienthanhtoan.setText(formatter.format(tongthanhtoan) + " VNĐ");
//
//		}
//		return true;
//
//	}
//
//	public Double quyDoiTien(String x) {
//		double kq = 0;
//		String[] a = x.split(",");
//		String b = "";
//		for (int j = 0; j < a.length; j++) {
//			b = b + a[j];
//		}
//		kq = kq + Double.parseDouble(b);
//		return kq;
//	}
//
//	public void suaThuoc() {
//		int sl = HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString()).getSoLuong();
//		Thuoc tthuoc = HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString());
//		if (tthuoc.getGiaTriQuyDoi() == 0)
//			tthuoc.setGiaTriQuyDoi(1);
//
//		if (cbdonvi.getSelectedIndex() == 0) {
//
//			if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//				return;
//			}
//			if (Integer.parseInt(txtsoluong.getText()) * gtqd > sl) {
//				return;
//			}
//
//			sl = sl - Integer.parseInt(table1.getValueAt(rowoftable1, 4).toString());
//
//			// sl = sl - Integer.parseInt(txtsoluong.getText()) * gtqd;
//
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				if (table.getValueAt(i, 0).toString().equalsIgnoreCase(table1.getValueAt(rowoftable1, 1).toString()))
//					table.setValueAt(sl, i, 8);
//			}
//
//			for (Thuoc thuoc : dsthuoc) {
//				if (thuoc.getMaThuoc().equalsIgnoreCase(table1.getValueAt(rowoftable1, 1).toString())) {
//					thuoc.setSoLuong(sl);
//				}
//			}
//
//			// table.setValueAt(sl,saverow,8);
//		}
//
//		if (cbdonvi.getSelectedIndex() == 1) {
//			if (!(txtsoluong.getText().length() > 0 && txtsoluong.getText().matches("[0-9]+"))) {
//				return;
//			}
//			if (Integer.parseInt(txtsoluong.getText()) > sl) {
//				return;
//			}
//			sl = sl - Integer.parseInt(table1.getValueAt(rowoftable1, 4).toString());
//			// sl = sl - Integer.parseInt(txtsoluong.getText());
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				if (table.getValueAt(i, 0).toString().equalsIgnoreCase(table1.getValueAt(rowoftable1, 1).toString()))
//					table.setValueAt(sl, i, 8);
//			}
//			for (Thuoc thuoc : dsthuoc) {
//				if (thuoc.getMaThuoc().equalsIgnoreCase(table1.getValueAt(rowoftable1, 1).toString())) {
//					thuoc.setSoLuong(sl);
//				}
//			}
//			// table.setValueAt(sl,saverow, 8);
//		}
//
//	}
//
//	public void xoaThuoc() {
//
//		if (rowoftable1 == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần xóa");
//			return;
//		}
//		int hoi = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa thuốc khỏi hóa đơn?", "Chú ý",
//				JOptionPane.YES_NO_OPTION);
//		if (hoi == JOptionPane.YES_OPTION) {
//
//			DecimalFormat formatter = new DecimalFormat("###,###,###");
//			int row = table1.getSelectedRow();
//
//			String a = table1.getValueAt(row, 6).toString();
//			String[] b = a.split(",");
//			String c = "";
//			for (int j = 0; j < b.length; j++) {
//				c = c + b[j];
//			}
//
//			tongthanhtien = tongthanhtien - Double.parseDouble(c);
//			txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				if (table.getValueAt(i, 0).toString().equalsIgnoreCase(table1.getValueAt(row, 1).toString()))
//					table.setValueAt(HoaDonDAO.timThuocTheoMa(table1.getValueAt(row, 1).toString()).getSoLuong() + "",
//							i, 8);
//			}
//
//			for (Thuoc thuoc : dsthuoc) {
//				if (thuoc.getMaThuoc().equalsIgnoreCase(table1.getValueAt(row, 1).toString())) {
//					thuoc.setSoLuong(HoaDonDAO.timThuocTheoMa(thuoc.getMaThuoc()).getSoLuong());
//				}
//			}
//			model1.removeRow(row);
//
//			double kq = 0;
//			for (int i = 0; i < table1.getRowCount(); i++) {
//				kq = kq + quyDoiTien(table1.getValueAt(i, 6).toString());
//			}
//			tongthanhtien = kq;
//			tongthanhtoan = tongthanhtien + tongthanhtien * (VAT / 100);
//			txttongthanhtien.setText(formatter.format(tongthanhtien) + " VNĐ");
//			txttienthanhtoan.setText(formatter.format(tongthanhtoan) + " VNĐ");
//
//		}
//
//	}
//
//	public void xoaRong() {
//
//	//	int hoi = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa mọi thông tin trên hóa đơn?", "Chú ý",
//			//	JOptionPane.YES_NO_OPTION);
//		//if (hoi == JOptionPane.YES_OPTION) {
//			for (int i = model1.getRowCount() - 1; i >= 0; i--) {
//				model1.removeRow(i);
//			}
//			dongia = 0;
//			thanhtien = 0;
//			tongthanhtien = 0;
//			// VAT = 0;
//			tientra = 0;
//			tongthanhtoan = 0;
//			tiendu = 0;
//			txttongthanhtien.setText("");
//			// txtthue.setText("");
//			txttienthanhtoan.setText("");
//			txttienkh.setText("");
//			txttiendu.setText("");
//			txtmathuoc.setText("");
//			txttenthuoc.setText("");
//			txtchuandoan.setText("");
//			txtsoluong.setText("");
//			txtmess.setText("");
//
//			txtmanv.setText("");
//			txttennv.setText("");
//			txtnl.setText("");
//			txthd.setText("");
//			txtmakh.setText("");
//			txttenkh.setText("");
//			txtsdtkh.setText("");
//
//			cbdonvi.removeAll();
//			txtsdt.selectAll();
//			txtsdt.requestFocus();
//
//			dsthuoc = HoaDonDAO.layDanhSachThuoc();
//			for (int i = model.getRowCount() - 1; i >= 0; i--) {
//				model.removeRow(i);
//			}
//			docfile();
//		//}
//	}
//
//	public KhachHang traCuuKhachHang1() {
//		KhachHang kh = null;
//
//		getjd = cbjdnv.getSelectedItem().toString();
//		spljd = getjd.split("--");
//
//		kh = new KhachHang(spljd[0], spljd[1], 0, spljd[2], "", "");
//		return kh;
//	}
//
//	public JDialog traCuuKhachHang() {
//
//		boolean flag = false;
//		JDialog jd = new JDialog();
//
//		jd.setTitle("Vui lòng chọn khách hàng");
//		Box bjd = Box.createVerticalBox();
//
//		bjd.add(cbjdnv = new JComboBox<String>());
//
//		for (KhachHang a : HoaDonDAO.getDSKhachHang(txttimmakh.getText(), txttimtenkh.getText(), txtsdt.getText())) {
//			cbjdnv.addItem(a.getMaKH() + "--" + a.getTenKH() + "--" + a.getSoDT() + "--" + a.getGioiTinh() + "--"
//					+ a.getNamSinh() + "--" + a.getDiaChi());
//		}
//		try {
//			getjd = cbjdnv.getSelectedItem().toString();
//			spljd = getjd.split("--");
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
//			jd.setVisible(false);
//			return jd;
//		}
//
//		Box bjd1 = Box.createHorizontalBox();
//		bjd1.add(btnjdnv = new JButton("Chọn"));
//		btnjdnv.addActionListener(this);
//		bjd.add(Box.createVerticalStrut(10));
//		bjd.add(bjd1);
//
//		bjd.add(Box.createVerticalStrut(10));
//		jd.getContentPane().add(bjd);
//
//		jd.setBounds(450, 0, 700, 110);
//		jd.setVisible(true);
//
//		return jd;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object o = e.getSource();
//		if (o.equals(btnjdnv)) {
//			traCuuKhachHang1();
//			layThongTinHoaDon();
//		}
//		if (o.equals(btnxoarong))
//			xoaRong();
//		if (o.equals(btnxoa))
//			xoaThuoc();
//		if (o.equals(btnsua)) {
//			if ((suaChiTietHoaDon()))
//				suaThuoc();
//		}
//		if (o.equals(btnthem)) {
//			themChiTietHoaDon();
//			capNhatSoLuong();
//			tinhTongThanhToan();
//		}
//		if (o.equals(cbtim))
//			setSearch();
//		if (o.equals(cbloc1))
//			timThuoc();
//
//		if (o.equals(btntim))
//			timThuoc();
//		if (o.equals(btnkhoiphuc))
//			khoiPhuc();
//		if (o.equals(btnxn)) {
//			traCuuKhachHang();
//			// layThongTinHoaDon();
//		}
//		if (o.equals(txtthue)) {
//			tinhTongThanhToan();
//		}
//		if (o.equals(txttienkh)) {
//			tinhTienDu();
//		}
//		if (o.equals(btnthanhtoan)) {
//			thanhToan();
//
//		}
//
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		Object o = e.getSource();
//
//		if (o.equals(table)) {
//
//			int check = -1;
//			int row = table.getSelectedRow();
//			saverow = table.getSelectedRow();
//			// rowoftable1 = table1.getSelectedRow();
//			txtmathuoc.setText(table.getValueAt(row, 0).toString());
//			txttenthuoc.setText(table.getValueAt(row, 1).toString());
//			soluong = Integer.parseInt(table.getValueAt(row, 8).toString());
//			gtqd = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaTriQuyDoi();
//			soluongquydoi = soluong * gtqd;
//			dongia = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaBan();
//
//			cbdonvi.removeAllItems();
//			cbdonvi.addItem(HoaDonDAO.timThuocTheoMa(table.getValueAt(row, 0).toString()).getDonVi());
//			cbdonvi.addItem(HoaDonDAO.timThuocTheoMa(table.getValueAt(row, 0).toString()).getDonViQuyDoi());
//			txtmess.setText("");
//			if (cbdonvi.getItemAt(0).equalsIgnoreCase("Hộp"))
//				txtmess.setText("   Quy đổi: " + gtqd + " " + cbdonvi.getItemAt(1));
//			// if(HoaDonDAO.timThuocTheoMa(table.getValueAt(row,0).toString()).getdo)
//			// if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Lọ")) {
//
//			// cbdonvi.addItem("Lọ");
//			// txtmess.setText("");
//			// }
//			// if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Viên")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Viên");
//			// txtmess.setText("");
//			// }
//			// if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Vỉ")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Vỉ");
//			// cbdonvi.addItem("Viên");
//
//			// }
//			// if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Hộp")) {
//			// cbdonvi.removeAllItems();
//			//// cbdonvi.addItem("Hộp");
//			// cbdonvi.addItem("Viên");
//			// txtmess.setText(" Quy đổi: " + gtqd + " Viên");
//			// }
//			if (HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaTriQuyDoi() == 0)
//				gtqd = 1;
//		}
//
//		if (o.equals(table1)) {
//
//			// int check = -1;
//			// int row = table.getSelectedRow();
//			// saverow = table.getSelectedRow();
//			rowoftable1 = table1.getSelectedRow();
//			txtmathuoc.setText(table1.getValueAt(rowoftable1, 1).toString());
//			txttenthuoc.setText(table1.getValueAt(rowoftable1, 2).toString());
//			// soluong = Integer.parseInt(table.getValueAt(row, 8).toString());
//			gtqd = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaTriQuyDoi();
//
//			cbdonvi.removeAllItems();
//			cbdonvi.addItem(HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString()).getDonVi());
//			cbdonvi.addItem(HoaDonDAO.timThuocTheoMa(table1.getValueAt(rowoftable1, 1).toString()).getDonViQuyDoi());
//			txtmess.setText("");
//			if (cbdonvi.getItemAt(0).equalsIgnoreCase("Hộp"))
//				txtmess.setText("   Quy đổi: " + gtqd + " " + cbdonvi.getItemAt(1));
//
//			// soluongquydoi = soluong * gtqd;
//			// dongia = HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaBan();
//			// if (table1.getValueAt(rowoftable1, 4).toString().equalsIgnoreCase("Lọ")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Lọ");
//			// txtmess.setText("");
//			// }
//			// if (table1.getValueAt(rowoftable1, 4).toString().equalsIgnoreCase("Viên")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Viên");
//			// txtmess.setText("");
//			// }
//			// if (table1.getValueAt(rowoftable1, 4).toString().equalsIgnoreCase("Vỉ")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Vỉ");
//			// c/bdonvi.addItem("Viên");
//			// txtmess.setText(" Quy đổi: " + gtqd + " Viên");
//			// }
//			// if (table1.getValueAt(rowoftable1, 4).toString().equalsIgnoreCase("Hộp")) {
//			// cbdonvi.removeAllItems();
//			// cbdonvi.addItem("Hộp");
//			// cbdonvi.addItem("Viên");
//			// txtmess.setText(" Quy đổi: " + gtqd + " Viên");
//			// }
//			if (HoaDonDAO.timThuocTheoMa(txtmathuoc.getText()).getGiaTriQuyDoi() == 0)
//				gtqd = 1;
//		}
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
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		Object o = e.getSource();
//		if (o.equals(txttim) || o.equals(txttimhc) || o.equals(txttimnsx) || o.equals(txthl) || o.equals(txtdbc))
//			timThuoc();
//
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		Object o = e.getSource();
//		if (o.equals(txttim) || o.equals(txttimhc) || o.equals(txttimnsx) || o.equals(txthl) || o.equals(txtdbc))
//			timThuoc();
//
//	}

}
