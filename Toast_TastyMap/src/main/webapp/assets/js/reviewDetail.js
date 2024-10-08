function goBack() {
	/*var place_idx = <%= placeIdx %>;*/
	/*var sessionPlaceIdx = '<%= placeIdx %>';
    if (sessionPlaceIdx) {
        window.location.href = "PlaceDetailService?place_idx=" + sessionPlaceIdx;
    } else {
        alert('Place index not found!');
    }*/
	window.history.back();
}


function toggleReplyForm(commentId) {
    var replyForm = document.getElementById('reply-form-' + commentId);
    if (replyForm.style.display === "none") {
        replyForm.style.display = "block";
    } else {
        replyForm.style.display = "none";
    }
}