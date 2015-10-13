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
		
    <form method="post" action="Controller?action=login" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p>
        	<label for="email">email</label>
        	<input type="email" id="email" name="email" required value="${param['email']}">
        </p>
        <p>
        	<label for="password">password</label>
        	<input type="password" id="password" name="password" required value="${param['password']}">
        </p>
        <p><input type="submit" id="Login" value="Login"></p>
        
    </form>
</main>
<jsp:include page="../footer.jspf"/>
