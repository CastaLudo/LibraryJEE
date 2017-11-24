<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LM 1.5 - Subscriber</title>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>
	<h4>Abonnés</h4>
	<br />
	<form action="searchSubscriberByName" method="get">
	<input name="searchSubscriber" value="" type="text" size="40"><span></span><input type="submit" value="Recherche">
	</form>
	<br /> <br />
	test
	<div name="container"
		style="width: 100%; display: flex; flex-direction: row;">
		<div class="leftDiv">
			<table>
					<th>Liste des Abonnés/ Recherche </th>
				<tr>
					<form name="selectSubscriber">
					<td><select name="subscriberSelected" size="6"
						style="border-color: orange;">
							<c:forEach var="subscriber" items="${subscribers}">
								<option name="subscriberId" value="${subscriber.getSubscriberId()}"
								 onclick="getSubscriberDetails(${subscriber.getSubscriberId()});">
									<b>${subscriber.getSubscriberFirstName()} ${subscriber.getSubscriberLastName()} </b>
								</option>
							</c:forEach>
					</select></td>
					</form>
				</tr>
			</table>
		</div>
		<div class="centerDiv">
			<jsp:include page="subscriberDetails.jsp"></jsp:include>
		</div>
		
		<div class="rightDiv"></div>
		</div>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript" 
                  src="<c:url value="/js/subscriber.js"/>"></script>
</body>
</html>