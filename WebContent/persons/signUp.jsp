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
		

    <form method="post" action="Controller?action=add" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p>
        	<label for="firstName">First Name</label>
        	<input type="text" id="firstName" name="firstName" required value="${param['firstName']}">
        </p>
        <p>
        	<label for="lastName">Last Name</label>
        	<input type="text" id="lastName" name="lastName" required value="${param['lastName']}">
        </p>
        <p>
        	<label for="email">Email</label>
        	<input type="email" id="email" name="email" required value="${param['email']}">
        </p>
        <p>
        	<label for="password">Password</label>
        	<input type="password" id="password"  name="password" required>
        </p>
        <p>
        	<label for="woonplaats">Woonplaats</label>
        	<input type="text" id="woonplaats"  name="woonplaats" required>
        </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<jsp:include page="../footer.jspf"/>