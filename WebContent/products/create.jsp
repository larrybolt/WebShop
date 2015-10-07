<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>

	<c:forEach var="errorMsg" items="${errorMsg}">
	<div class="alert-danger">
		<ul>
			<li>${errorMsg}</li>
		</ul>
	</div>
	</c:forEach>

    <form method="post" action="Controller?action=addProduct" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p>
        	<label for="name">Name</label>
        	<input type="text" id="name" name="name" required value="${product.name}">
        </p>
        <p>
        	<label for="price">Price</label>
        	<input type="number" id="price" name="price" required value="${product.price}">EUR
        </p>
        <p>
        	<label for="ImgUrl">Img URL</label>
        	<input type="url" id="ImgUrl" name="ImgUrl" value="${product.imgUrl}" required>
        </p>
        <p><input type="submit" id="addProduct" value="Save"></p>
        
    </form>
</main>
<jsp:include page="../footer.jspf"/>
