package poly.com.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;







public class conect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		create();
	}
	public static void create() { // CREATE = INSERT
		// Nạp persistence.xml và tạo EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dat");
		// Tạo EntityManager để bắt đầu làm việc với CSDL
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin(); // Bắt đầu Transaction
			
			// MÃ THAO TÁC
			// Tạo Entity
			User entity = new User();
			
			
			entity.setEmail("ty@gmail.com");
			entity.setPassword("123");
			entity.setActive(true);
			entity.setAdmin(true);
			entity.setUsername("dat121");
			// Insert vào CSDL
			em.persist(entity);  

			em.getTransaction().commit(); // Chấp nhận kết quả thao tác
			System.out.println("Thêm mới thành công!");
		} catch (Exception e) {
			em.getTransaction().rollback(); // Hủy thao tác
			System.out.println(e+"Thêm mới thất bại!");
		}
		em.close();
		emf.close();
	}

}
