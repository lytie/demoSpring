package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Person;


public interface IPersonDAO {

    Iterable<Person> findAll();

    List<Person> search(String name);

    Optional<Person> findOne(Integer id);

    void save(Person person);

    void delete(Integer id);

}