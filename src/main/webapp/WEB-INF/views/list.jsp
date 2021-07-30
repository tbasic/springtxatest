<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="resources/css/nstyle.css" />
<title>Insert title here</title>
</head>
<body>
<h3>list.jsp</h3>
<c:if test="${empty sessionScope.uid }">
	<a href="login">login</a>
</c:if>
<c:if test="${not empty sessionScope.uid }">
	<a href="logoutProc">logout</a>
</c:if>

  join


<hr />
<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th>히트</th>
		</tr>
		
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.bid }</td>
				<td>${dto.bname }</td>
				<td>
					<c:set value="${dto.bindent }" var="endindent" />
					<c:forEach begin="1" end="${dto.bindent }" var="cnt">
						&nbsp;
						<c:if test="${cnt eq endindent }">
							<img src="resources/img/reply.gif" />[re]
						</c:if>
					</c:forEach>
					<a href="content_view?bid=${dto.bid }">${dto.btitle }</a>
				</td>
				<td>${dto.bdate }</td>
				<td>${dto.bhit }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
</table>
TotCnt : ${totRowCnt }
<br />

<form id="form1" name="form1" action="list" method="post">
	<c:if test="${searchVo.totPage>1 }">
	<c:if test="${searchVo.page>1 }">
		<a href="list?page=1">[처음]</a>
		<a href="list?page=${searchVo.page-1 }">[이전]</a>
	</c:if>
	<c:forEach begin="${searchVo.pageStart }" end="${searchVo.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVo.page }">
				<span style="color: red; font-weight: bold;">${i } &nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${i }" style="text-decoration: none;">${i } &nbsp;</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${searchVo.totPage>searchVo.page }">
		<a href="list?page=${searchVo.page+1 }">[다음]</a>
		<a href="list?page=${searchVo.totPage }">[마지막]</a>
	</c:if>
	</c:if>
	<div>
	<c:choose>
	 <c:when test="${btitle }">
	 	<input type="checkbox" name="searchType" value="btitle" checked />
	 </c:when>
	 <c:otherwise>
	 	<input type="checkbox" name="searchType" value="btitle" />
	 </c:otherwise>
	</c:choose>
	<label>제목</label>
	
	<c:choose>
	 <c:when test="${bcontent }">
	 	<input type="checkbox" name="searchType" value="bcontent" checked />
	 </c:when>
	 <c:otherwise>
	 	<input type="checkbox" name="searchType" value="bcontent" />
	 </c:otherwise>
	</c:choose>
	<label>내용</label>
	
	<input type="text" name="sk" style="width: 150px;" maxlength="50" value="${searchKeyword }" />
	<input name="btn_search" type="submit" value="검색" />
	</div>
</form>
</body>
</html>