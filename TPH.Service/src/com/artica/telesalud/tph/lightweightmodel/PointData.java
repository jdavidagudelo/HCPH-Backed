package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;

public class PointData implements Serializable{
	private int x, y;
	
	private int number;

	public PointData() {
		super();
	}

	public PointData(int x, int y, int number) {
		super();
		this.x = x;
		this.y = y;
		this.number = number;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
