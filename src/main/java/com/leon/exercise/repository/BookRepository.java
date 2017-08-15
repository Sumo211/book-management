package com.leon.exercise.repository;

import com.leon.exercise.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
