package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;


@Service
public class PersonDAO implements IPersonDAO {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> search(String name) {
        return personRepository.findByNameContaining(name);
    }

    @Override
    public Optional<Person> findOne(Integer id) {
		return  personRepository.findById(id);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Integer id) {
    	personRepository.deleteById(id);
    }


}