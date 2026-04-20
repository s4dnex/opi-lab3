package sadnex.web.storage;

import sadnex.web.entity.Point;

import java.io.Closeable;
import java.util.List;

/**
 * Интерфейс для доступа к точкам из инфраструктурной системы хранения.
 * Наследуется от Closeable.
 * @author s4dnex
 */
public interface PointStorage extends Closeable {
    void addPoint(Point point);
    List<Point> getPoints();
}
