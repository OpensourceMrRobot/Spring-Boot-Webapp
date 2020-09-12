package org.rastalion.spring5webapp.repositories;

import org.rastalion.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    /*
    Right click on CrudRepository
     */

}
