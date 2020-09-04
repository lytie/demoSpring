package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Person;


@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findByNameContaining(String name);

}