package sadnex.web.storage;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sadnex.web.entity.Point;

import java.util.List;

@Stateless
public class HibernatePointStorage implements PointStorage {
    @PersistenceContext(unitName = "web-lab3")
    private EntityManager entityManager;

    @Override
    public void addPoint(Point point) {
        entityManager.persist(point);
    }

    @Override
    public List<Point> getPoints() {
        return entityManager.createQuery("SELECT p FROM Point p", Point.class)
                .getResultList();
    }

    @Override
    public void close() {
        // Fun fact: Wildfly manages transactions automatically via JTA
    }
}
