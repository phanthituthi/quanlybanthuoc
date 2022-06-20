package Service;

import java.util.List;

import Model.NhomThuoc;

public interface LoaiThuocService {

	public List<NhomThuoc> layDanhSachLoaiThuoc();
	public NhomThuoc timNhomThuocTheoTen(String ten);
}
