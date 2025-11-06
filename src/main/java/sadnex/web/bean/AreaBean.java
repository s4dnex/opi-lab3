package sadnex.web.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("area")
@SessionScoped
public class AreaBean implements Serializable {
    private static final long serialVersionUID = 52L;

    private Double r = 1.0;

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}
