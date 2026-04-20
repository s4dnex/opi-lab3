package sadnex.web.storage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sadnex.web.entity.Point;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class HibernatePointStorageTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private HibernatePointStorage storage;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("web-lab3-test");
        entityManager = entityManagerFactory.createEntityManager();
        storage = new HibernatePointStorage(entityManager);

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("INSERT INTO points (x, y, r, is_hit, request_time) VALUES (1.0, 2.0, 3, false, '2025-04-15 10:00:00')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO points (x, y, r, is_hit, request_time) VALUES (4.0, 5.0, 6, false, '2025-04-16 12:30:00')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO points (x, y, r, is_hit, request_time) VALUES (7.0, 8.0, 9, true, '2025-04-16 15:45:00')").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @After
    public void destroy() {
        if (entityManager != null && entityManager.isOpen()) entityManager.close();
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) entityManagerFactory.close();
    }

    @Test
    public void getPoints() {
        List<Point> points = storage.getPoints();

        assertEquals(3, points.size());

        Point p1 = points.get(0);
        Point p2 = points.get(1);
        Point p3 = points.get(2);

        assertEquals(7.0, p1.getX(), 0.0001);
        assertEquals(8.0, p1.getY(), 0.0001);
        assertEquals(9, p1.getR());
        assertTrue(p1.isHit());
        assertEquals(LocalDateTime.of(2025, 4, 16, 15, 45), p1.getRequestTime());

        assertEquals(4.0, p2.getX(), 0.0001);
        assertEquals(5.0, p2.getY(), 0.0001);
        assertEquals(6, p2.getR());
        assertFalse(p2.isHit());
        assertEquals(LocalDateTime.of(2025, 4, 16, 12, 30), p2.getRequestTime());

        assertEquals(1.0, p3.getX(), 0.0001);
        assertEquals(2.0, p3.getY(), 0.0001);
        assertEquals(3, p3.getR());
        assertFalse(p3.isHit());
        assertEquals(LocalDateTime.of(2025, 4, 15, 10, 0), p3.getRequestTime());
    }

    @Test
    public void addPoint() {
        Point newPoint = new Point(10.0, 20.0, 30, LocalDateTime.of(2025, 4, 17, 9, 0));

        entityManager.getTransaction().begin();
        storage.addPoint(newPoint);
        entityManager.getTransaction().commit();

        List<Point> points = storage.getPoints();
        assertEquals(4, points.size());
        assertEquals(newPoint.getRequestTime(), points.getFirst().getRequestTime());
    }
}