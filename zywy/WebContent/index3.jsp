<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
		<table align="center" border="0" width="60%">
			<tr>
				<td>
					<c:if test="${empty ci}">
						没有数据
					</c:if>
					<c:if test="${!empty ci}">
						<tr>
							<th>${ci.title }</th>
							<th>${ci.control }</th>
							<th>${ci.time }</th>
						</tr>
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>