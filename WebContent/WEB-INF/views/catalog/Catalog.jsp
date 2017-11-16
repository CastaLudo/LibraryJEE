<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LM 1.5 - Catalogs</title>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>
	<h4>Catalogues</h4>
	<br />
	<form action="searchCatalogByName" method="get">
	<input name="searchCatalog" value="" type="text" size="40"><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	<div class="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
				<th>Liste des catalogues disponibles
				</th>
				<tr>
					<form name="selectCatalog">
					<td><select class="leftList" name="catalogSelected" size="6" id="catalogs">
							<c:forEach var="catalog" items="${catalogs}">
								<option name="catalogId" onclick="getBooksList(${catalog.getId()});" value="${catalog.getId()}">${catalog.getCatalogName()}</option>

							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
			<input type="button" value="Supprimer le catalogue" onclick="deleteCatalog();" />
			
			<div id="catalogToDelete" value="" style="color: #FF0000;">${errorMessage}</div>
		</div>
		<div class="centerDiv">
			<form action="addCatalog" method="get">
				
				<input name="catalogToAdd" value="" type="text" style="width:30em;">
				<input type="submit" value="Ajouter Catalogue">
			
			</form>
			
			<jsp:include page="innerCatalogBooks.jsp"></jsp:include>
		
		</div>
		<div class="rightDiv"></div>
		</div>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" 
                  src="<c:url value="/js/catalog.js"/>"></script>
<footer style="padding-bottom: 0;">Tested by Lu</footer>
</body>
</html>