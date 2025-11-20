package sadnex.web.bean;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Named("dateTime")
@ApplicationScoped
public class DateTimeBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 52L;

    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
