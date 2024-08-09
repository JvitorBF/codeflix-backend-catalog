package com.github.jvitorbf.adm.catalogo.infrastructure;

import com.github.jvitorbf.adm.catalogo.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(new UseCase().execute());
    }
}