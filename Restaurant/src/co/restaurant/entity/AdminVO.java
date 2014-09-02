package co.restaurant.entity;

public class AdminVO {
	private int id;
	private String login;
	private String password;
	private String name;
	private String address1;
	private String address2;
	private String state;
	private String zip;
	private String weekdayAm;
	private String weekdayPm;
	private String weekendAm;
	private String weekendPm;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getWeekdayAm() {
		return weekdayAm;
	}
	public void setWeekdayAm(String weekdayAm) {
		this.weekdayAm = weekdayAm;
	}
	public String getWeekdayPm() {
		return weekdayPm;
	}
	public void setWeekdayPm(String weekdayPm) {
		this.weekdayPm = weekdayPm;
	}
	public String getWeekendAm() {
		return weekendAm;
	}
	public void setWeekendAm(String weekendAm) {
		this.weekendAm = weekendAm;
	}
	public String getWeekendPm() {
		return weekendPm;
	}
	public void setWeekendPm(String weekendPm) {
		this.weekendPm = weekendPm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String admin) {
		this.login = admin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String login) {
		this.password = login;
	}
	
	
}
