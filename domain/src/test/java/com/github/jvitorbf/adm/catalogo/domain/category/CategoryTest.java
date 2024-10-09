package com.github.jvitorbf.adm.catalogo.domain.category;

import com.github.jvitorbf.adm.catalogo.domain.exceptions.DomainException;
import com.github.jvitorbf.adm.catalogo.domain.validation.handler.ThrowValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var newCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());
    }


    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReaciveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var currentCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var currentException = Assertions.assertThrows(DomainException.class, () -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReaciveError() {
        final String expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var currentCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var currentException = Assertions.assertThrows(DomainException.class, () -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReaciveError() {
        final String expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var currentCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var currentException = Assertions.assertThrows(DomainException.class, () -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReaciveError() {
        final String expectedName = """
                Evidentemente, a revolução dos costumes nos obriga à análise do investimento em reciclagem técnica.
                Por outro lado, a complexidade dos estudos efetuados agrega valor ao estabelecimento dos relacionamentos
                verticais entre as hierarquias. Todas estas questões, devidamente ponderadas, levantam dúvidas.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var currentCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var currentException = Assertions.assertThrows(DomainException.class, () -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, currentException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, currentException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldNotReaciveError() {
        final String expectedName = "Filmes";
        final var expectedDescription = "  ";
        final var expectedIsActive = true;

        final var newCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow(() -> newCategory.validate(new ThrowValidationHandler()));
        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNull(newCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldNotReaciveError() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A melhor categoria";
        final var expectedIsActive = false;

        final var newCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertDoesNotThrow(() -> newCategory.validate(new ThrowValidationHandler()));
        Assertions.assertNotNull(newCategory);
        Assertions.assertNotNull(newCategory.getId());
        Assertions.assertEquals(expectedName, newCategory.getName());
        Assertions.assertEquals(expectedDescription, newCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, newCategory.isActive());
        Assertions.assertNotNull(newCategory.getCreatedAt());
        Assertions.assertNotNull(newCategory.getUpdatedAt());
        Assertions.assertNotNull(newCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A melhor categoria";
        final var expectedIsActive = false;

        final var newCategory = Category.newCategory(expectedName, expectedDescription, true);

        Assertions.assertDoesNotThrow(() -> newCategory.validate(new ThrowValidationHandler()));

        final Instant createdAt = newCategory.getCreatedAt();
        final Instant updatedAt = newCategory.getUpdatedAt();

        Assertions.assertTrue(newCategory.isActive());
        Assertions.assertNull(newCategory.getDeletedAt());

        final var currentCategory = newCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(currentCategory.getId(), newCategory.getId());
        Assertions.assertEquals(expectedName, currentCategory.getName());
        Assertions.assertEquals(expectedDescription, currentCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, currentCategory.isActive());
        Assertions.assertEquals(createdAt, currentCategory.getCreatedAt());
        Assertions.assertTrue(currentCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(currentCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A melhor categoria";
        final var expectedIsActive = true;

        final var newCategory = Category.newCategory(expectedName, expectedDescription, false);

        Assertions.assertDoesNotThrow(() -> newCategory.validate(new ThrowValidationHandler()));

        final Instant createdAt = newCategory.getCreatedAt();
        final Instant updatedAt = newCategory.getUpdatedAt();

        Assertions.assertFalse(newCategory.isActive());
        Assertions.assertNotNull(newCategory.getDeletedAt());

        final var currentCategory = newCategory.activate();

        Assertions.assertDoesNotThrow(() -> currentCategory.validate(new ThrowValidationHandler()));

        Assertions.assertEquals(currentCategory.getId(), newCategory.getId());
        Assertions.assertEquals(expectedName, currentCategory.getName());
        Assertions.assertEquals(expectedDescription, currentCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, currentCategory.isActive());
        Assertions.assertEquals(createdAt, currentCategory.getCreatedAt());
        Assertions.assertTrue(currentCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(currentCategory.getDeletedAt());
    }
}
