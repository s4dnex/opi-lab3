package sadnex.web.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import sadnex.web.entity.Point;

/**
 * CDI-бин. Нужен для проверки попадания точки в меч.
 * @author s4dnex
 */
@Named("swordHitChecker")
@ApplicationScoped
public class SwordHitChecker implements HitChecker {
    @Override
    public boolean checkHit(Point point) {
        // Sword's core
        if (Math.abs(point.getY()) <= point.getR()) {
            return Math.abs(point.getX()) <= point.getR() / 5.0;
        }

        // Sword's edge
        if (Math.abs(point.getX()) <= point.getR() / 5.0
                && point.getY() <= -point.getR()) {
            return true;
        }

        // Handle's edges
        if (point.getR() / 10.0 <= Math.abs(point.getX())
                && Math.abs(point.getX()) <= point.getR() / 2.0) {
            return point.getY() >= point.getR()
                    && point.getY() <= (-0.5 * Math.abs(point.getX()) + 1.25 * point.getR());
        }

        // Handle's core
        if (point.getR() <= point.getY()
                && point.getY() <= 1.9 * point.getR()) {
            return Math.abs(point.getX()) <= point.getR() / 10.0;
        }

        // Handle's top half-circle
        if (point.getY() >= 1.9 * point.getR()) {
            return Math.pow(point.getY() - 1.9 * point.getR(), 2) <= (Math.pow(point.getR() / 10.0, 2) - Math.pow(point.getX(), 2));
        }

        return false;
    }
}
