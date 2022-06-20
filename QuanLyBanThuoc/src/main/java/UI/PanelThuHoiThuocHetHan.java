package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import DAO.ThuocDAO;
import Model.LoThuoc;
import Model.Thuoc;

public class PanelThuHoiThuocHetHan extends JPanel implements ActionListener {

	private List<LoThuoc> loThuocs;
	private DefaultTableModel tbModel;
	private JTable tb;
	private JScrollPane jScrollPane;
	private JButton btnThuHoi, btnLamMoi, btnThoat;
	private JPanel pCenter, pSouth;

	public PanelThuHoiThuocHetHan() {
		setLayout(new BorderLayout());
		loThuocs = new ArrayList<LoThuoc>();

		taoCenterPanel();
		taoSouthPanel();

		this.add(pCenter, BorderLayout.CENTER);
		this.add(pSouth, BorderLayout.SOUTH);

		lamMoiUI();
		//themListener();
	}

	public void taoCenterPanel() {
		pCenter = new JPanel(new GridLayout(1, 1));
		pCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pCenter.setBackground(new Color(0, 128, 128));

		String[] columnNames = { "Mã lô", "Mã thuốc", "Tên thuốc", "Ngày nhập", "Ngày hết hạn", "Lượng nhập",
				"Lượng bán", "Lượng hết hạn" };
		tbModel = new DefaultTableModel(columnNames, 30);
		tb = new JTable(tbModel);
		tb.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 14));
		tb.setRowHeight(20);
		JTableHeader tableHeader = tb.getTableHeader();
		tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		jScrollPane = new JScrollPane(tb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Danh sách lo thuốc hết hạn", TitledBorder.LEFT,
				TitledBorder.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 16)));
		jScrollPane.setBackground(new Color(173, 216, 230));

		pCenter.add(jScrollPane);
	}

	public void taoSouthPanel() {
		pSouth = new JPanel();
		pSouth.setBackground(new Color(0, 128, 128));

		btnThuHoi = new JButton("Thu hồi");
		btnThuHoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnThuHoi.setBackground(Color.white);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi.setBackground(Color.white);

		pSouth.add(btnThuHoi);
		pSouth.add(btnLamMoi);
	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(1200, 500);
//		PanelThuHoiThuocHetHan hetHan = new PanelThuHoiThuocHetHan();
//		frame.add(hetHan);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}

	public void lamMoiUI() {
		lamMoiDSThuocHetHan();
		//lamMoiBangLoThuoc();
	}

	public void lamMoiDSThuocHetHan() {
//		ThuocDAO dao = new ThuocDAO();
//		ArrayList<LoThuoc> temp = dao.getDSThuocHetHan();
//		loThuocs.clear();
//		loThuocs.addAll(temp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public void lamMoiBangLoThuoc() {
//		tbModel.getDataVector().removeAllElements();
//		Vector<String> vector;
//		for (LoThuoc loThuoc : loThuocs) {
//			vector = new Vector<String>();
//			vector.add(loThuoc.getMaLo() + "");
//			vector.add(loThuoc.getThuoc().getMaThuoc());
//			vector.add(loThuoc.getThuoc().getTenThuoc());
//			vector.add(loThuoc.getNgayNhap() + "");
//			vector.add(loThuoc.getHanSuDung() + "");
//			vector.add(loThuoc.getSoLuongNhapVao() + "");
//			vector.add(loThuoc.getSoLuongBanRa() + "");
//			vector.add((loThuoc.getSoLuongNhapVao() - loThuoc.getSoLuongBanRa()) + "");
//			tbModel.addRow(vector);
//		}
//		tb.updateUI();
//		tb.clearSelection();
//	}
//
//	public void thuHoiThuocHetHan() {
//		ThuocDAO dao = new ThuocDAO();
//		boolean rs = dao.thuHoiThuocHetHan((ArrayList<LoThuoc>) loThuocs);
//		if (rs) {
//			JOptionPane.showMessageDialog(this, "Thu hồi thuốc thành công!");
//			lamMoiUI();
//		} else
//			JOptionPane.showMessageDialog(this, "Thu hồi thuốc không thành công!");
//	}
//
//	public void themListener() {
//		btnThuHoi.addActionListener(this);
//		btnLamMoi.addActionListener(this);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object o = e.getSource();
//		if (o.equals(btnThuHoi))
//			thuHoiThuocHetHan();
//		else if (o.equals(btnLamMoi))
//			lamMoiUI();
//	}
}
