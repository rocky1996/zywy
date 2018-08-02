<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
		<form action="${pageContext.request.contextPath }/servlet/ComIntroServlet?operation=query" method="post">
			<table align="center" border="0" width="85%">
				<input type="text" name="text"/>
				<input type="submit" value="go">
			</table>
		</form>
	</body>
</html>