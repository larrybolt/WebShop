package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private PersonType personType;

	/*
	 * Returns existing person
	 */
	public Person(int id, String email, String password, String firstName, String lastName, String salt ) {
		setId(id);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setSalt(salt);
	}
	/*
	 * Returns new person, also generates salt and hashes password
	 */
	public Person(String email, String password, String firstName, String lastName, PersonType personType) {
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
		setPersonType(personType);
		// generate a new salt
		setSalt(this.generateSalt());
		// hash password for user
		if (password == null || password.length() < 4)
			throw new IllegalArgumentException("Please specify a 4 character password");
		setPassword(this.hashPassword(password));
	}
	
	
	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("Please enter your email");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return checkPassword(password);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		if(password.length()<4){
			throw new IllegalArgumentException("too short password, min 4 characters");
		}
		this.password = password;
	}
	
	public String hashPassword(String password){
		return hashPassword(password, this.getSalt());
	}
	public boolean checkPassword(String password){
		return hashPassword(password).equals(this.getPassword());
	}
	public String hashPassword(String password, String salt){
		// hash password using salt using hash(hash(salt)+hash(password)+"42")
		return Person.hashString(
				Person.hashString(salt)+
				Person.hashString(password)+
				"42" 
				/* ideally this comes from configurations or environment values
				 * this was an attacker needs access to:
				 * - database salt
				 * - config/env salt
				 * - application logic
				 * to setup a rainbow table.
				 * Even better would be to use Bcrypt,
				 * because slower -> slower for attacker to setup rainbow table/brute force
				 */
		);
	}
	public static String hashString(String input){
		MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(input.getBytes("UTF-8"));
			byte[] digest = crypt.digest();
			return new BigInteger(1, digest).toString(16);
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	protected String generateSalt(){
		return Person.hashString(new Integer(new Random().nextInt(10000)).toString());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public PersonType getPersonType(){
		return this.getPersonType();
	}
	public void setPersonType(PersonType role){
		this.personType = role;
	}
}
