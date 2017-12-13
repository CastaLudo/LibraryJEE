/**
 * @param id
 * @returns
 */
function getSubscriberDetails(id) {
	var subscriber = returnData('/libraryJEE/subscriberDetails?id='+id);
	var borrows = returnData('/libraryJEE/borrowsInSubscriber?id='+id);
	displayInformations(subscriber, borrows);
}


/**
 * @param subscriber
 * @param borrows
 * @returns
 */
function displayInformations(subscriber, borrows) {
	if (subscriber.length != 0) {
		$('#subscriberFirstName').val(subscriber.subscriberFirstName);
		$('#subscriberLastName').val(subscriber.subscriberLastName);
		$('#subscriberId').val(subscriber.subscriberId);
	}
	if (borrows.length != 0) {
		select = document.getElementById('booksBox');
		removeOptions(select);
		document.getElementById("booksBox").style.visibility="visible";
		document.getElementById('booksBox').size="15";
		for (var i = 0; i < borrows.length; i++) {
			var opt = document.createElement('option');
			opt.value = borrows[i].isbn;
			opt.innerHTML = borrows[i].title +" "+ borrows[i].subtitle + " ISBN : " + borrows[i].isbn;
			select.appendChild(opt);
		}
	}
	else document.getElementById("booksBox").style.visibility="hidden"; 
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