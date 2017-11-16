<table class="details" name="idAuthor" id="idAuthor" value="">
	<tr>
		<td><label>First Name</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="subscriberFirstName"
			name="subscriberFirstName" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Last Name</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="subscriberLastName"
			name="subscriberLastName" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Subscription n°</label></td>
		<td class="fieldsToFill"><input  type="text" id="subscriberId"
			name="subscriberId" value=""></td>
	</tr>
	
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td>
		<label>Current Borrowed Copies</label>
		</td>
	</tr>
	<tr><td></td>
		<td style=""><select id="booksBox" style="border-color: orange;" >
				<c:forEach var="book" items="${books}">
					<option value="${book.getIsbn()}"></option>
				</c:forEach>
		</select></td>

	</tr>
</table>