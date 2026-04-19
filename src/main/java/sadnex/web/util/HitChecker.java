package sadnex.web.util;

import sadnex.web.entity.Point;

/**
 * Интерфейс для проверки точки на попадание.
 * @author s4dnex
 */
public interface HitChecker {
    boolean checkHit(Point point);
}
