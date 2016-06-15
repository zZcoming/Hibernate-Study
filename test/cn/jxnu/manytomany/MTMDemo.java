package cn.jxnu.manytomany;

import cn.jxnu.base.BaseDemo;
import com.imooc.manytomany.Student;
import com.imooc.manytomany.Teacher;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MTMDemo extends BaseDemo {

	// 创建表
	public static void main(String[] args) {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true);
	}

	@Test
	public void testSave() {
		/*
		save不会自动保存关联的对象，必须先手动保存关联对象
		 */
		Teacher zhang = new Teacher("tech.Zhang");
		Teacher li = new Teacher("tech.Li");
		Teacher zhao = new Teacher("tech.Zhao");
		session.save(zhang);
		session.save(li);
		session.save(zhao);

		List<Teacher> list = new ArrayList<Teacher>();
		list.add(zhang);
		list.add(li);
		Student s = new Student();
		s.setName("XiaoMing");
		s.setTeachers(list);
		session.save(s);

		list = new ArrayList<Teacher>();
		list.add(zhang);
		list.add(zhao);
		s = new Student();
		s.setName("LiHua");
		s.setTeachers(list);
		session.save(s);
	}

	@Test
	public void testUpdate() {
		Teacher zhang = new Teacher("tech.Zhang.Change");
		zhang.setId(1);
		Teacher li = new Teacher("tech.Li.Change");
		li.setId(2);

		List<Teacher> list = new ArrayList<Teacher>();
		list.add(zhang);
		list.add(li);
		Student s = new Student();
		s.setSid(3);
		s.setName("XiaoMing");
		s.setTeachers(list);
		session.update(s);
		/*
//  从以下sql语句中可以看出，update只关心关联对象的主键的值，其他的变动会忽略（因为没有update teacher表）
Hibernate: 修改Student表
    update
        mtm_student
    set
        name=?
    where
        sid=?
Hibernate: 先删除中间表中的对应记录
    delete
    from
        teacher_student
    where
        sid=?
Hibernate: 将对应关联关系加入中间表（2个关联对象）
    insert
    into
        teacher_student
        (sid, tid)
    values
        (?, ?)
Hibernate:
    insert
    into
        teacher_student
        (sid, tid)
    values
        (?, ?)
		 */
	}

	@Test
	 public void getStudent() {
		List<Student> list = session.createQuery("from mtmStudent").list();
		for (Student s : list) {
			System.out.println(s.getName());
			System.out.println(s);
		}
	}

	@Test
	public void getTeacher() {
		List<Teacher> list = session.createQuery("from Teacher").list();
		for (Teacher t : list) {
			System.out.println(t);
			for (Student s : t.getStudents()) {
				System.out.println(s);
			}
		}
	}

}
