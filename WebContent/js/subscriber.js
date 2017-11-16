/**
 * 
 */
function getSubscriberDetails(id) {
	var subscriber = returnDetails('/libraryJEE/subscriberDetails?id='+id);
	var borrows = returnBorrows('/libraryJEE/borrowsInSubscriber?id='+id);
	console.log("les emprunts");
	console.log(borrows);
	displayInformations(subscriber, borrows);
}

function removeOptions(selectbox) {
	var i;
	for(i = selectbox.options.length - 1 ; i >= 0 ; i--) 
	{
		selectbox.remove(i);
	}
}

function displayInformations(subscriber, borrows) {
	if (subscriber.length != 0) {
		$('#subscriberFirstName').val(subscriber.subscriberFirstName);
		$('#subscriberLastName').val(subscriber.subscriberLastName);
		$('#subscriberId').val(subscriber.subscriberId);
	}
	if (borrows.length != 0) {
		select = document.getElementById('booksBox');
		removeOptions(select);
//		$('#booksBox').show();
		document.getElementById("booksBox").style.visibility="visible";
//		$('#booksBox').css('size', 15);
		document.getElementById('booksBox').size="15";
		console.log("traitement des emprunts");
		console.log(borrows);
		for (var i = 0; i < borrows.length; i++) {
			var opt = document.createElement('option');
			opt.value = borrows[i].isbn;
			opt.innerHTML = borrows[i].title +" "+ borrows[i].subtitle + " ISBN : " + borrows[i].isbn;
			select.appendChild(opt);
		}
		//for (var b in borrows) {
			
//			var opt = document.createElement('option');
//			opt.value = b.isbn;
//			opt.innerHTML = b.title +" "+ b.subtitle + " ISBN : " + b.isbn + 
//			" by " + b.author.authorFirstName + " " + b.author.authorLastName;
//			select.appendChild(opt);
//		}
	}
	else document.getElementById("booksBox").style.visibility="hidden"; 
//		$('#booksBox').hide();
}

function returnBorrows(url) {
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

function returnDetails(url) {
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
