# WebShop
## Configurations
Configurations are retrieved from `/WebContent/WEB-INF/config.xml`, an example is available at `/WebContent/WEB-INF/config.example.xml`

## TODO
- Write tests
- Alter Person to use hashes for authentication (changes in Person, PersonService and PersonRepository)
- Expand persons to admins and customers
- Use cookies/sessions to keep users logged in
- Escape html in input, perhaps using [apache.commons escapeHtml](https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/StringEscapeUtils.html#escapeHtml(java.lang.String))
