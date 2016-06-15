package com.imooc.manytoone;

import javax.persistence.*;

@Entity(name = "mtoStudent")
@Table(name = "mto_student")
public class Student {

	@Id
	@GeneratedValue
	private Integer sid;
	@Column(length = 20)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id")
	private Classroom room;

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", name='" + name + '\'' +
				", room=" + room +
				'}';
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

	public Classroom getRoom() {
		return room;
	}

	public void setRoom(Classroom room) {
		this.room = room;
	}
}
