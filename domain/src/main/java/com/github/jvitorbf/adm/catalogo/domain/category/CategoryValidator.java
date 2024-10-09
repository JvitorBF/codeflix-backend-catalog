package com.github.jvitorbf.adm.catalogo.domain.category;

import com.github.jvitorbf.adm.catalogo.domain.validation.Error;
import com.github.jvitorbf.adm.catalogo.domain.validation.ValidationHandler;
import com.github.jvitorbf.adm.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        chechNameConstraints();
    }

    private void chechNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > 255 || length < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }

    }
}
