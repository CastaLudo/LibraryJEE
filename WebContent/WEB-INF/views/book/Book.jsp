<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LM 1.5 - Books</title>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>
	<h4>Livres</h4>
	<br />
	<form action="searchBookByName" method="get">
	<input name="searchBook" value="" type="text" size="40"><span></span><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	<div name="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
					<th>Liste des livres/ Recherche </th>
				<tr>
					<form name="selectBook">
					<td><select name="bookSelected" size="6"
						style="border-color: orange;">
							<c:forEach var="book" items="${books}">
								<option name="bookId" value="${book.getIsbn()}"
								 onclick="getBookDetails(${book.getIsbn()});">
									<b>${book.title} ${book.subtitle} </b>de ${book.author }
								</option>
							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
		</div>
		<div class="centerDiv">
		
			<jsp:include page="innerBookDetails.jsp"></jsp:include>
		</div>
		
		<div class="rightDiv"></div>
		</div>

		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" 
                  src="<c:url value="/js/book.js"/>"></script>
</body>
</html>