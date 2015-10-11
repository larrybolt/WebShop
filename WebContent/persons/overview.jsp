<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>
<table>
<tr>
<th>E-mail</th>
<th>First Name</th>
<th>Last Name</th>
</tr>
<c:forEach var="person" items="${persons}">
<tr>
<td>${person.email }</td>
<td>${person.firstName }</td>
<td>${person.lastName }</td>
<td>
	<a href="?action=deletePerson&id=${person.id }">delete</a>
</td>
</tr>
</c:forEach>
<caption>Users Overview</caption>
</table>

</main>
<jsp:include page="../footer.jspf"/>