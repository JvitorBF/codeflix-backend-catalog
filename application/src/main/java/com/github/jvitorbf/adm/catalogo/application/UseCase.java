package com.github.jvitorbf.adm.catalogo.application;

import com.github.jvitorbf.adm.catalogo.domain.category.Category;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class UseCase {

    public Category execute() {
        return Category.newCategory("Ação","Muita explosão", true);
    }
}