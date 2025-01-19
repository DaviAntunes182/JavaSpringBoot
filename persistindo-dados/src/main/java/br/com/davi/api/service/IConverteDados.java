package br.com.davi.api.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
