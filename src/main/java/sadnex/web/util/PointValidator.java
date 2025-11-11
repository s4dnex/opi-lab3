package sadnex.web.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import sadnex.web.entity.Point;
import sadnex.web.exception.ValidationException;

import java.util.Set;

@Named("pointValidator")
@ApplicationScoped
public class PointValidator implements Validator<Point> {
    @Override
    public void validate(Point point) throws ValidationException {
        if (point.getX() < -3 || point.getX() > 3) {
            throw new ValidationException("X must be a decimal in between -3 and 3");
        }

        if (point.getY() < -10 || point.getY() > 10) {
            throw new ValidationException("Y must be a decimal in between -10 and 10");
        }

        if (!Set.of(1, 2, 3, 4, 5).contains(point.getR())) {
            throw new ValidationException("R must be an integer in range from 1 to 5");
        }
    }
}
