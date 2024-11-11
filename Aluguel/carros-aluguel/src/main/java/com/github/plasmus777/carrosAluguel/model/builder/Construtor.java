package com.github.plasmus777.carrosAluguel.model.builder;

public interface Construtor<T> {
    void novo();
    T construir();
}
