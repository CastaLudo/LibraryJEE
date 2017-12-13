/** Query the author details and his books list for the selected id and Ask displaying its @authorDetails.jsp
 * @param id
 * @returns
 */
function getAuthorDetails(id) {
	var author = returnData('/libraryJEE/getAuthorDetails?id='+id);
	var books = returnData('/libraryJEE/getAuthorSBooks?id='+id);
	viewAuthorDetails(author, books);
}

/** AJAX Query Servlet  
 * @param url
 * @returns resultat as Json format
 */
function returnData(url) {
	$.get({
		url:url,
		async:false
	})
	.done(function(retourData, status)
			{
		resultat = retourData;
			})
			.fail(function(err)
					{
				resultat = err.status;
					});
	return resultat;
}

/** Display author details and the books written by him/her @authorDetails.jsp
 * @param author
 * @param books
 * @returns
 */
function viewAuthorDetails(author, books) {
	if (author.length != 0) {
		$('#idAuthor').attr({value : author.id});
		$('#firstNameAuthor').val(author.authorFirstName);
		$('#lastNameAuthor').val(author.authorLastName);
		$('#yearOfBirth').val(author.yearOfBirth);
		$('#yearOfDeath').val(author.yearOfDeath);
		}
	if (books.length != 0) {
		document.getElementById("booksBox").style.visibility="visible";
		select = document.getElementById('booksBox');
		document.getElementById('booksBox').size="15";
		removeOptions(select);

		for (var i = 0; i < books.length; i++) {
			var opt = document.createElement('option');
			opt.value = books[i].isbn;
			opt.innerHTML = books[i].title +" "+ books[i].subtitle + " ISBN : " + books[i].isbn;
			select.appendChild(opt);
		}
	}
	else {
		document.getElementById("booksBox").style.visibility="hidden";
	}
}

/** Clear all option in 'select' tag
 * @param select
 * @returns 
 */
function removeOptions(select) {
    var i;
    for(i = select.options.length - 1 ; i >= 0 ; i--)
    {
    	select.remove(i);
    }
}