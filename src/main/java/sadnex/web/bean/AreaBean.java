package sadnex.web.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sadnex.web.util.Validator;
import sadnex.web.entity.Point;
import sadnex.web.storage.PointStorage;
import sadnex.web.util.HitChecker;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Named("area")
@RequestScoped
public class AreaBean implements Serializable {
    private static final long serialVersionUID = 52L;

    @Inject
    private PointStorage pointStorage;

    @Inject
    private HitChecker hitChecker;

    @Inject
    private @Named("pointValidator") Validator<Point> validator;

    private Double x = 0.0;
    private Double y = 0.0;
    private Integer r = 1;
    private Boolean isClick = false;

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Integer getR() {
        return r;
    }

    public Boolean getIsClick() {
        return isClick;
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

    public void setIsClick(Boolean isClick) {
        this.isClick = isClick;
    }

    public void createPoint() {
        Point point = new Point(x, y, r, Timestamp.valueOf(LocalDateTime.now()));
        if (!isClick) {
            validator.validate(point);
        }
        point.setHit(hitChecker.checkHit(point));
        pointStorage.addPoint(point);
    }

    public List<Point> getPoints() {
        return pointStorage.getPoints().reversed();
    }
}
