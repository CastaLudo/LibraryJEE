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
	<form action="searchBorrowsByTitle" method="get">
	<input name="searchTitle" value="" type="text" size="40">
	<br />
	<input name="searchSubscriber" value="" type="text" size="40">
	<span></span><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	<div name="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
					<th>Liste des Emprnts / Recherche </th>
				<tr>
					<form name="selectSubscriber">
					<td><select name="borrowSelected" size="6"
						style="border-color: orange;">
							<c:forEach var="borrow" items="${borrows}">
								<option name="copyId" value="${borrow.getId()}"
								 onclick="">
									<b>${borrow.getBook().getTitle()} Ex n°${borrow.getId()}</b>
								</option>
							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
		</div>
</body>
</html>