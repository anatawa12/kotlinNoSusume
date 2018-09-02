package com.anatawa12.infraWorkshop.kotlinNoSusume.dataClassses;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class JavaJavaBean {
	private String name;
	private int cost;

	public JavaJavaBean() {
		this.name = "";
		this.cost = 0;
	}

	public JavaJavaBean(String name, int cost) {
		if (name == null) throw new IllegalArgumentException("name is null");
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) throw new IllegalArgumentException("name is null");
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		JavaJavaBean that = (JavaJavaBean) o;

		if (cost != that.cost) return false;
		return name.equals(that.name);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + cost;
		return result;
	}

	@Override
	public String toString() {
		return "JavaJavaBean{" +
				"name='" + name + '\'' +
				", cost=" + cost +
				'}';
	}
}
