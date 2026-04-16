package sadnex.web.util;

import org.junit.Before;
import org.junit.Test;
import sadnex.web.entity.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SwordHitCheckerTest {
    private SwordHitChecker hitChecker;

    @Before
    public void setUp() {
        hitChecker = new SwordHitChecker();
    }

    @Test
    public void checkHit() {
        List<Point> hitPoints = new ArrayList<>();
        hitPoints.add(new Point(0, 0, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0.2, 0, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0, -1.5, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0.25, 1.05, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0, 1.5, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0, 1.95, 1, LocalDateTime.now()));
        hitPoints.add(new Point(0.08, 1.92, 1, LocalDateTime.now()));

        for (Point point : hitPoints) {
            assertTrue(hitChecker.checkHit(point));
        }

        List<Point> missedPoints = new ArrayList<>();
        missedPoints.add(new Point(0.21, 0, 1, LocalDateTime.now()));
        missedPoints.add(new Point(0.15, 1.5, 1, LocalDateTime.now()));
        missedPoints.add(new Point(0, 2.2, 1, LocalDateTime.now()));
        missedPoints.add(new Point(0.6, 1.1, 1, LocalDateTime.now()));
        missedPoints.add(new Point(0.3, -1.5, 1, LocalDateTime.now()));

        for (Point point : missedPoints) {
            assertFalse(hitChecker.checkHit(point));
        }
    }
}
