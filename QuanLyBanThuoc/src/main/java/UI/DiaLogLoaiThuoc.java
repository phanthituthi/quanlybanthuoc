package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultStyledDocument;

//import DAO.LoaiThuocDAO;
import Model.NhomThuoc;

public class DiaLogLoaiThuoc extends JDialog  {

	private JPanel pLoai, pTTLoai, pDSLoai, pXuLy, pButton, pTimKiem;
	//
	private JTextField txtMa, txtTen;
	private JTextArea txaMoTa;
	//
	private DefaultTableModel tableModel;
	private JTable tbLoai;
	private JScrollPane scrollPane;
	//
	private JButton btnThem, btnCapNhat, btnXoa, btnLamMoi;
	//
	private JComboBox<String> cbTim;
	private JTextField txtTim;
	private JButton btnTim;
	//
	private ArrayList<NhomThuoc> loaiThuocs;

	public DiaLogLoaiThuoc(Frame owner) {
		super(owner);
		buildUI();
		loaiThuocs = new ArrayList<NhomThuoc>();
		taoPanelLoai();
		taoPanelXuLy();

		this.add(pLoai, BorderLayout.CENTER);
		this.add(pXuLy, BorderLayout.EAST);

		//themListener();
		lamMoiUI();
//		setVisible(true);
	}

	public void buildUI() {
		this.setTitle("Loại thuốc");
		setSize(1210, 490);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void taoPanelLoai() {
		taoPanelTTLoai();
		taoPanelDSLoai();

		pLoai = new JPanel();
		pLoai.setBackground(new Color(0, 128, 128));
		pLoai.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridx = 0;
		c.gridy = 0;
		pLoai.add(pTTLoai, c);
		c.gridy = 1;
		pLoai.add(pDSLoai, c);
	}

	public void taoPanelTTLoai() {
		JLabel lblMa, lblTen, lblMoTa;
		lblMa = new JLabel("Mã");
		lblMa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		txtMa = new JTextField(30);
		txtMa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txtMa.setEditable(false);
		txtTen = new JTextField(30);
		txtTen.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		txaMoTa = new JTextArea(2, 30);
		txaMoTa.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		txaMoTa.setLineWrap(true);
		txaMoTa.setBorder(BorderFactory.createLineBorder(Color.gray));

		pTTLoai = new JPanel();
		pTTLoai.setBackground(new Color(0, 128, 128));
		pTTLoai.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 5;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridy = 0;
		c.gridx = 0;
		pTTLoai.add(lblMa, c);
		c.gridx = 1;
		pTTLoai.add(txtMa, c);
		c.gridx = 2;
		pTTLoai.add(lblTen, c);
		c.gridx = 3;
		pTTLoai.add(txtTen, c);

		c.gridx = 0;
		c.gridy = 1;
		pTTLoai.add(lblMoTa, c);
		c.gridx = 1;
		c.gridwidth = 3;
		pTTLoai.add(txaMoTa, c);
	}

	public void taoPanelDSLoai() {
		String[] column = { "Mã", "Tên", "Mô tả" };
		tableModel = new DefaultTableModel(column, 20);
		tbLoai = new JTable(tableModel);
		tbLoai.setBackground(Color.white);
		tbLoai.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 14));
		tbLoai.setRowHeight(20);

		JTableHeader tableHeader = tbLoai.getTableHeader();
		tableHeader.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		scrollPane = new JScrollPane(tbLoai, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Danh sách loại thuốc", TitledBorder.LEFT,
				TitledBorder.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 16)));
		scrollPane.setBackground(new Color(173, 216, 230));
		scrollPane.setMinimumSize(new Dimension(700, 300));

		pDSLoai = new JPanel();
		pDSLoai.setBackground(new Color(0, 128, 128));
		pDSLoai.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pDSLoai.setLayout(new GridLayout(1, 1));
		pDSLoai.add(scrollPane);
	}

	public void taoPanelXuLy() {
		taoPanelButton();
		taoPanelTimKiem();

		pXuLy = new JPanel();
		pXuLy.setBackground(new Color(0, 128, 128));
		pXuLy.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 5, 0, 5);

		c.gridy = 0;
		c.gridx = 0;
		pXuLy.add(pButton, c);
		c.gridy = 1;
		pXuLy.add(pTimKiem, c);
	}

	public void taoPanelButton() {

		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.white);
		btnThem.setPreferredSize(new Dimension(270, 30));
		btnThem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(Color.white);
		btnCapNhat.setPreferredSize(new Dimension(270, 30));
		btnCapNhat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.white);
		btnXoa.setPreferredSize(new Dimension(270, 30));
		btnXoa.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.white);
		btnLamMoi.setPreferredSize(new Dimension(270, 30));
		btnLamMoi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		pButton = new JPanel();
		pButton.setBackground(new Color(0, 128, 128));
		pButton.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 5;
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

	public void taoPanelTimKiem() {
		JLabel lblTimKiem, lblTheoMa, lblTheoTen;

		lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
		lblTimKiem.setPreferredSize(new Dimension(200, 30));
		lblTimKiem.setOpaque(true);
		lblTimKiem.setBackground(new Color(173, 216, 230));
		lblTimKiem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));

		String[] tieuchitim = { "Theo mã", "Theo tên" };
		cbTim = new JComboBox<String>(tieuchitim);
		cbTim.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		txtTim = new JTextField(20);
		txtTim.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		btnTim = new JButton("Tìm");
		btnTim.setBackground(Color.white);
		btnTim.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(0, 128, 128));
		pTimKiem.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 5;
		c.insets = new Insets(5, 0, 5, 0);

		c.gridy = 0;
		c.gridx = 0;
		pTimKiem.add(lblTimKiem, c);
		c.gridy = 1;
		pTimKiem.add(cbTim, c);
		c.gridy = 2;
		pTimKiem.add(txtTim, c);
		c.gridy = 3;
		pTimKiem.add(btnTim, c);

	}

	public void lamMoiUI() {
		//txtMa.setText(LoaiThuocDAO.getMaLoaiThuoc());
		txtTen.setText("");
		txtTim.setText("");
		txaMoTa.setText("");
		cbTim.setSelectedIndex(0);
		//lamMoiDSLoaiThuoc();
		//lamMoiBangLoaiThuoc();
	}

//	public void lamMoiDSLoaiThuoc() {
//		ArrayList<NhomThuoc> arrayList = LoaiThuocDAO.getDSLoaiThuoc();
//		loaiThuocs.clear();
//		loaiThuocs.addAll(arrayList);
//	}

//	public void lamMoiBangLoaiThuoc() {
//		tableModel.getDataVector().removeAllElements();
//		Vector<String> vector;
//		for (NhomThuoc loaiThuoc : loaiThuocs) {
//			vector = new Vector<String>();
//			vector.add(loaiThuoc.getMaLoai());
//			vector.add(loaiThuoc.getTenLoai());
//			vector.add(loaiThuoc.getMoTa());
//			tableModel.addRow(vector);
//		}
//
//		tbLoai.updateUI();
//	}
//
//	public void themListener() {
//		btnLamMoi.addActionListener(this);
//		btnThem.addActionListener(this);
//		btnXoa.addActionListener(this);
//		btnCapNhat.addActionListener(this);
//		btnTim.addActionListener(this);
//		tbLoai.addMouseListener(this);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object object = e.getSource();
//		if (object.equals(btnLamMoi))
//			lamMoiUI();
//		else if (object.equals(btnThem))
//			xuLyThemLoaiThuoc();
//		else if (object.equals(btnXoa))
//			xuLyXoaLoaiThuoc();
//		else if (object.equals(btnCapNhat))
//			xuLyCapNhatLoaiThuoc();
//		else if (object.equals(btnTim))
//			xuLyTimLoaiThuoc();
//	}
//
//	public void xuLyThemLoaiThuoc() {
//		if (kiemTraDuLieu() == false)
//			return;
//		NhomThuoc loaiThuoc = chuyenDoiSangLoaiThuoc();
//		boolean kq = LoaiThuocDAO.themLoaiThuoc(loaiThuoc);
//		if (kq) {
//			JOptionPane.showMessageDialog(this, "Thêm loại thuốc mới thành công!");
//			lamMoiUI();
//		} else
//			JOptionPane.showMessageDialog(this, "Thêm loại thuốc mới không thành công!");
//	}
//
//	public boolean kiemTraDuLieu() {
//		if (txtTen.getText().trim().equals("")) {
//			JOptionPane.showMessageDialog(this, "Tên loại thuốc không được bỏ trống!");
//			return false;
//		}
//		return true;
//	}
//
//	public NhomThuoc chuyenDoiSangLoaiThuoc() {
//		String ma = txtMa.getText();
//		String ten = txtTen.getText().trim();
//		String mota = txaMoTa.getText().trim();
//		NhomThuoc loaiThuoc = new NhomThuoc(ma, ten, mota);
//		return loaiThuoc;
//	}
//
//	public void xuLyXoaLoaiThuoc() {
//		int selectedRow = tbLoai.getSelectedRow();
//		if (selectedRow == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thuốc cần xóa!");
//			return;
//		}
//
//		int ans = JOptionPane.showOptionDialog(this, "Bạn có chắc chắn muốn xóa loại thuốc?", "Xóa loại thuốc",
//				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//		if (ans == JOptionPane.NO_OPTION)
//			return;
//		String maloai = txtMa.getText();
//		boolean kq = LoaiThuocDAO.xoaLoaiThuoc(maloai);
//		if (kq) {
//			JOptionPane.showMessageDialog(this, "Xóa loại thuốc thành công!");
//			lamMoiUI();
//		} else
//			JOptionPane.showMessageDialog(this, "Xóa loại thuốc không thành công!");
//	}
//
//	public void xuLyCapNhatLoaiThuoc() {
//		int selectedRow = tbLoai.getSelectedRow();
//		if (selectedRow == -1) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thuốc cần cập nhật!");
//			return;
//		}
//
//		int ans = JOptionPane.showOptionDialog(this, "Bạn có chắc chắn muốn cập nhật loại thuốc?",
//				"Cập nhật loại thuốc", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//		if (ans == JOptionPane.NO_OPTION)
//			return;
//		NhomThuoc loaiThuoc = new NhomThuoc(txtMa.getText());
//		loaiThuoc.setTenLoai(txtTen.getText().trim());
//		loaiThuoc.setMoTa(txaMoTa.getText().trim());
//		boolean kq = LoaiThuocDAO.capNhatLoaiThuoc(loaiThuoc);
//		if (kq) {
//			JOptionPane.showMessageDialog(this, "Cập nhật loại thuốc thành công!");
//			lamMoiUI();
//		} else
//			JOptionPane.showMessageDialog(this, "Cập nhật loại thuốc không thành công!");
//	}
//
//	public void xuLyTimLoaiThuoc() {
//		String tim = txtTim.getText().trim();
//		if (cbTim.getSelectedIndex() == 0) {
//			if (!kiemTraMaLoaiThuoc(tim)) {
//				JOptionPane.showMessageDialog(this, "Mã loại thuốc không hợp lệ! ");
//				return;
//			}
//			NhomThuoc loaiThuoc = LoaiThuocDAO.timLoaiThuocTheoMa(tim);
//			if (loaiThuoc == null) {
//				JOptionPane.showMessageDialog(this, "Không tìm thấy loại thuốc có mã: " + tim);
//				return;
//			}
//			int row = loaiThuocs.indexOf(loaiThuoc);
//			if (row != -1) {
//				tbLoai.setRowSelectionInterval(row, row);
//				fillForm();
//			}
//		} else {
//			ArrayList<NhomThuoc> arrayList = LoaiThuocDAO.timLoaiThuocTheoTen(tim);
//			if (arrayList.size() == 0) {
//				JOptionPane.showMessageDialog(this, "Không tìm thấy loại thuốc có tên: " + tim);
//				return;
//			}
//			lamMoiUI();
//			loaiThuocs.clear();
//			loaiThuocs.addAll(arrayList);
//			lamMoiBangLoaiThuoc();
//		}
//	}
//
//	public boolean kiemTraMaLoaiThuoc(String ma) {
//		String reg = "^LOAI[0-9]{1,4}$";
//		Pattern pattern = Pattern.compile(reg);
//		Matcher matcher = pattern.matcher(ma);
//		return matcher.matches();
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
//		int selectedRow = tbLoai.getSelectedRow();
//		NhomThuoc loaiThuoc = loaiThuocs.get(selectedRow);
//		txtMa.setText(loaiThuoc.getMaLoai());
//		txtTen.setText(loaiThuoc.getTenLoai());
//		txaMoTa.setText(loaiThuoc.getMoTa());
//	}
}
