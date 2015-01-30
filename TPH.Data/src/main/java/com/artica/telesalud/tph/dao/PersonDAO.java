package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.model.person.Person;

public interface PersonDAO {
	
	public List<Person> getAll();
	public Person insert(Person person);
	public Person update(Person person);
	public Person newBlankPerson();
	public Person delete(Person person);
	public Person findPerson(Integer personId) throws TelesaludException;
	public List<Person> getPersons(String criterio, Integer limit, Integer offset);
	public Integer getTotalPersons();
	public Person findPersonByRegister(String registro) throws TelesaludException;
}
