package org.rastalion.spring5webapp.repositories;

import org.rastalion.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    /*
    Go and have a look at: localhost:8080/h2-console

    JDBC url should be: jdbc:h2:mem:testdb
     */

}
