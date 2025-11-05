package sadnex.web.bean;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named("dateTime")
@RequestScoped
public class DateTimeBean implements Serializable {
    private static final long serialVersionUID = 52L;

    private LocalDateTime now = LocalDateTime.now();

    public LocalDateTime getNow() {
        return now;
    }
}
