document.addEventListener("DOMContentLoaded", function() {
	// 이미지 선택 시 폼 자동 제출 및 미리보기 설정
	document.getElementById("profileImageInput").addEventListener("change", function(event) {
		const file = event.target.files[0];
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById("imagePreview").src = e.target.result;
			}
			reader.readAsDataURL(file);
		} else {
			// 파일이 없을 경우 기본 이미지를 설정
			document.getElementById("imagePreview").src = "images/default_mem_img.png";
		}
	});

	// 비밀번호 수정 버튼 클릭 이벤트
	document.getElementById('changePassword').addEventListener('click', function() {
		openModal('passwordModal');
	});

	// 닉네임 수정 버튼 클릭 이벤트
	document.getElementById('changeNickname').addEventListener('click', function() {
		openModal('nicknameModal');
	});
});

$(document).ready(function() {
	// 페이지 로드 시 AJAX로 컨트롤러에 요청
	$.ajax({
		url: 'MyControllerService',
		method: 'GET',
		success: function(response) {
			$('#controllerData').text(response); // 받은 데이터를 HTML에 표시
		},
		error: function() {
			$('#controllerData').text('데이터를 불러오지 못했습니다.');
		}
	});
});

// 모달 열기 및 닫기 함수
function openModal(modalId) {
	document.getElementById(modalId).style.display = 'flex';
}

function closeModal(modalId) {
	document.getElementById(modalId).style.display = 'none';
}

function openNotifications() {
	const notificationIcon = document.getElementById("notificationIcon");
	notificationIcon.style.color = "red"; // 클릭하면 색깔 변경 (빨간색)
	window.location.href = 'http://127.0.0.1:5500/%ED%86%A0%EC%8A%A4%ED%8A%B8/%EC%95%8C%EB%A6%BC%20%EC%84%BC%ED%84%B0.html'; // 원하는 URL로 이동
}

const profileImageInput = document.getElementById('profileImageInput');
const imagePreview = document.getElementById('imagePreview');

profileImageInput.addEventListener('change', function(event) {
	const file = event.target.files[0];
	if (file) {
		const reader = new FileReader();
		reader.onload = function(e) {
			imagePreview.src = e.target.result;  // 미리보기 이미지 변경
			imagePreview.style.display = 'block';  // 이미지 표시
		}
		reader.readAsDataURL(file);  // 파일을 읽어 Data URL로 변환
	} else {
		imagePreview.src = "images/default_mem_img.png";  // 파일이 없을 경우 기본 이미지로 설정
	}
});

function goBack() {
	window.location.href = "main.jsp";
}