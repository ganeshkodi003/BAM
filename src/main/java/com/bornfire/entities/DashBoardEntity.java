package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DASHBOARD_MANAGER")
public class DashBoardEntity {

	@Id
	private String screen_id;
	private String screen_name;
	private String dash_board_1;
	private String dash_board_2;
	private String dash_board_3;
	private String dash_board_4;
	private String dash_board_5;
	private String description_1;
	private String description_2;
	private String description_3;
	private String description_4;
	private String description_5;
	private String entry_user;
	private Date entry_date;
	private String modify_user;
	private Date modify_date;
	private String verify_user;
	private Date verify_date;
	public String getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(String screen_id) {
		this.screen_id = screen_id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getDash_board_1() {
		return dash_board_1;
	}
	public void setDash_board_1(String dash_board_1) {
		this.dash_board_1 = dash_board_1;
	}
	public String getDash_board_2() {
		return dash_board_2;
	}
	public void setDash_board_2(String dash_board_2) {
		this.dash_board_2 = dash_board_2;
	}
	public String getDash_board_3() {
		return dash_board_3;
	}
	public void setDash_board_3(String dash_board_3) {
		this.dash_board_3 = dash_board_3;
	}
	public String getDash_board_4() {
		return dash_board_4;
	}
	public void setDash_board_4(String dash_board_4) {
		this.dash_board_4 = dash_board_4;
	}
	public String getDash_board_5() {
		return dash_board_5;
	}
	public void setDash_board_5(String dash_board_5) {
		this.dash_board_5 = dash_board_5;
	}
	public String getDescription_1() {
		return description_1;
	}
	public void setDescription_1(String description_1) {
		this.description_1 = description_1;
	}
	public String getDescription_2() {
		return description_2;
	}
	public void setDescription_2(String description_2) {
		this.description_2 = description_2;
	}
	public String getDescription_3() {
		return description_3;
	}
	public void setDescription_3(String description_3) {
		this.description_3 = description_3;
	}
	public String getDescription_4() {
		return description_4;
	}
	public void setDescription_4(String description_4) {
		this.description_4 = description_4;
	}
	public String getDescription_5() {
		return description_5;
	}
	public void setDescription_5(String description_5) {
		this.description_5 = description_5;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public DashBoardEntity(String screen_id, String screen_name, String dash_board_1, String dash_board_2,
			String dash_board_3, String dash_board_4, String dash_board_5, String description_1, String description_2,
			String description_3, String description_4, String description_5, String entry_user, Date entry_date,
			String modify_user, Date modify_date, String verify_user, Date verify_date) {
		super();
		this.screen_id = screen_id;
		this.screen_name = screen_name;
		this.dash_board_1 = dash_board_1;
		this.dash_board_2 = dash_board_2;
		this.dash_board_3 = dash_board_3;
		this.dash_board_4 = dash_board_4;
		this.dash_board_5 = dash_board_5;
		this.description_1 = description_1;
		this.description_2 = description_2;
		this.description_3 = description_3;
		this.description_4 = description_4;
		this.description_5 = description_5;
		this.entry_user = entry_user;
		this.entry_date = entry_date;
		this.modify_user = modify_user;
		this.modify_date = modify_date;
		this.verify_user = verify_user;
		this.verify_date = verify_date;
	}
	public DashBoardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
