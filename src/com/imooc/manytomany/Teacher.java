package com.imooc.manytomany;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mtm_teacher")
public class Teacher {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 20)
	private String name;

	// 默认lazy加载
	@ManyToMany(mappedBy = "teachers")  // teachers -> Student对应的属性名
	private List<Student> students;

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	public Teacher() {
	}

	public Teacher(String name) {
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
