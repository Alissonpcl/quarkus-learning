package io.alissonlima.com.quarkus.jdbc;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.inject.Inject;

@QuarkusMain
public class AppMain implements QuarkusApplication {

    @Inject
    PgPool client;


    @Override
    public int run(String... args) throws Exception {
        simplesList();

        queryWithIn();

        return 0;
    }

    private void queryWithIn() {
        Uni<RowSet<Row>> rowSet = client
                .preparedQuery("SELECT i_clinica, nome FROM clinicas WHERE i_clinica = ANY ($1) AND NOME IS NOT NULL ORDER BY nome ASC")
                .execute(Tuple.tuple().addArrayOfInteger(new Integer[]{18021, 17883}));

        Multi<Clinica> clinicas = rowSet
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Clinica::from);

        clinicas.subscribe().with(clinica -> System.out.println(clinica.id + " - " + clinica.nome));
    }

    private void simplesList() {
        Uni<RowSet<Row>> rowSet = client.query("SELECT i_clinica, nome FROM clinicas WHERE NOME IS NOT NULL ORDER BY nome ASC LIMIT 2").execute();

        Multi<Clinica> clinicas = rowSet
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Clinica::from);

        clinicas.subscribe().with(clinica -> System.out.println(clinica.id + " - " + clinica.nome));
    }
}