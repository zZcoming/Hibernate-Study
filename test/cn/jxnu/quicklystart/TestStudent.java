package cn.jxnu.quicklystart;

import com.imooc.quicklystart.Address;
import com.imooc.quicklystart.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestStudent {

	private SessionFactory sessionFactory;
	private Session session;

	/*
		关于Address的注解@Embeddable：
			不会生成额外的表，但是会将Address的属性添加到Student对应的表中
	*/
	public static void main(String[] args) {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true);
	}

	@Before
	public void before() {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		// 生成sessionFactory
		this.sessionFactory = cfg.buildSessionFactory(serviceRegistry);

//		SchemaExport schemaExport = new SchemaExport(cfg);
//		schemaExport.create(true, true);

		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@Test
	public void query() {
		Query query = session.createQuery("from Student");
		List<Student> list = query.list();
		for (Student stu : list) {
			System.out.println(stu);
		}
	}

	@Test
	public void save() {
		Student s = new Student();
		s.setAddress(new Address("江西", "ja", "yf"));
		s.setSname("tom");
		s.setBirthday(new Date());
		session.save(s);
	}

	@After
	public void after() {
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}


