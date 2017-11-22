/**
 * 
 */

function getBorrowDetails(id) {
	var copy = returnData('/libraryJEE/copyBorrowed?id='+id);
	var borrower = returnData('/libraryJEE/subscriberBorrowing?id='+id);
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

function displayBorrowDetails(copy, borrower) {
	$('#idCopy').val(copy.id);
	$('#copyDetails').val(copy.book.isbn + " : " + copy.book.title + " by " 
			+ copy.book.author.authorFirstName + " " + copy.book.author.authorLastName);
	$('#idBorrower').val(borrower.subscriberId);
	$('#borrowerDetails').val(borrower.subscriberFirstName + " " + borrower.subscriberLastName);
}