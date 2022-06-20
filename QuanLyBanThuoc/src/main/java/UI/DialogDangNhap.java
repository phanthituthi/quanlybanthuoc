package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.NhanVienDAO;
//import DAO.NhanVienDAO;
import Model.NhanVien;

public class DialogDangNhap extends JDialog implements ActionListener {

	private NhanVien nhanVien;
	private JTextField txtMaNV;
	private JPasswordField pwdMK;
	private JButton btnDangNhap, btnThoat;
	private NhanVienDAO nv = new NhanVienDAO();

	public DialogDangNhap(Frame owner) {
		super(owner);
		this.add(taoTieuDePanel(), BorderLayout.NORTH);
		this.add(taoInputPanel(), BorderLayout.CENTER);
		this.add(taoButtonPanel(), BorderLayout.SOUTH);

		themListener();

		this.setResizable(false);
		this.setSize(600, 220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	public JPanel taoTieuDePanel() {
		JLabel tieuDe = new JLabel("Đăng Nhập");
		tieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		JPanel pTieuDe = new JPanel();
		pTieuDe.setBackground(new Color(0, 128, 128));
		pTieuDe.add(tieuDe);

		return pTieuDe;
	}

	public JPanel taoButtonPanel() {
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		btnDangNhap.setBackground(Color.white);
		btnDangNhap.setForeground(Color.black);
		btnDangNhap.setPreferredSize(new Dimension(150, 30));

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		btnThoat.setBackground(Color.white);
		btnThoat.setForeground(Color.black);
		btnThoat.setPreferredSize(btnDangNhap.getPreferredSize());

		JPanel pButton = new JPanel();
		pButton.setBackground(new Color(0, 128, 128));
		pButton.add(btnDangNhap);
		pButton.add(btnThoat);

		return pButton;
	}

	public Box taoInputPanel() {
		JLabel manv = new JLabel("UserName:");
		manv.setPreferredSize(new Dimension(120, 30));
		manv.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		manv.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel mk = new JLabel("Password:");
		mk.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		mk.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mk.setPreferredSize(manv.getPreferredSize());

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));
		pwdMK = new JPasswordField();
		pwdMK.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 16));

		Box bInput, bMaNV, bMK;
		bInput = Box.createVerticalBox();
		bInput.setOpaque(true);
		bInput.setBackground(new Color(0, 128, 128));
		bInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		bMaNV = Box.createHorizontalBox();
		bMaNV.setBackground(new Color(0, 128, 128));
		bMaNV.add(manv);
		bMaNV.add(txtMaNV);

		bMK = Box.createHorizontalBox();
		bMK.setBackground(new Color(0, 128, 128));
		bMK.add(mk);
		bMK.add(pwdMK);

		bInput.add(bMaNV);
		bInput.add(Box.createVerticalStrut(10));
		bInput.add(bMK);

		return bInput;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void themListener() {
		btnThoat.addActionListener(this);
		btnDangNhap.addActionListener(this);
		txtMaNV.addActionListener(this);
		pwdMK.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat))
			System.exit(0);
		else if (o.equals(btnDangNhap))
			xuLyDangNhap();
		else if (o.equals(txtMaNV)) {
			if (kiemTraMaNVVaThongBao() == true)
				pwdMK.requestFocus();
		} else if (o.equals(pwdMK)) {
			if (String.copyValueOf(pwdMK.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng không để trống mật khẩu!");
				pwdMK.requestFocus();
			} else if(KiemTraMatKhau(String.copyValueOf(pwdMK.getPassword()))==false){
				JOptionPane.showMessageDialog(this,"Mật khẩu ít nhất có 6 ký tự!");
				pwdMK.requestFocus();
			}
		} else btnDangNhap.doClick();
	}

	public void xuLyDangNhap() {
		if (kiemTraDuLieu() == false)
			return;
		nhanVien = nv.layDanhSachNhanVienTheoMa(txtMaNV.getText().trim());
		if (nhanVien == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng sử dụng tài khoản tồn tại!");
			return;
		} 
		if (!String.copyValueOf(pwdMK.getPassword()).equals(nhanVien.getMatKhau())) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!");
			pwdMK.requestFocus();
			return;
		}
		//JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
		this.setVisible(false);
	}

	public boolean kiemTraDuLieu() {
		if (kiemTraMaNVVaThongBao() == false)
			return false;
		else if (String.copyValueOf(pwdMK.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng không để trống mật khẩu!");
			pwdMK.requestFocus();
			return false;
		} else if(KiemTraMatKhau(String.copyValueOf(pwdMK.getPassword()))==false){
			JOptionPane.showMessageDialog(this,"Mật khẩu ít nhất có 6 ký tự!");
			pwdMK.requestFocus();
			return false;
		}
		return true;
	}

	public boolean kiemTraMaNVVaThongBao() {
		if (txtMaNV.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên không để trống!");
			txtMaNV.requestFocus();
			return false;
		} else if (kiemTraMaNV(txtMaNV.getText().trim()) == false) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên không sai định dạng!");
			txtMaNV.requestFocus();
			return false;
		}
		return true;
	}

	public boolean kiemTraMaNV(String ma) {
		String reg = "^(NV[0-9]{4})|(QL[0-9]{4})$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(ma);
		return matcher.matches();
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String a = scanner.next();
		System.out.println(KiemTraMatKhauText(a));;
	}
	public static Boolean KiemTraMatKhauText(String mk) {
		String regexTen = "^([a-zA-Z.]+[\s][a-zA-Z.]+)|([a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+)|([a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+)|([a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+[\\s][a-zA-Z.]+)$";
		String regex = "^(.+[\s]*.+)|(.+[\s]*.+[\s]*.+)|(.+[\s]*.+[\s]*.+[\s]*.+)|(.+[\s]*.+[\s]*.+[\s]*.+[\s]*.+)$";
		return	mk.matches(regex);
	}
	
	public static Boolean KiemTraMatKhau(String mk) {
		String regex = "^([a-zA-Z0-9]*[a-zA-Z0-9]+){6,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mk);
		return matcher.matches();
	}
}
