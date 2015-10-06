<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>
<table>
<tr>
<th></th>
<th>Description</th>
<th>Price</th>
<th></th>
</tr>
<c:forEach var="product" items="${products}">
<tr>
<td><img src="${product.imgUrl}" alt="" style="max-width: 100px;"/></td>
<td>${product.description}</td>
<td>${product.price }</td>
<td>
	<a href="?action=deleteProduct&id=${product.id}">x</a>
	<a href="?action=editProduct&id=${product.id }">edit</a>
</td>
</tr>
</c:forEach>
<caption>Product Overview</caption>
</table>

</main>
<jsp:include page="footer.jspf"/>