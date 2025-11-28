package sadnex.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private int r;

    @Column(name = "is_hit")
    private boolean isHit;

    @NotNull
    @Column(name = "request_time")
    private LocalDateTime requestTime;

    public Point() {

    }

    public Point(double x, double y, int r, LocalDateTime requestTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.requestTime = requestTime;
    }

    public Point(double x, double y, int r, boolean isHit, LocalDateTime requestTime) {
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

    public LocalDateTime getRequestTime() {
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

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
