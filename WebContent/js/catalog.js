/** Query the booklist for the selected Catalog and Ask displaying the books list in innerCatalogBooks.jsp
 * @param idCatalog
 * @returns 
 */
function getBooksList(idCatalog) {
	var X = returnData('/libraryJEE/booksInCatalog?id='+idCatalog);
	viewBooksFromCatalog(X);
}

/** AJAX Query Servlet  
 * @param url
 * @returns resultat as Json format
 */
function returnData(url) {
	$.get({
		url:url,
		async: false
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

/** Display the books parsed as Json format in the booksBox @innerCatalogBooks.jsp
 * @param resultat
 * @returns
 */
function viewBooksFromCatalog(resultat) {
	if (resultat.length != 0) {
		document.getElementById("booksInCatalog").style.visibility="hidden";
		document.getElementById('booksList').style.visibility="visible";
		select = document.getElementById('booksBox');
		select.style.visibility="visible";
		document.getElementById('booksBox').size="15";
		removeOptions(select);

		$('label[name=catalogName]').text("Book in "+resultat[0].catalog.catalogName)
		
		for (var i = 0; i < resultat.length; i++) {
			var opt = document.createElement('option');
			opt.value = resultat[i].isbn;
			opt.innerHTML = resultat[i].title +" "+ resultat[i].subtitle + " by " + resultat[i].author.authorFirstName + " " 
			+ resultat[i].author.authorLastName;
			select.appendChild(opt);
		}
	}
	else {
		document.getElementById('booksList').style.visibility="hidden";
		document.getElementById("booksBox").style.visibility="hidden";
		document.getElementById("booksInCatalog").style.visibility="visible";
		document.getElementById("booksInCatalog").innerHTML = "Il n'y a pas de livre référencé dans ce catalogue";
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

/**Delete the selected Catalog and reload the Catalog's list
 * @returns
 */
function deleteCatalog() {
	var selectedCatalog = document.getElementById("catalogs");
	var valueSelected = selectedCatalog.options[selectedCatalog.selectedIndex];
	
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", "deleteCatalog?catalogToDelete=" + valueSelected.value, false); // true for asynchronous 
    xmlHttp.send(null);
	location.reload(true);
}