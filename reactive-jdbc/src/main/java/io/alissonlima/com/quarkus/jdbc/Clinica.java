package io.alissonlima.com.quarkus.jdbc;

import io.vertx.mutiny.sqlclient.Row;

public class Clinica {

    public Integer id;
    public String nome;

    public Clinica(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static Clinica from(Row row) {
        return new Clinica(row.getInteger("i_clinica"), row.getString("nome"));
    }
}
