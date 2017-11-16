<table class="details" name="idAuthor" id="idAuthor" value="">
	<tr>
		<td><label>First Name</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="firstNameAuthor"
			name="firstNameAuthor" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Last Name</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="lastNameAuthor"
			name="lastNameAuthor" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Born in</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="yearOfBirth"
			name="yearOfBirth" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Dead in</label></td>
		<td class="fieldsToFill"><input class="toFill" type="text" id="yearOfDeath"
			name="yearOfDeath" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td>
		<label>Books written this author</label>
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