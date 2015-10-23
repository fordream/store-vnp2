package com.example.detechbluetooth;

public class Device {
	private String name;
	private String address;
	private int second;
	private int minute;
	private int hour;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public Device (String name, String address) {
		this.name = name;
		this.address = address;
	}
	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
}
