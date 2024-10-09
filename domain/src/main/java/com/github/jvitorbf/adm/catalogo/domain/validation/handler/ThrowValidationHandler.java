package com.github.jvitorbf.adm.catalogo.domain.validation.handler;

import com.github.jvitorbf.adm.catalogo.domain.exceptions.DomainException;
import com.github.jvitorbf.adm.catalogo.domain.validation.Error;
import com.github.jvitorbf.adm.catalogo.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception e) {
            throw DomainException.with(new Error(e.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
