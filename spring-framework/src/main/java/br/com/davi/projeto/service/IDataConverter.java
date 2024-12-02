package br.com.davi.projeto.service;

public interface IDataConverter {
   <T> T obterDados(String json, Class<T> classe);
}
