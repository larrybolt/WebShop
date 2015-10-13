<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>

    <form method="post" action="Controller?action=deleteProduct&id=${product.id }" novalidate="novalidate">
        <p>
        	Are you sure you want to delete <strong><c:out value="${product.name}"/></strong>?
        </p>
        <p>
        	<img src="${product.imgUrl}" alt="" style="max-width: 50px">
        </p>
        <p><input type="submit" id="confirmDelete" value="Yes, delete!"></p>
        
    </form>
</main>
<jsp:include page="../footer.jspf"/>