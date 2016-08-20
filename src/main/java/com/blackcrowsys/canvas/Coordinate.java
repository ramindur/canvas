package com.blackcrowsys.canvas;

/**
 * Created by ramindursingh on 20/08/2016.
 */
public class Coordinate {

    private int x;

    private int y;


    public Coordinate(){}

    public Coordinate(String xPoint, String yPoint) {
        this.x = Integer.parseInt(xPoint);
        this.y = Integer.parseInt(yPoint);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
