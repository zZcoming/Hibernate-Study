package cn.jxnu.onetoone;

import cn.jxnu.base.BaseDemo;
import com.imooc.onetoone.IdCard;
import com.imooc.onetoone.Student;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class OTODemo extends BaseDemo{

	// 创建表
	public static void main(String[] args) {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true);
	}

	@Test
	public void testSave() {
		IdCard card = new IdCard();
		card.setCardNo("12345678909");
		Student s = new Student();
		s.setName("Tomcat");
		s.setIdCard(card);
		session.save(s);
//		session.saveOrUpdate(s);
		/*
			使用session.save：
				对于保存的对象(Student)，(save)无论主键是否有值，直接insert一条新记录到数据库，
				对于关联的对象(IdCard)，(saveOrUpdate)如果主键有值，则根据主键update，否则也insert新纪录
			使用session.saveOrUpdate：
				对于保存的对象，(save)如果主键有值，则根据主键update，否则insert
				对于关联的对象，(saveOrUpdate)处理方式同save方法
		 */
	}

	@Test
	public void testGet() {
		Student stu = (Student) session.get(Student.class, 3);
		// 需要发两条sql语句
		System.out.println(stu);//.getIdCard().getStudent());
	}
}
