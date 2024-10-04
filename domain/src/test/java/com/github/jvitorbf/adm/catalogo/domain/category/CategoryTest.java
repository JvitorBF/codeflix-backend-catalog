package com.github.jvitorbf.adm.catalogo.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var currentCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertNotNull(currentCategory, "A categoria não deve ser nula");
        Assertions.assertNotNull(currentCategory.getId(), "O ID da categoria não deve ser nulo");
        Assertions.assertEquals(expectedName, currentCategory.getName(), "O nome da categoria deve ser igual ao esperado");
        Assertions.assertEquals(expectedDescription, currentCategory.getDescription(), "A descrição da categoria deve ser igual ao esperado");
        Assertions.assertEquals(expectedIsActive, currentCategory.isActive(), "O status ativo da categoria deve ser igual ao esperado");
        Assertions.assertNotNull(currentCategory.getCreatedAt(), "A data de criação não deve ser nula");
        Assertions.assertNotNull(currentCategory.getUpdatedAt(), "A data de atualização não deve ser nula");
        Assertions.assertNull(currentCategory.getDeletedAt(), "A data de exclusão deve ser nula");
    }
}
