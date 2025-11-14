package sadnex.web.bean;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Named("dateTime")
@RequestScoped
public class DateTimeBean implements Serializable {
    private static final long serialVersionUID = 52L;

    private LocalDateTime now = LocalDateTime.now();

    public String getNow() {
        return now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
