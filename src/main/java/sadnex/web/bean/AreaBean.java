package sadnex.web.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("area")
@RequestScoped
public class AreaBean implements Serializable {
    private static final long serialVersionUID = 52L;

    private Double x = 0.0;
    private Double y = 0.0;
    private Integer r = 1;

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
    
    public Integer getR() {
        return r;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Integer r) {
        this.r = r;
    }
}
