<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
        <p><label for="ID">ID</label><input type="text" id="ID" name="ID"
         required value=""> </p>
        <p><label for="description">Description</label><input type="text" id="description" name="description"
         required value=""> </p>
        <p><label for="price">Price</label><input type="number" id="price" name="price" required value=""></p>
        
        <p><input type="submit" id="add" value="add"></p>
        
    </form>

</body>
</html>