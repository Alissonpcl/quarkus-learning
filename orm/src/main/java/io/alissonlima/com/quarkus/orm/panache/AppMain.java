package io.alissonlima.com.quarkus.orm.panache;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

@QuarkusMain
public class AppMain implements QuarkusApplication {

    @Inject
    Logger log;

    @ActivateRequestContext
    @Override
    public int run(String... args) throws Exception {
        log.info("Iniciando aplicacao");
//        list10();
//        getByIDatabases();

        findPaginated();
        log.info("finalizando aplicacao");
        return 0;
    }

    private void findPaginated() {
        var allPaginated = Clinica.getAllPaginated();

        int count = 0 ;
        while (allPaginated.hasNextPage() && count < 3){
            System.out.printf("PAGE %d ----------\n", count);
            allPaginated.nextPage()
                    .stream()
                    .forEach(this::printClinica);
            count++;
        }
    }

    private void getByIDatabases() {
        Clinica clinica = Clinica.getByIDatabases(59823);
        printClinica(clinica);
    }

    private void list10() {
        var clinicas = Clinica.listAll(10);

        for (Clinica clinica : clinicas) {
            printClinica(clinica);
        }
    }

    private void printClinica(Clinica clinica) {
        log.infof("%d - %d - %1s", clinica.id, clinica.iDatabases, clinica.nome);
    }

}