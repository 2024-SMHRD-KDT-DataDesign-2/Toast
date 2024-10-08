window.onload = function() {
	// 회원가입 실패 시 알림창 띄우기
	var joinStatusElement = document.getElementById('join-status');
	if (joinStatusElement) {
		var joinFailed = joinStatusElement.getAttribute('data-join-failed');
		if (joinFailed === 'true') {
			alert("회원가입 실패! 다시 시도해주세요.");
		}
	}

	// 로그인 실패 시 알림창 띄우기
	var loginStatusElement = document.getElementById('login-status');
	if (loginStatusElement) {
		var loginFailed = loginStatusElement.getAttribute('data-login-failed');
		if (loginFailed === 'true') {
			alert("로그인 실패! 다시 시도해주세요.");
		}
	}

	// 회원 정보 업데이트 실패시 알림창 띄우기
	var updateStatusElement = document.getElementById('update-status');
	if (updateStatusElement) {
		var UpdateFailed = updateStatusElement.getAttribute('data-update-failed');
		console.log("UpdateFailed 값:", UpdateFailed); // 값 확인

		if (UpdateFailed && UpdateFailed === 'true') {
			console.log("회원 정보 수정 실패! 다시 시도해주세요.");
			alert("회원 정보 수정 실패! 다시 시도해주세요.");
		}
	}
	
};
