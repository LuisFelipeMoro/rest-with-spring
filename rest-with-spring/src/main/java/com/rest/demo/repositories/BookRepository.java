package com.rest.demo.repositories;

import com.rest.demo.model.Book;
import com.rest.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
