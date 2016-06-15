package cn.jxnu.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

public class BaseDemo {

	protected SessionFactory sessionFactory;
	protected Session session;

	@Before
	public void before() {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		// 生成sessionFactory
		this.sessionFactory = cfg.buildSessionFactory(serviceRegistry);

		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@After
	public void after() {
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
