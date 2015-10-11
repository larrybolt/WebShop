package domain;
import java.io.InputStream;
import java.util.List;

import db.PersonRepository;
import db.PersonRepositoryDB;
import db.PersonRepositoryMap;

public class PersonService {
	private PersonRepository personRepository;

	public PersonService(){
		this.personRepository = new PersonRepositoryMap();
	}
	
	public PersonService(InputStream resourceAsStream) {
		this.personRepository = new PersonRepositoryDB(resourceAsStream);
	}
	
	public Person getPerson(int personId) {
		return getPersonRepository().get(personId);
	}
	public Person getPerson(String id) {
		return getPerson(Integer.parseInt(id));
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		deletePerson(Integer.parseInt(id));
	}
	public void deletePerson(int id) {
		getPersonRepository().delete(id);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}
}
