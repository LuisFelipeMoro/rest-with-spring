package com.rest.demo.controllers;

import com.rest.demo.dto.BookDto;
import com.rest.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<BookDto> findAll(){
        return bookService.findAll();
    }
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookDto findById(
            @PathVariable(value = "id") Long id)
            throws Exception{

        return bookService.findById(id);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookDto create(
            @RequestBody BookDto book) throws Exception {

        return bookService.create(book);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookDto update(
            @RequestBody BookDto book) throws Exception {

        return bookService.update(book);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "id") Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
