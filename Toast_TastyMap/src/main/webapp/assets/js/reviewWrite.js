// Star rating functionality
const stars = document.querySelectorAll('.star');
const selectedRating = document.getElementById('selectedRating');  // Hidden input

stars.forEach(star => {
    star.addEventListener('click', function() {
        const starValue = this.getAttribute('data-value');
        
        // Update hidden input value with selected star value
        selectedRating.value = starValue;

        // Toggle star rating
        stars.forEach(s => {
            if (s.getAttribute('data-value') <= starValue) {
                s.classList.add('checked');
            } else {
                s.classList.remove('checked');
            }
        });
    });
});

// Function to trigger the file input for photo upload
/*function triggerFileInput() {
    document.getElementById('photoInput').click();
}*/

function triggerFileInput() {
    document.getElementById('photoInput').click();  // file input을 클릭하여 파일 선택 창을 염
}

// Function to preview the selected image
/*function previewImage(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
        const imagePreview = document.getElementById('imagePreview');
        imagePreview.src = e.target.result;
        imagePreview.style.display = 'block'; // Show image preview
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}*/

function previewImage(event) {
    const fileInput = event.target;  // 선택한 파일의 input element
    const files = fileInput.files;   // 선택한 파일 리스트

    // 파일이 존재하면 첫 번째 파일을 미리보기로 설정
    if (files.length > 0) {
        const reader = new FileReader();  // FileReader 객체 생성
        reader.onload = function(e) {
            const imagePreview = document.getElementById('imagePreview');
            imagePreview.src = e.target.result;  // 미리보기 이미지 설정
            imagePreview.style.display = 'block';  // 미리보기 표시
        };
        reader.readAsDataURL(files[0]);  // 첫 번째 파일을 미리보기로 설정
    }
}



document.getElementById('photoInput').addEventListener('change', previewImage);

function goBack() {
    window.location.href = "main.jsp";
}