/**
 * 
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

function getSelectedOption(sel) {
    var opt;
    var len = sel.length;
    for ( var i = 0; i < len; i++ ) {
        opt = sel.option[i];
        if ( opt.selected === true ) {
            break;
        }
    }
    return opt;
}

function getBooksList(idCatalog) {
	var X = returnData('/libraryJEE/booksInCatalog?id='+idCatalog);
	viewBooksFromCatalog(X);
}

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

/**
 * @param selectbox
 * @returns clear the book list in innerCatalogBooks.jsp
 */
function removeOptions(selectbox) {
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}


/**
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