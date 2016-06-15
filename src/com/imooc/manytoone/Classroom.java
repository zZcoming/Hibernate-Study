package com.imooc.manytoone;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mto_classroom")
public class Classroom {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")  // name要和多方的相同，否则会生成两个外键
	private List<Student> students;

	@Override
	public String toString() {
		String s = "Classroom{" +
				"id=" + id +
				", name='" + name + '\'';
		for (Student stu : students) {
			s += (", " + stu.getName());
		}
		s += '}';
		return s;
	}

	public Classroom() {
	}

	public Classroom(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
