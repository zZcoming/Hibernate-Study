package cn.jxnu.manytoone;


import cn.jxnu.base.BaseDemo;
import com.imooc.manytoone.Classroom;
import com.imooc.manytoone.Student;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MTODemo extends BaseDemo{

	// 创建表
	public static void main(String[] args) {
		// hibernate配置对象
		Configuration cfg = new Configuration().configure();
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true);
	}

	@Test
	public void testSave() {
		Classroom c = new Classroom("三年级二班");

		Student s = new Student();
		s.setName("XiaoMing");
		s.setRoom(c);
		session.save(s);

		s = new Student();
		s.setName("LiHua");
		s.setRoom(c);
		session.save(s);
		/*
			多对一的save和一对一的save方式一样，会先保存关联对象
		 */

		c = new Classroom("三年级一班");

		s = new Student();
		s.setName("XiaoHua");
		s.setRoom(c);
		session.save(s);

		s = new Student();
		s.setName("LiMing");
		s.setRoom(c);
		session.save(s);
	}

	@Test
	public void saveClass() {
		/*
		在一对多保存“一”的时候，对于关联的“多”：
			如果“多”的主键为空：直接insert新的记录
			如果“多”的主键不为空：根据主键update记录
		 */
		Classroom c = new Classroom("三年级三班");

		Student s = new Student();
//		s.setSid(5);
		s.setName("XiaoMing-Change");

		List<Student> list = new ArrayList<Student>();
		list.add(s);

		c.setStudents(list);
		session.save(c);
		/*
Hibernate: 保存班级信息
    insert
    into
        mto_classroom
        (name)
    values
        (?)
Hibernate: 保存学生信息
    insert
    into
        mto_student
        (name, room_id)
    values
        (?, ?)
Hibernate: 改变学生的room_id
    update
        mto_student
    set
        room_id=?
    where
        sid=?
		 */
		/*
		当设置了student的id后：第二句sql变成了update
Hibernate:
    insert
    into
        mto_classroom
        (name)
    values
        (?)
Hibernate:
    update
        mto_student
    set
        name=?,
        room_id=?
    where
        sid=?
Hibernate:
    update
        mto_student
    set
        room_id=?
    where
        sid=?
		 */
	}

	@Test
	public void testGet() {
		System.out.println(session.get(Student.class, 3));
		System.out.println(session.get(Classroom.class, 2));
	}

	@Test
	public void testUpdate() {
		Classroom c = new Classroom("三年级二班");
		c.setId(3);
		Student s = new Student();
		s.setSid(3);
		s.setName("XiaoMing-Change");
		List<Student> list = new ArrayList<Student>();
		list.add(s);
		c.setStudents(list);
		session.update(c);
	}
}
