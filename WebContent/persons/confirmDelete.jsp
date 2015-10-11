<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>

    <form method="post" action="Controller?action=deletePerson&id=${person.id }" novalidate="novalidate">
        <p>
        	Are you sure you want to delete <strong>${person.firstName} ${person.lastName}</strong>?
        </p>
        <p><input type="submit" id="confirmDelete" value="Yes, delete!"></p>
        
    </form>
</main>
<jsp:include page="../footer.jspf"/>
