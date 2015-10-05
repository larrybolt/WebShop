<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jspf"/>
<main>

	<c:if test="${errorMsg }">
	<div class="alert-danger">
	<c:forEach var="errorMsg" items="${errorMsg}">
		<ul>
			<li>${errorMsg}</li>
		</ul>
		</c:forEach>
	</div>
	</c:if>

    <form method="post" action="Controller?action=add" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
         required value=""> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
         required value=""> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" required value=""></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password"
         required> </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<jsp:include page="footer.jspf"/>