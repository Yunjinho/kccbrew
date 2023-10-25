

const navBar = document.querySelector('.nav-bar');

navBar.addEventListener('mouseenter', showAllDropdowns);
navBar.addEventListener('mouseleave', hideAllDropdowns);

function showAllDropdowns() {
	const dropdowns = document.querySelectorAll('.comm-nav-dropdown');
	dropdowns.forEach(dropdown => {
		dropdown.style.display = 'block';
	});
}

function hideAllDropdowns() {
	const dropdowns = document.querySelectorAll('.comm-nav-dropdown');
	dropdowns.forEach(dropdown => {
		dropdown.style.display = 'none';
	});
}
