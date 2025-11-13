package sadnex.web.validation;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

public class XValidator implements Validator<Double> {
    @Override
    public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException {
        SelectBooleanCheckbox clickCheckbox = (SelectBooleanCheckbox) context.getViewRoot().findComponent("input-form:click");

        System.out.println("clickCheckbox: " + clickCheckbox.getSubmittedValue());
        if ((Boolean) clickCheckbox.getSubmittedValue()) {
            return;
        }

        if (value < -3 || value > 3) {
            FacesMessage msg = new FacesMessage("X must be a decimal in between -3 and 3");
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        }
    }
}
