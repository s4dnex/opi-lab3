package sadnex.web.storage;

import sadnex.web.entity.Point;

import java.io.Closeable;
import java.util.List;

public interface PointStorage extends Closeable {
    public void addPoint(Point point);

    public List<Point> getPoints();
}
