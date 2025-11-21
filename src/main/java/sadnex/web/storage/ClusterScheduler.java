package sadnex.web.storage;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class ClusterScheduler {
    @PersistenceContext(unitName = "web-lab3")
    private EntityManager entityManager;

    @Schedule(hour = "*", minute = "*/5")
    public void cluster() {
        // System.out.println("ClusterScheduler working");
        entityManager.createNativeQuery("CLUSTER points USING points_request_time_desc_idx;").executeUpdate();
        // System.out.println("ClusterScheduler stopped");
    }
}
