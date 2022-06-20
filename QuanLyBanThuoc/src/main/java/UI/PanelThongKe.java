package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.DinhDangDate;
import DAO.HoaDonDAO;
import DAO.ThongKeDAO;
import Model.LoThuoc;
import Model.Thuoc;


public class PanelThongKe extends JPanel implements ActionListener {
	private int col = 0;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private SqlDateModel jdtracuu = new SqlDateModel();
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private JTable table1, table2, table3,table3a,table4a, table4;
	private DefaultTableModel model1, model2, model3,model3a, model4,model4a;
	private JLabel lbngay, lbthang, lbnam, lbngaytc;
	private JTextField txttongnv, txttongkh, txttongthuoc, txtngay, txtthang, txtnam, txtngaytk;
	private JComboBox<String> cbngay, cbthang, cbnam;
	private JButton btntracuu;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
		//		try {
		//			PanelThongKe frame = new PanelThongKe();
		//			frame.setVisible(true);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//	}
		//});
	//}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public PanelThongKe() throws ParseException {
//		setTitle("Quản Lý Nhân Viên");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		////setResizable(false);
		//setSize(1900, 1000);

		//JMenuBar menuBar = new JMenuBar();
		//menuBar.setBackground(new Color(220, 220, 220));
		//setJMenuBar(menuBar);

	//	JMenu mnTrangChu = new JMenu("Trang chá»§");
	//	mnTrangChu.setFont(new Font("Times New Roman", Font.BOLD, 14));
	//	menuBar.add(mnTrangChu);

		//JMenu mnQunL = new JMenu("Quáº£n lÃ½");
		//mnQunL.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//menuBar.add(mnQunL);

		//JMenuItem mntmBnHng = new JMenuItem("BÃ¡n hÃ ng");
	//	mntmBnHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	//	mnQunL.add(mntmBnHng);

		//JMenuItem mntmThuc = new JMenuItem("Thuá»‘c");
		//mntmThuc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmThuc);

		//JMenuItem mntmKhchHng = new JMenuItem("KhÃ¡ch hÃ ng");
		//mntmKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	//	mnQunL.add(mntmKhchHng);

		//JMenuItem mntmNhnVin = new JMenuItem("NhÃ¢n viÃªn");
		//mntmNhnVin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmNhnVin);

		//JMenuItem mntmNhSnXut = new JMenuItem("NhÃ  sáº£n xuáº¥t");
		//mntmNhSnXut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//mnQunL.add(mntmNhSnXut);

		//JMenu mnThngK = new JMenu("Thá»‘ng kÃª");
	//	mnThngK.setFont(new Font("Times New Roman", Font.BOLD, 14));
	//	menuBar.add(mnThngK);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);

		JPanel pn = new JPanel();
		Box bn = Box.createHorizontalBox();
		bn.add(Box.createHorizontalStrut(270));

		JLabel label = new JLabel("Sá»‘ lÆ°á»£ng nhÃ¢n viÃªn:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bn.add(label);
		bn.add(Box.createHorizontalStrut(10));
		bn.add(txttongnv = new JTextField("1"));
		txttongnv.setEditable(false);
		txttongnv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bn.add(Box.createHorizontalStrut(50));

		JLabel label_1 = new JLabel("Sá»‘ lÆ°á»£ng khÃ¡ch hÃ ng:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bn.add(label_1);
		bn.add(Box.createHorizontalStrut(10));
		bn.add(txttongkh = new JTextField("1"));
		txttongkh.setEditable(false);
		txttongkh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bn.add(Box.createHorizontalStrut(50));

		JLabel label_2 = new JLabel("Sá»‘ lÆ°á»£ng thuá»‘c:");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bn.add(label_2);
		bn.add(Box.createHorizontalStrut(10));
		bn.add(txttongthuoc = new JTextField("1"));
		txttongthuoc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txttongthuoc.setEditable(false);
		bn.add(Box.createHorizontalStrut(50));

		txttongkh.setBorder(null);
		txttongnv.setBorder(null);
		txttongthuoc.setBorder(null);

		pn.add(bn);

		TitledBorder tbpn = new TitledBorder(BorderFactory.createLineBorder(Color.black), "");
		pn.setBorder(tbpn);
		bn.setPreferredSize(new Dimension(1500, 25));
		add(pn, BorderLayout.NORTH);

		JPanel pc = new JPanel();
		pc.setBackground(new Color(0, 128, 128));
		Box bc = Box.createVerticalBox();
		Box bc1 = Box.createHorizontalBox();

		JLabel label_3 = new JLabel("Ngày:");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_3.setForeground(new Color(255, 255, 255));
		bc1.add(label_3);bc1.add(Box.createHorizontalStrut(10));
		bc1.add(cbngay=new JComboBox<String>());
		cbngay.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbngay.setBackground(new Color(255, 255, 255));
		cbngay.addItem("23");
		bc1.add(Box.createHorizontalStrut(30));

		JLabel label_4 = new JLabel("Tháng:");
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bc1.add(label_4);bc1.add(Box.createHorizontalStrut(10));
		bc1.add(cbthang=new JComboBox<String>());
		cbthang.setBackground(new Color(255, 255, 255));
		cbthang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbthang.addItem("4");
		bc1.add(Box.createHorizontalStrut(30));

		JLabel label_5 = new JLabel("Năm:");
		 label_5.setForeground(new Color(255, 255, 255));
		 label_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		 bc1.add(label_5);bc1.add(Box.createHorizontalStrut(10));
		 bc1.add(cbnam=new JComboBox<String>());
		 cbnam.setBackground(new Color(255, 255, 255));
		 cbnam.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		 cbnam.addItem("2000");

		Properties jdp = new Properties();
		jdp.put("text.day", "Day");
		jdp.put("text.month", "Month");
		jdp.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(jdtracuu, jdp);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DinhDangDate());
		datePicker.getJFormattedTextField().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		bc1.add(lbngaytc = new JLabel("Ngày tra cứu:"));
		bc1.add(Box.createHorizontalStrut(10));
		lbngaytc.setForeground(new Color(255, 255, 255));
		lbngaytc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//bc1.add(datePicker);

		bc1.add(Box.createHorizontalStrut(10));
		bc1.add(btntracuu = new JButton("Tra cứu"));
		btntracuu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btntracuu.setBackground(new Color(255, 255, 255));
		btntracuu.addActionListener(this);

		Box bc1a = Box.createHorizontalBox();
		bc1a.add(lbngay = new JLabel("Tính doanh thu trong ngày:"));
		lbngay.setForeground(new Color(255, 255, 255));
		lbngay.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bc1a.add(Box.createHorizontalStrut(10));
		bc1a.add(txtngay = new JTextField(10));
		txtngay.setBackground(new Color(0, 128, 128));
		txtngay.setForeground(new Color(255, 255, 255));
		txtngay.setFont(new Font("Times New Roman", Font.ITALIC, 14));

		Box bc1b = Box.createHorizontalBox();
		bc1b.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bc1b.add(lbthang = new JLabel("Tính doanh thu trong tháng:"));
		lbthang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbthang.setForeground(new Color(255, 255, 255));
		bc1b.add(Box.createHorizontalStrut(8));
		bc1b.add(txtthang = new JTextField(10));
		txtthang.setForeground(new Color(255, 255, 255));
		txtthang.setBackground(new Color(0, 128, 128));
		txtthang.setFont(new Font("Times New Roman", Font.ITALIC, 14));

		Box bc1c = Box.createHorizontalBox();
		bc1c.add(lbnam = new JLabel("Tính doanh thu trong năm:"));
		lbnam.setForeground(new Color(255, 255, 255));
		lbnam.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bc1c.add(Box.createHorizontalStrut(10));
		bc1c.add(txtnam = new JTextField(10));
		txtnam.setBackground(new Color(0, 128, 128));
		txtnam.setForeground(new Color(255, 255, 255));
		txtnam.setFont(new Font("Times New Roman", Font.ITALIC, 14));

		lbnam.setPreferredSize(lbthang.getPreferredSize());
		lbngay.setPreferredSize(lbthang.getPreferredSize());
		lbngaytc.setPreferredSize(lbthang.getPreferredSize());

		Box bc2 = Box.createHorizontalBox();
		pc.setForeground(new Color(0, 0, 0));
		TitledBorder tbpc = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Thá»‘ng kÃª doanh thu");
		tbpc.setTitleColor(Color.white);
		pc.setBorder(tbpc);

//		for (int i = 1; i <= LocalDate.now().getDayOfMonth(); i++) {
//			dataset.addValue(
//					ThongKeDAO.timDoanhThuTheoNgay(
//							LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-" + i),
//					"Doanh thu má»—i ngÃ y trong thÃ¡ng", String.valueOf(i));
//		}
		JFreeChart chart = ChartFactory.createBarChart("Biá»ƒu Ä‘á»“ doanh thu trong thÃ¡ng", "NgÃ y", "VNÄ�", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer bar = (BarRenderer) plot.getRenderer();
		Color color = new Color(79, 129, 189);
		bar.setSeriesPaint(0, color);
		ChartPanel cpc = new ChartPanel(chart);
		bc2.add(cpc);
		bc2.setPreferredSize(new Dimension(745, 500));

		Box bc2a = Box.createHorizontalBox();
		JLabel label_6 = new JLabel("NgÃ y thá»‘ng kÃª:");
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bc2a.add(label_6);
		bc2a.add(Box.createHorizontalStrut(10));
		bc2a.add(txtngaytk = new JTextField(10));
		txtngaytk.setForeground(new Color(255, 255, 255));
		txtngaytk.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		txtngaytk.setBackground(new Color(0, 128, 128));
		txtngaytk.setBorder(null);
		txtngaytk.setEditable(false);
		txtngaytk.setText(LocalDate.now() + "");

//		txtngay.setText(formatter.format(ThongKeDAO.timDoanhThuTheoNgay(LocalDate.now() + "")) + " VNÄ�");
//		txtthang.setText(formatter.format(ThongKeDAO.timDoanhThuTheoThang(LocalDate.now())) + " VNÄ�");
//		txtnam.setText(formatter.format(ThongKeDAO.timDoanhThuTheoNam(LocalDate.now())) + " VNÄ�");

		bc.add(bc2a);
		bc.add(Box.createVerticalStrut(10));
		bc.add(bc2);
		bc.add(Box.createVerticalStrut(20));
		bc.add(bc1);
		bc.add(Box.createVerticalStrut(10));
		bc.add(bc1a);
		bc.add(Box.createVerticalStrut(10));
		bc.add(bc1b);
		bc.add(Box.createVerticalStrut(10));
		bc.add(bc1c);
		bc.add(Box.createVerticalStrut(50));
		pc.add(bc);
		add(pc, BorderLayout.CENTER);

		JPanel pe = new JPanel();
		pe.setBackground(new Color(0, 128, 128));
		Box be = Box.createVerticalBox();

		String[] header1 = { "MÃ£ thuá»‘c", "TÃªn thuá»‘c", "Loáº¡i", "Dáº¡ng bÃ o cháº¿", "Hoáº¡t cháº¥t", "HÃ m lÆ°á»£ng", "NhÃ  sáº£n xuáº¥t",
				"Ä�Æ¡n vá»‹ tÃ­nh", "Sá»‘ lÆ°á»£ng", "LÆ°á»£t mua" };
		model1 = new DefaultTableModel(header1, 0);
		JScrollPane pane1 = new JScrollPane(table1 = new JTable(model1), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setPreferredSize(new Dimension(950,760));

		String[] header2 = { "MÃ£ thuá»‘c", "TÃªn thuá»‘c", "Loáº¡i", "Dáº¡ng bÃ o cháº¿", "Hoáº¡t cháº¥t", "HÃ m lÆ°á»£ng", "NhÃ  sáº£n xuáº¥t",
				"Ä�Æ¡n vá»‹ tÃ­nh", "Sá»‘ lÆ°á»£ng", "LÃ£i" };
		model2 = new DefaultTableModel(header2, 0);
		JScrollPane pane2 = new JScrollPane(table2 = new JTable(model2), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setPreferredSize(new Dimension(950,760));

		String[] header3 = { "MÃ£ LÃ´", "MÃ£ thuá»‘c", "TÃªn Thuá»‘c", "NgÃ y nháº­p", "Háº¡n sá»­ dá»¥ng" };
		model3 = new DefaultTableModel(header3, 0);
		JScrollPane pane3 = new JScrollPane(table3 = new JTable(model3), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		String[] header3a = { "MÃ£ LÃ´", "MÃ£ thuá»‘c", "TÃªn Thuá»‘c", "NgÃ y nháº­p", "Háº¡n sá»­ dá»¥ng" };
		model3a = new DefaultTableModel(header3a, 0);
		JScrollPane pane3a = new JScrollPane(table3a = new JTable(model3a), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setPreferredSize(new Dimension(950,760));

		String[] header4 = { "MÃ£ thuá»‘c", "TÃªn thuá»‘c", "Loáº¡i", "Dáº¡ng bÃ o cháº¿", "Hoáº¡t cháº¥t", "HÃ m lÆ°á»£ng", "NhÃ  sáº£n xuáº¥t",
				"Ä�Æ¡n vá»‹ tÃ­nh", "Sá»‘ lÆ°á»£ng" };
		model4 = new DefaultTableModel(header4, 0);
		JScrollPane pane4 = new JScrollPane(table4 = new JTable(model4), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
		String[] header4a = { "MÃ£ thuá»‘c", "TÃªn thuá»‘c", "Loáº¡i", "Dáº¡ng bÃ o cháº¿", "Hoáº¡t cháº¥t", "HÃ m lÆ°á»£ng", "NhÃ  sáº£n xuáº¥t",
				"Ä�Æ¡n vá»‹ tÃ­nh", "Sá»‘ lÆ°á»£ng" };
		model4a = new DefaultTableModel(header4a, 0);
		JScrollPane pane4a = new JScrollPane(table4a = new JTable(model4a), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setPreferredSize(new Dimension(950,760));

		be.add(new JLabel("Thuá»‘c bÃ¡n cháº¡y:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane1);
		be.add(new JLabel("Thuá»‘c lÃ£i nháº¥t:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane2);
		be.add(new JLabel("Thuá»‘c sáº¯p háº¿t háº¡n:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane3);
		be.add(new JLabel("Thuá»‘c sáº¯p háº¿t hÃ ng:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane4);
		
		be.add(new JLabel("Thuá»‘c Ä‘Ã£ háº¿t háº¡n:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane3a);
		be.add(new JLabel("Thuá»‘c Ä‘Ã£ háº¿t hÃ ng:"));
		be.add(Box.createVerticalStrut(5));
		be.add(pane4a);

		TitledBorder tbpe = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Thá»‘ng kÃª thuá»‘c");
		tbpe.setTitleColor(Color.white);
		pe.setBorder(tbpe);

		JScrollPane pane = new JScrollPane(be, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(715, 700));
		pane.setBackground(new Color(0, 128, 128));

		pe.add(pane);
		pe.setPreferredSize(new Dimension(748, 750));
		add(pe, BorderLayout.EAST);

//		txttongkh.setText(ThongKeDAO.demKhachHang() + "");
//		txttongnv.setText(ThongKeDAO.demNhanVien() + "");
//		txttongthuoc.setText(ThongKeDAO.demThuoc() + "");

		//layThuocSapHetHang();

		col = LocalDate.now().getDayOfMonth() - 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public void layThuocSapHetHang() {
//
//		for (Thuoc a : ThongKeDAO.layThuocSapHetHang()) {
//			String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//					a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), a.getDonVi(),
//					a.getSoLuong() + "" };
//			model4.addRow(row);
//		}
//		
//		for (Thuoc a : ThongKeDAO.layThuocDaHetHang()) {
//			String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//					a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), a.getDonVi(),
//					a.getSoLuong() + "" };
//			model4a.addRow(row);
//		}
//
//		for (Thuoc a : ThongKeDAO.layThuocBanChay()) {
//			String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//					a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), a.getDonVi(), a.getSoLuong() + "",
//					a.getTrangThai() };
//			model1.addRow(row);
//		}
//
//		for (Thuoc a : ThongKeDAO.layThuocLaiNhat()) {
//			String[] row = { a.getMaThuoc(), a.getTenThuoc(), a.getLoai().getTenLoai(), a.getDangBaoChe(),
//					a.getHoatChat(), a.getHamLuong(), a.getNhaSanXuat().getTenNSX(), a.getDonVi(), a.getSoLuong() + "",
//					formatter.format(Double.parseDouble(a.getTrangThai())) };
//			model2.addRow(row);
//		}
//
//		for (LoThuoc a : ThongKeDAO.layThuocSapHetHan()) {
//			String[] row = { a.getMaLo() + "", a.getThuoc().getMaThuoc(),a.getThuoc().getTenThuoc(), a.getNgayNhap() + "",
//					a.getHanSuDung() + "" };
//		model3.addRow(row);
//		}
//		
//		for (LoThuoc a : ThongKeDAO.layThuocDaHetHan()) {
//			String[] row = { a.getMaLo() + "", a.getThuoc().getMaThuoc(),a.getThuoc().getTenThuoc(), a.getNgayNhap() + "",
//					a.getHanSuDung() + "" };
//		model3a.addRow(row);
//		}
//	}
//
//	public void traCuuDoanhThu() {
//
////		for (int i = 0; i <= col; i++) {
////			dataset.removeColumn(0);
////		}
////		txtngaytk.setText(jdtracuu.getYear() + "/" + (jdtracuu.getMonth() + 1) + "/" + jdtracuu.getDay());
////		col = jdtracuu.getDay() - 1;
////		for (int i = 1; i <= jdtracuu.getDay(); i++) {
////			dataset.addValue(
////					ThongKeDAO.timDoanhThuTheoNgay(jdtracuu.getYear() + "-" + (jdtracuu.getMonth() + 1) + "-" + i),
////					"Doanh thu má»—i ngÃ y trong thÃ¡ng", String.valueOf(i));
////			// dataset.addValue(1,"Doanh thu má»—i ngÃ y trong thÃ¡ng",String.valueOf(i));
//
//		}
////
////		txtngay.setText(
////				formatter
////						.format(ThongKeDAO.timDoanhThuTheoNgay(
////								LocalDate.of(jdtracuu.getYear(), jdtracuu.getMonth() + 1, jdtracuu.getDay()) + ""))
////						+ " VNÄ�");
////		txtthang.setText(
////				formatter
////						.format(ThongKeDAO.timDoanhThuTheoThang(
////								LocalDate.of(jdtracuu.getYear(), jdtracuu.getMonth() + 1, jdtracuu.getDay())))
////						+ " VNÄ�");
////		txtnam.setText(
////				formatter
////						.format(ThongKeDAO.timDoanhThuTheoNam(
////								LocalDate.of(jdtracuu.getYear(), jdtracuu.getMonth() + 1, jdtracuu.getDay())))
////						+ " VNÄ�");
////	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object o = e.getSource();
//		if (o.equals(btntracuu))
//			traCuuDoanhThu();
//
//	}

}
