package com.seu.util;

public class student {
	private int id;
	private StringBuffer name = new StringBuffer();
	private int age;
	
	public student() {
		id = 0;
		age = 0;
	}
	
	public student(int id, String name, int age) {
		this.id = id;
		this.name.replace(0, name.length(), name);
		this.age = age;
	}
	
	public int getid() {
		return id;
	}
	
	public String getname() {
		return name.toString();
	}
	
	public int getage() {
		return age;
	}
	
	public void setid(int id) {
		this.id = id;
	}
	
	public void setname(String namenew) {
		this.name.replace(0, this.name.length(), namenew);
	}
	
	public void setage(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "id="+id+"++"+"name="+name+"++"+"age="+age;
	}
	
	public static void main(String[] args) {
		
	}

}
