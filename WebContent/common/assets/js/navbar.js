const sideBar = document.querySelector(".sidebar");
const toggleButton = document.getElementById("sidebar_button");
const listCFMenu = document.querySelectorAll(".cf-menu");
const containerCTS = document.querySelector(".container-cts");
const buttonDisplayNav = document.querySelector(".button-display-nav");

toggleButton.addEventListener("click", () => {
	sideBar.classList.toggle("active");
	if (sideBar.classList.contains("active")) {
		containerCTS.style.marginLeft = "0";
		containerCTS.style.width = "100%";
		buttonDisplayNav.style.display = "block";
	} else {
		containerCTS.style.marginLeft = "280px";
		containerCTS.style.width = "calc(100% - 280px)";
		buttonDisplayNav.style.display = "none";
	}
});

buttonDisplayNav.addEventListener("click", () => {
	sideBar.classList.toggle("active");
	containerCTS.style.marginLeft = "280px";
	containerCTS.style.width = "calc(100% - 280px)";
	buttonDisplayNav.style.display = "none";
});

// menu item
const listMenu = document.querySelectorAll(".menu-item");

listMenu.forEach((item) => {
	item.addEventListener("click", () => {
		listMenu.forEach((itm) => {
			itm.classList.remove("is-active");
		});
		listCFMenu.forEach((itm) => {
			itm.classList.remove("active");
			item.classList.remove("visually");
		});
		item.classList.toggle("is-active");
		console.log(item.parentNode);
		item.parentNode.classList.toggle("active");
		setTimeout(() => {
			item.parentNode.classList.add("visually");
		}, 250);
	});
});

const dropdownBtn = document.getElementById("dropdown-btn");
const dropdownContent = document.getElementById("dropdown-content");
const overlay = document.querySelector(".overlay");

dropdownBtn.addEventListener("click", function() {
	dropdownContent.classList.toggle("show");
	dropdownBtn.classList.toggle("button-display-nav");
});

window.addEventListener("click", function(event) {
	if (
		!dropdownBtn.contains(event.target) &&
		!dropdownContent.contains(event.target)
	) {
		dropdownContent.classList.remove("show");
		dropdownBtn.classList.remove("button-display-nav");
	}
});
