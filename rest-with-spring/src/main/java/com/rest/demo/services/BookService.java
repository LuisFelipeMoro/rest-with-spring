package com.rest.demo.services;

import com.rest.demo.controllers.BookController;
import com.rest.demo.dto.BookDto;
import com.rest.demo.exceptions.RequiredObjectIsNullException;
import com.rest.demo.exceptions.ResourceNotFoundException;
import com.rest.demo.mapper.GenericMapper;
import com.rest.demo.model.Book;
import com.rest.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    BookRepository bookRepository;

    public List<BookDto> findAll(){

        logger.info("Finding all books");

        var books = GenericMapper.parseListObjects(bookRepository.findAll(), BookDto.class);

        books
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }
    public BookDto findById(Long id){

        if(id == null) throw new RequiredObjectIsNullException();
        logger.info("Finding a person");

        var entity = bookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
        BookDto dto = GenericMapper.parseObject(entity, BookDto.class);
        try {
            dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public BookDto create(BookDto book) throws Exception {

        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a person");
        var entity = GenericMapper.parseObject(book, Book.class);
        BookDto dto = GenericMapper.parseObject(bookRepository.save(entity), BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public BookDto update(BookDto book) throws Exception {

        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("Updating a person");

        Book entity = bookRepository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));


        BookDto dto = GenericMapper.parseObject(bookRepository.save(entity), BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");

        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        bookRepository.delete(entity);

    }
}
