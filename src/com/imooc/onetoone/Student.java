package com.imooc.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "oto_student")
public class Student {

	@Id
	@GeneratedValue
	private Integer sid;
	@Column(length = 20)
	private String name;
	// 一对一关联
	@OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn的name属性：在本表生成一个字段card_id，外键关联到IdCard的主键
	/*
		如果不写@JoinColumn：
			1. 默认name为关联类的Entity.name的第一个字母小写加"_id"(IdCard->idCard_id)
			2. 默认unique = false
			3. 默认referencedColumnName = "id"（即IdCard标注了@Id的字段）
	 */
	@JoinColumn(name = "card_id")
	private IdCard idCard;

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", name='" + name + '\'' +
				", idCard=" + idCard.getCardNo() +
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

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
}
