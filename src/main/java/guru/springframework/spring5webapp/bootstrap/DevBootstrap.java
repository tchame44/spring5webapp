package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepo;
	private BookRepository bookRepo;
	private PublisherRepository publisherRepo;
	
	public DevBootstrap(AuthorRepository authorRepo, BookRepository bookRepo,PublisherRepository publisherRepo) {
		super();
		this.authorRepo = authorRepo;
		this.bookRepo = bookRepo;
		this.publisherRepo = publisherRepo;
	}

	private void initData(){
		Publisher pub1 = new Publisher("TOTO", "Rue de toto");
		Publisher pub2 = new Publisher("TATA", "Rue de tata");
		
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "1234", pub1);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		publisherRepo.save(pub1);
		authorRepo.save(eric);
		bookRepo.save(ddd);
		
		
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("hJK", "23444", pub2);
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		
		publisherRepo.save(pub2);
		authorRepo.save(rod);
		bookRepo.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
		
	}

}
