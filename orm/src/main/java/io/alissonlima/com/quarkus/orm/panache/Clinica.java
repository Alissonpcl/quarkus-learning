package io.alissonlima.com.quarkus.orm.panache;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinicas")
@NamedQuery(name = Clinica.CLINICAS_GET_BY_I_DATABASES, query = "from Clinica where iDatabases = ?1")
public class Clinica extends PanacheEntityBase {

    final static String CLINICAS_GET_BY_I_DATABASES = "CLINICAS_GET_BY_I_DATABASES";

    @Id
    @Column(name = "i_clinica")
    public Integer id;
    public String nome;

    @Column(name = "i_databases")
    public Integer iDatabases;

    public static List<Clinica> listAll(int limit) {

        return findAll()
                .range(0, limit)
                .list();
    }

    public static Clinica getByIDatabases(Integer iDatabases) {
        return find("#" + CLINICAS_GET_BY_I_DATABASES, iDatabases).firstResult();
    }

    public static PanacheQuery<Clinica> getAllPaginated() {
        return findAll()
                .page(Page.ofSize(10));

    }


}
