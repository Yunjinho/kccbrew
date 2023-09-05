document.addEventListener('DOMContentLoaded', () => {
const slides = document.querySelectorAll('.slide');
    const prevButton = document.getElementById('prevSlide');
    const nextButton = document.getElementById('nextSlide');
    let currentSlide = 0;

    function showSlide(index) {
      slides[currentSlide].classList.remove('active');
      slides[index].classList.add('active');
      currentSlide = index;
    }

    prevButton.addEventListener('click', () => {
      if (currentSlide > 0) {
        showSlide(currentSlide - 1);
      }
    });

    nextButton.addEventListener('click', () => {
      if (currentSlide < slides.length - 1) {
        showSlide(currentSlide + 1);
      }
    });

    // 초기에 첫 번째 슬라이드를 표시
    showSlide(0);
});