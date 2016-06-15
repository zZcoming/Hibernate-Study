package com.imooc.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "oto_id_card")
public class IdCard {
	@Id
	@GeneratedValue
	private Integer id;
	// 身份证号
	@Column(length = 11)
	private String cardNo;
	// one to one 双向关联，mappedBy = Student对应的属性名
	@OneToOne(mappedBy = "idCard")  // idCard -> Student对应的属性名
	private Student student;

	@Override
	public String toString() {
		return "IdCard{" +
				"id=" + id +
				", cardNo='" + cardNo + '\'' +
				", student=" + student.getName() +
				'}';
	}

	public IdCard() {
	}

	public IdCard(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
