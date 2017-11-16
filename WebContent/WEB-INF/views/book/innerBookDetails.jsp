<table class="details">
	<tr>
		<td><label>ISBN</label></td>
		<td class="fieldsToFill"><input class="toFill" id="isbn"
			type="text" name="isbn" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Title</label></td>
		<td class="fieldsToFill"><input class="toFill" id="title"
			type="text" name="title" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Subtitle</label></td>
		<td class="fieldsToFill"><input class="toFill" id="subtitle"
			type="text" name="subtitle" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Author</label></td>
		<td class="fieldsToFill"><input class="toFill" id="author"
			type="text" name="author" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>
	<tr>
		<td><label>Catalog</label></td>
		<td class="fieldsToFill"><input class="toFill" id="catalog"
			type="text" name="catalog" value=""></td>
	</tr>
	<tr>
		<td><p></p></td>
	</tr>

	<tr>
		<td><label>Number of copies</label></td>
		<td><input type="text" name="numberOfCopies" id="numberOfCopies"
			value=""></td>
	</tr>
</table>

<br />
<div id="copiesList" style="visibility: hidden;">
	<table>
		<tr>

			<label for="copy"></label>
			<br />
		</tr>
		<tr>
			<td><select id="copiesBox"
				style="border-color: orange;" width=250px;>
					<c:forEach var="copy" items="${copies}">
						<option value="${copy.getId()}"></option>
					</c:forEach>
			</select></td>

		</tr>
	</table>
</div>