<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LM 1.5 - Authors</title>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>
	<h4>Auteurs</h4>
	<br />
	<form action="searchAuthorByName" method="get">
	<input name="searchAuthor" value="" type="text" size="40"><span></span><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	<div class="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
				<th>Liste des auteurs / Recherche</th>
				<tr>
					<form name="selectAuthor"
						action="<c:url value="/booksInAuthor?id=${catalogId}"/>"
						method="POST">
					<td><select class="leftList" name="authorSelected" size="6">
							<c:forEach var="author" items="${authors}">
								<option name="authorId" onclick="getAuthorDetails(${author.getAuthorId()});" 
								value="${author.getAuthorId()}">
								${author.toString()} 

								</option>

							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
		</div>
		<div class="centerDiv">
			<jsp:include page="authorDetails.jsp"></jsp:include>
		</div>
		
		<div class="rightDiv"></div>
		</div>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" 
                  src="<c:url value="/js/author.js"/>"></script>

</body>
</html>