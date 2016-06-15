package com.imooc.manytomany;

import javax.persistence.*;
import java.util.List;

@Entity(name = "mtmStudent")
@Table(name = "mtm_student")
public class Student {

	@Id
	@GeneratedValue
	private Integer sid;
	@Column(length = 20)
	private String name;

	@ManyToMany  // 默认是lazy加载
	@JoinTable(
		name = "teacher_student",  // 中间表的表名
		joinColumns = {@JoinColumn(name = "sid")},  // 本类的主键
		inverseJoinColumns = {@JoinColumn(name = "tid")}  // 对应类的主键
	)
	private List<Teacher> teachers;

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", name='" + name + '\'' +
				", teachers=" + teachers +
				'}';
	}

	public Student(String name, List<Teacher> teachers) {
		this.name = name;
		this.teachers = teachers;
	}

	public Student() {
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
}
