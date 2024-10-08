// 즐겨찾기 추가/해제 기능
function toggleFavorite() {
    const favoriteBtn = document.getElementById('favoriteBtn');
    const popupMessage = document.getElementById('popupMessage');
    favoriteBtn.classList.toggle('active'); // 클릭 시 active 클래스를 추가/제거

    if (favoriteBtn.classList.contains('active')) {
        popupMessage.textContent = '즐겨찾기에 추가되었습니다!';
    } else {
        popupMessage.textContent = '즐겨찾기에서 제거되었습니다!';
    }

    openPopup(); // 팝업창 열기
}

// 팝업창 열기
function openPopup() {
    const popup = document.getElementById('favoritePopup');
    popup.classList.add('active'); // 팝업 활성화

    // 2초 후에 팝업 자동 닫기
    setTimeout(closePopup, 2000);
}

// 팝업창 닫기
function closePopup() {
    const popup = document.getElementById('favoritePopup');
    popup.classList.remove('active'); // 팝업 비활성화
}


// 뒤로가기 버튼 클릭 시 원하는 URL로 이동
function goBack() {
    window.location.href = "PlaceListService";
}