package sadnex.web.validation;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

/**
 * JSF валидатор координаты Y. Используется в range.xhtml.
 * Значение должно быть от -10 до 10 не включительно.
 * @author s4dnex
 */
public class YValidator implements Validator<Double> {
    @Override
    public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException {
        SelectBooleanCheckbox clickCheckbox = (SelectBooleanCheckbox) context.getViewRoot().findComponent("input-form:click");

        if ((Boolean) clickCheckbox.getSubmittedValue()) {
            return;
        }

        if (value < -10 || value > 10) {
            FacesMessage msg = new FacesMessage("Y must be a decimal in between -10 and 10");
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        }
    }
}
