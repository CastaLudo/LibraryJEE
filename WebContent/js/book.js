/**
 * 
 */

function getBookDetails(isbn) {
	var book = returnData('/libraryJEE/bookDetails?id='+isbn);
	var copies = returnCopies('/libraryJEE/nbCopies?id='+isbn);
	viewBookDetails(book, copies);
}

function returnCopies(url) {
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

function viewBookDetails(resultBook, copies) {
	if (resultBook.length != 0) {
		$('#isbn').attr({value : resultBook.isbn});
		$('#title').val(resultBook.title);
		$('#subtitle').val(resultBook.subtitle);
		$('#author').val(resultBook.author.authorFirstName + " " + resultBook.author.authorLastName);
		$('#catalog').val(resultBook.catalog.catalogName);
		$('#numberOfCopies').val(copies.length);
	}
	if (copies.length != 0) {
		select = document.getElementById('copiesBox');
		document.getElementById("copiesList").style.visibility="visible";
		select.size="5";
		removeOptions(select);
		for (var i = 0; i < copies.length; i++) {
			var opt = document.createElement('option');
			opt.value = copies[i].id;
			opt.innerHTML = "exemplaire n°" + copies[i].id + ", statut disponible : " + copies[i].available + ", en réparation : " + copies[i].underRepair; 
			select.appendChild(opt);
		}
	}
	else {
		select = document.getElementById('copiesBox');
		removeOptions(select);
		select.size="0";
		document.getElementById("copiesList").style.visibility="hidden";
	}
}

function removeOptions(selectbox) {
	if (selectbox.options.length > 0) {
		var j;
		for(j = selectbox.options.length - 1 ; j >= 0 ; j--) 
		{
			selectbox.remove(j);
		}
	}
}