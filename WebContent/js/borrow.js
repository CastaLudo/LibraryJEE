/**
 * 
 */

function getBorrowDetails(id) {
	var copy = returnData('/libraryJEE/copyDetails?id='+id);
	var borrower = returnData('/librayJEE/subscriberDetails?id='+id);
	displayBorrowDetails(copy, borrower);
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

function displayBorrowDetails(copy, subscriber) {
	
}