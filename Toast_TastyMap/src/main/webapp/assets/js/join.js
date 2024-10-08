const imageInput = document.getElementById('imageInput');
const imagePreview = document.getElementById('imagePreview');

imageInput.addEventListener('change', function(event) {
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
