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
							<th>选择</th>
							<th>标题</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${ci }" var="c" varStatus="vs">
							<tr>
								<td align="center">
									<input type="checkbox" name="ids" value="${c.comid }">
								</td>
								<td align="center">${c.title }</td>
								<td align="center">
										<a href="${pageContext.request.contextPath }/servlet/ComIntroServlet?operation=showDetailOne&comid=${c.comid}">查看详情</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>