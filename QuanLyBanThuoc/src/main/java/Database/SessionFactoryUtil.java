package Database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import Model.CTHoaDonPK;
import Model.ChiTietHoaDon;
import Model.HoaDon;
import Model.KhachHang;
import Model.LoThuoc;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.NhomThuoc;
import Model.Thuoc;

public class SessionFactoryUtil {
	private static SessionFactory sessionFactory;
	
	static {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		
		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(Thuoc.class)
				.addAnnotatedClass(NhomThuoc.class)
				.addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(NhaCungCap.class)
				.addAnnotatedClass(LoThuoc.class)
				.addAnnotatedClass(KhachHang.class)
				.addAnnotatedClass(HoaDon.class)
				.addAnnotatedClass(CTHoaDonPK.class)
				.addAnnotatedClass(ChiTietHoaDon.class)
				.getMetadataBuilder().build();
		
		sessionFactory = metadata.getSessionFactoryBuilder().build();
				
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

}
