package db;
import java.util.List;
import domain.Person;

public interface PersonRepository {
		
		public Person get(int personId);
		public List<Person> getAll();
		public void add(Person person);
		public void update(Person person);
		public void delete(int id);
		public int generateNewId();
}