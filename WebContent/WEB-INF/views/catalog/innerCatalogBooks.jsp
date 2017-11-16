<br />
<table>
	<tr>

		<label for="catalog" name="catalogName"></label>
		<br />
	</tr>
	<tr>
		<td style="visibility:hidden;"><select id="booksBox" style="border-color: orange;"
			width=250px; >
				<c:forEach var="book" items="${books}">
					<option value="${book.getIsbn()}"></option>
				</c:forEach>
		</select></td>

	</tr>
</table>
