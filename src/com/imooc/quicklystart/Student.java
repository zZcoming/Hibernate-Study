package com.imooc.quicklystart;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_student")
public class Student {
	// id为字符串的话，要指定长度

//	如果主键是字符串，并且也手动赋值
//	@Id
//	@GeneratedValue(generator = "myGen")  // Gen -> Generator
//	@GenericGenerator(name = "myGen", strategy = "assigned")
//	@Column(length = 8)

	@Id
	@GeneratedValue  // 数据库默认方式   (strategy = GenerationType.AUTO)
	@Column(name = "s_id")
	private int sid;
	private String sname;
	private String gender;
	private Date birthday;
	@Transient  // 加了此注解的属性将不会出现在表中
	private String major;
	@Embedded  // 不写也可以，但是Address类上一定要写@Embeddable
	private Address address;

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", sname='" + sname + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", major='" + major + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	public Student() {
	}

	public Student(int sid, String sname, String gender, Date birthday, String major) {
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
