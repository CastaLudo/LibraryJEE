<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LM 1.5 - Borrows</title>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>
<h4>Emprunts en cours</h4>
	<br />
	<form action="searchInBorrow" method="get">
	<select id="selectSearchType">
		<option value="searchBorrowByBookName">par titre de livre</option>
		<option value="searchBorrowxBySubscriberName">par nom d'abonné</option>
	</select>
	<br />
	<input name="searchSubscriber" value="" type="text" size="40">
	<span></span><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	<div name="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
					<th>Liste des Emprunts / Recherche </th>
				<tr>
					<form name="selectSubscriber">
					<td><select name="borrowSelected" size="6"
						style="border-color: orange;">
							<c:forEach var="borrow" items="${borrows}">
								<option name="copyId" value="${borrow.getId()}"
								 onclick="getBorrowDetails(${borrow.getId()})">
									<b>${borrow.getBook().getTitle()} - Exemplaire n°${borrow.getId()}</b>
								</option>
							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
		</div>
		<div name="centerDiv">
		<jsp:include page="borrowDetails.jsp"></jsp:include>
		</div>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" 
                  src="<c:url value="/js/borrow.js"/>"></script>
</body>
</html>