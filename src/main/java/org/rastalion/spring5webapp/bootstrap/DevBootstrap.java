package org.rastalion.spring5webapp.bootstrap;

import org.rastalion.spring5webapp.model.Author;
import org.rastalion.spring5webapp.model.Book;
import org.rastalion.spring5webapp.model.Publisher;
import org.rastalion.spring5webapp.repositories.AuthorRepository;
import org.rastalion.spring5webapp.repositories.BookRepository;
import org.rastalion.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component // Makes this a Spring Bean
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // This will get autowired with the correct implementation, thanks Spring
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Publisher publisher = new Publisher("Foo Bar");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }

    /*
    Because this class is managed as a Spring Bean in the Spring context it's going to
    get wired up for us. And then when that event gets thrown[The context ready event]
    our method initData get's executed and will populate our h2 db with the data provided in that method.

    This is all possible because we implement the interface: ApplicationListener<ContextRefreshedEvent>
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
