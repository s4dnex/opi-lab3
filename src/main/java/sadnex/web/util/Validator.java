package sadnex.web.util;

import sadnex.web.exception.ValidationException;

public interface Validator<T> {
    public void validate(T obj) throws ValidationException;
}
