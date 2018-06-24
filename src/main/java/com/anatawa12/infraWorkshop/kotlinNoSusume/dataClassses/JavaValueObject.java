package com.anatawa12.infraWorkshop.kotlinNoSusume.dataClassses;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class JavaValueObject {
	private final String name;
	private final int cost;

	public JavaValueObject(String name, int cost) {
		if (name == null) throw new IllegalArgumentException("name is null");
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		JavaValueObject that = (JavaValueObject) o;

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
		return "JavaValueObject{" +
				"name='" + name + '\'' +
				", cost=" + cost +
				'}';
	}
}
