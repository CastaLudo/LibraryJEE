/**
 * 
 */

function getAuthorDetails(id) {
	var author = returnData('/libraryJEE/getAuthorDetails?id='+id);
	console.log("call js insert values");
	var books = returnBooks('/libraryJEE/getAuthorSBooks?id='+id);
	console.log("books : " + books);
	viewAuthorDetails(author, books);
}

function returnBooks(url) {
	$.get({
		url:url,
		async:false
	})
	.done(function(datas, status)
			{
		resultat = datas;
			})
			.fail(function(err)
					{
				resultat = err.status;
					});
	return resultat;
}

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

function removeOptions(selectbox) {
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function viewAuthorDetails(author, books) {
	if (author.length != 0) {
		console.log("insert author details in centerDiv");
		$('#idAuthor').attr({value : author.id});
		$('#firstNameAuthor').val(author.authorFirstName);
		$('#lastNameAuthor').val(author.authorLastName);
		$('#yearOfBirth').val(author.yearOfBirth);
		$('#yearOfDeath').val(author.yearOfDeath);
		console.log("nb books = " + books.length);
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