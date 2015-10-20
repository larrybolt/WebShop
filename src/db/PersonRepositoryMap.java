package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;

public class PersonRepositoryMap implements PersonRepository {
	private Map<Integer, Person> persons = new HashMap<Integer, Person>();
	
	public PersonRepositoryMap () {
	}
	
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getId())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getId(), person);
	}
	
	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getId(), person);
	}
	
	public void delete(int personId){
		persons.remove(personId);
	}

	@Override
	public Person get(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int generateNewId() {
		return this.persons.size()+1;
	}
}
