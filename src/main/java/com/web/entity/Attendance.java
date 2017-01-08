package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity

@Table(name="attendance")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Attendance implements Serializable{

	private Integer id;
	private Integer createYear;
	private Integer createMonth;
	private User user;
	private Classes classes;
	
	private Integer dayMorning1;
	private Integer dayAfternoon1;
	private Integer dayMorning2;
	private Integer dayAfternoon2;
	private Integer dayMorning3;
	private Integer dayAfternoon3;
	private Integer dayMorning4;
	private Integer dayAfternoon4;
	private Integer dayMorning5;
	private Integer dayAfternoon5;
	private Integer dayMorning6;
	private Integer dayAfternoon6;
	private Integer dayMorning7;
	private Integer dayAfternoon7;
	private Integer dayMorning8;
	private Integer dayAfternoon8;
	private Integer dayMorning9;
	private Integer dayAfternoon9;
	private Integer dayMorning10;
	private Integer dayAfternoon10;
	private Integer dayMorning11;
	private Integer dayAfternoon11;
	private Integer dayMorning12;
	private Integer dayAfternoon12;
	private Integer dayMorning13;
	private Integer dayAfternoon13;
	private Integer dayMorning14;
	private Integer dayAfternoon14;
	private Integer dayMorning15;
	private Integer dayAfternoon15;
	private Integer dayMorning16;
	private Integer dayAfternoon16;
	private Integer dayMorning17;
	private Integer dayAfternoon17;
	private Integer dayMorning18;
	private Integer dayAfternoon18;
	private Integer dayMorning19;
	private Integer dayAfternoon19;
	private Integer dayMorning20;
	private Integer dayAfternoon20;
	private Integer dayMorning21;
	private Integer dayAfternoon21;
	private Integer dayMorning22;
	private Integer dayAfternoon22;
	private Integer dayMorning23;
	private Integer dayAfternoon23;
	private Integer dayMorning24;
	private Integer dayAfternoon24;
	private Integer dayMorning25;
	private Integer dayAfternoon25;
	private Integer dayMorning26;
	private Integer dayAfternoon26;
	private Integer dayMorning27;
	private Integer dayAfternoon27;
	private Integer dayMorning28;
	private Integer dayAfternoon28;
	private Integer dayMorning29;
	private Integer dayAfternoon29;
	private Integer dayMorning30;
	private Integer dayAfternoon30;
	private Integer dayMorning31;
	private Integer dayAfternoon31;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreateYear() {
		return createYear;
	}
	public void setCreateYear(Integer createYear) {
		this.createYear = createYear;
	}
	public Integer getCreateMonth() {
		return createMonth;
	}
	public void setCreateMonth(Integer createMonth) {
		this.createMonth = createMonth;
	}
	
	@ManyToOne
	@JoinColumn(name="stuId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="classesId")
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning1() {
		return dayMorning1;
	}
	public void setDayMorning1(Integer dayMorning1) {
		this.dayMorning1 = dayMorning1;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon1() {
		return dayAfternoon1;
	}
	public void setDayAfternoon1(Integer dayAfternoon1) {
		this.dayAfternoon1 = dayAfternoon1;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning2() {
		return dayMorning2;
	}
	public void setDayMorning2(Integer dayMorning2) {
		this.dayMorning2 = dayMorning2;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon2() {
		return dayAfternoon2;
	}
	public void setDayAfternoon2(Integer dayAfternoon2) {
		this.dayAfternoon2 = dayAfternoon2;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning3() {
		return dayMorning3;
	}
	public void setDayMorning3(Integer dayMorning3) {
		this.dayMorning3 = dayMorning3;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon3() {
		return dayAfternoon3;
	}
	public void setDayAfternoon3(Integer dayAfternoon3) {
		this.dayAfternoon3 = dayAfternoon3;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning4() {
		return dayMorning4;
	}
	public void setDayMorning4(Integer dayMorning4) {
		this.dayMorning4 = dayMorning4;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon4() {
		return dayAfternoon4;
	}
	public void setDayAfternoon4(Integer dayAfternoon4) {
		this.dayAfternoon4 = dayAfternoon4;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning5() {
		return dayMorning5;
	}
	public void setDayMorning5(Integer dayMorning5) {
		this.dayMorning5 = dayMorning5;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon5() {
		return dayAfternoon5;
	}
	public void setDayAfternoon5(Integer dayAfternoon5) {
		this.dayAfternoon5 = dayAfternoon5;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning6() {
		return dayMorning6;
	}
	public void setDayMorning6(Integer dayMorning6) {
		this.dayMorning6 = dayMorning6;
	}
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon6() {
		return dayAfternoon6;
	}
	public void setDayAfternoon6(Integer dayAfternoon6) {
		this.dayAfternoon6 = dayAfternoon6;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning7() {
		return dayMorning7;
	}
	public void setDayMorning7(Integer dayMorning7) {
		this.dayMorning7 = dayMorning7;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon7() {
		return dayAfternoon7;
	}
	public void setDayAfternoon7(Integer dayAfternoon7) {
		this.dayAfternoon7 = dayAfternoon7;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning8() {
		return dayMorning8;
	}
	public void setDayMorning8(Integer dayMorning8) {
		this.dayMorning8 = dayMorning8;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon8() {
		return dayAfternoon8;
	}
	public void setDayAfternoon8(Integer dayAfternoon8) {
		this.dayAfternoon8 = dayAfternoon8;
	}
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning9() {
		return dayMorning9;
	}
	public void setDayMorning9(Integer dayMorning9) {
		this.dayMorning9 = dayMorning9;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon9() {
		return dayAfternoon9;
	}
	public void setDayAfternoon9(Integer dayAfternoon9) {
		this.dayAfternoon9 = dayAfternoon9;
	}
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning10() {
		return dayMorning10;
	}
	public void setDayMorning10(Integer dayMorning10) {
		this.dayMorning10 = dayMorning10;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon10() {
		return dayAfternoon10;
	}
	public void setDayAfternoon10(Integer dayAfternoon10) {
		this.dayAfternoon10 = dayAfternoon10;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning11() {
		return dayMorning11;
	}
	public void setDayMorning11(Integer dayMorning11) {
		this.dayMorning11 = dayMorning11;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon11() {
		return dayAfternoon11;
	}
	public void setDayAfternoon11(Integer dayAfternoon11) {
		this.dayAfternoon11 = dayAfternoon11;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning12() {
		return dayMorning12;
	}
	public void setDayMorning12(Integer dayMorning12) {
		this.dayMorning12 = dayMorning12;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon12() {
		return dayAfternoon12;
	}
	public void setDayAfternoon12(Integer dayAfternoon12) {
		this.dayAfternoon12 = dayAfternoon12;
	}
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning13() {
		return dayMorning13;
	}
	public void setDayMorning13(Integer dayMorning13) {
		this.dayMorning13 = dayMorning13;
	}
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon13() {
		return dayAfternoon13;
	}
	public void setDayAfternoon13(Integer dayAfternoon13) {
		this.dayAfternoon13 = dayAfternoon13;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning14() {
		return dayMorning14;
	}
	public void setDayMorning14(Integer dayMorning14) {
		this.dayMorning14 = dayMorning14;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon14() {
		return dayAfternoon14;
	}
	public void setDayAfternoon14(Integer dayAfternoon14) {
		this.dayAfternoon14 = dayAfternoon14;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning15() {
		return dayMorning15;
	}
	public void setDayMorning15(Integer dayMorning15) {
		this.dayMorning15 = dayMorning15;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon15() {
		return dayAfternoon15;
	}
	public void setDayAfternoon15(Integer dayAfternoon15) {
		this.dayAfternoon15 = dayAfternoon15;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning16() {
		return dayMorning16;
	}
	public void setDayMorning16(Integer dayMorning16) {
		this.dayMorning16 = dayMorning16;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon16() {
		return dayAfternoon16;
	}
	public void setDayAfternoon16(Integer dayAfternoon16) {
		this.dayAfternoon16 = dayAfternoon16;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning17() {
		return dayMorning17;
	}
	public void setDayMorning17(Integer dayMorning17) {
		this.dayMorning17 = dayMorning17;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon17() {
		return dayAfternoon17;
	}
	public void setDayAfternoon17(Integer dayAfternoon17) {
		this.dayAfternoon17 = dayAfternoon17;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning18() {
		return dayMorning18;
	}
	public void setDayMorning18(Integer dayMorning18) {
		this.dayMorning18 = dayMorning18;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon18() {
		return dayAfternoon18;
	}
	public void setDayAfternoon18(Integer dayAfternoon18) {
		this.dayAfternoon18 = dayAfternoon18;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning19() {
		return dayMorning19;
	}
	public void setDayMorning19(Integer dayMorning19) {
		this.dayMorning19 = dayMorning19;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon19() {
		return dayAfternoon19;
	}
	public void setDayAfternoon19(Integer dayAfternoon19) {
		this.dayAfternoon19 = dayAfternoon19;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning20() {
		return dayMorning20;
	}
	public void setDayMorning20(Integer dayMorning20) {
		this.dayMorning20 = dayMorning20;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon20() {
		return dayAfternoon20;
	}
	public void setDayAfternoon20(Integer dayAfternoon20) {
		this.dayAfternoon20 = dayAfternoon20;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning21() {
		return dayMorning21;
	}
	public void setDayMorning21(Integer dayMorning21) {
		this.dayMorning21 = dayMorning21;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon21() {
		return dayAfternoon21;
	}
	public void setDayAfternoon21(Integer dayAfternoon21) {
		this.dayAfternoon21 = dayAfternoon21;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning22() {
		return dayMorning22;
	}
	public void setDayMorning22(Integer dayMorning22) {
		this.dayMorning22 = dayMorning22;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon22() {
		return dayAfternoon22;
	}
	public void setDayAfternoon22(Integer dayAfternoon22) {
		this.dayAfternoon22 = dayAfternoon22;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning23() {
		return dayMorning23;
	}
	public void setDayMorning23(Integer dayMorning23) {
		this.dayMorning23 = dayMorning23;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon23() {
		return dayAfternoon23;
	}
	public void setDayAfternoon23(Integer dayAfternoon23) {
		this.dayAfternoon23 = dayAfternoon23;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning24() {
		return dayMorning24;
	}
	public void setDayMorning24(Integer dayMorning24) {
		this.dayMorning24 = dayMorning24;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon24() {
		return dayAfternoon24;
	}
	public void setDayAfternoon24(Integer dayAfternoon24) {
		this.dayAfternoon24 = dayAfternoon24;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning25() {
		return dayMorning25;
	}
	public void setDayMorning25(Integer dayMorning25) {
		this.dayMorning25 = dayMorning25;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon25() {
		return dayAfternoon25;
	}
	public void setDayAfternoon25(Integer dayAfternoon25) {
		this.dayAfternoon25 = dayAfternoon25;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning26() {
		return dayMorning26;
	}
	public void setDayMorning26(Integer dayMorning26) {
		this.dayMorning26 = dayMorning26;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon26() {
		return dayAfternoon26;
	}
	public void setDayAfternoon26(Integer dayAfternoon26) {
		this.dayAfternoon26 = dayAfternoon26;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning27() {
		return dayMorning27;
	}
	public void setDayMorning27(Integer dayMorning27) {
		this.dayMorning27 = dayMorning27;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon27() {
		return dayAfternoon27;
	}
	public void setDayAfternoon27(Integer dayAfternoon27) {
		this.dayAfternoon27 = dayAfternoon27;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning28() {
		return dayMorning28;
	}
	public void setDayMorning28(Integer dayMorning28) {
		this.dayMorning28 = dayMorning28;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon28() {
		return dayAfternoon28;
	}
	public void setDayAfternoon28(Integer dayAfternoon28) {
		this.dayAfternoon28 = dayAfternoon28;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning29() {
		return dayMorning29;
	}
	public void setDayMorning29(Integer dayMorning29) {
		this.dayMorning29 = dayMorning29;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon29() {
		return dayAfternoon29;
	}
	public void setDayAfternoon29(Integer dayAfternoon29) {
		this.dayAfternoon29 = dayAfternoon29;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning30() {
		return dayMorning30;
	}
	public void setDayMorning30(Integer dayMorning30) {
		this.dayMorning30 = dayMorning30;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon30() {
		return dayAfternoon30;
	}
	public void setDayAfternoon30(Integer dayAfternoon30) {
		this.dayAfternoon30 = dayAfternoon30;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayMorning31() {
		return dayMorning31;
	}
	public void setDayMorning31(Integer dayMorning31) {
		this.dayMorning31 = dayMorning31;
	}
	
	@Column(columnDefinition="int default 1")
	public Integer getDayAfternoon31() {
		return dayAfternoon31;
	}
	public void setDayAfternoon31(Integer dayAfternoon31) {
		this.dayAfternoon31 = dayAfternoon31;
	}
	
	
	
}
