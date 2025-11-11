package sadnex.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double x;
    private double y;
    private int r;
    private boolean isHit;
    private Timestamp requestTime;

    public Point() {

    }

    public Point(double x, double y, int r, Timestamp requestTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.requestTime = requestTime;
    }

    public Point(double x, double y, int r, boolean isHit, Timestamp requestTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.requestTime = requestTime;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isHit() {
        return isHit;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }
}
