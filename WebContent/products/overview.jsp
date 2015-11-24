<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jspf"/>
<main>
<table>
<tr>
<th></th>
<th>Name</th>
<th>
	Price
	(<a href="?action=${param['action']}&order=price">Sorteer</a>)
</th>
<th></th>
</tr>
<c:forEach var="product" items="${products}">
<tr>
<td><img src="${product.imgUrl}" alt="" style="max-width: 100px;"/></td>
<td><c:out value="${product.name}"/></td>
<td>${product.price }</td>
<td>
	<a href="?action=deleteProduct&id=${product.id}">delete</a>
	<a href="?action=editProduct&id=${product.id }">edit</a>
</td>
</tr>
</c:forEach>
<caption>Product Overview</caption>
</table>
<p><a href="?action=addProduct">Add new Product</a></p>

</main>
<jsp:include page="../footer.jspf"/>