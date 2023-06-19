// Dynamic field
const addBtn = document.getElementById("btnAddField");
const categoryList = document.querySelector(".category");

function addDynamicField(e) {
	e.preventDefault();
	const div = document.createElement("div");
	div.classList.add("wrapper");
	div.innerHTML = `<div class="wrapper-delete-item">
	  <h6>Biến thể sản phẩm</h6>
	  <button class="btn--delete remove-item-btn" type="button">
	      <span>Xoá</span>
	  </button>
	</div>
	<div class="input-container">
	  <input type="text" required="required" id="SKU" name="SKU" aria-labelledby="SKU"><span
	      class="highlight"></span><span class="bar"></span>
	  <label for="SKU">SKU</label>
	</div>
	<div class="device">
	  <div class="input-container">
	      <input type="number" required="required" id="qty_in_stock" name="qty_in_stock"
	          aria-labelledby="qty_in_stock"><span class="highlight"></span><span
	          class="bar"></span>
	      <label for="qty_in_stock">Số lượng sản phẩm</label>
	  </div>
	  <div class="input-container">
	      <input type="number" required="required" id="product_price" name="product_price"
	          aria-labelledby="product_price"><span class="highlight"></span><span
	          class="bar"></span>
	      <label for="product_price">Giá sản phẩm</label>
	  </div>
	  <div class="input-container">
	      <input type="number" required="required" id="warranty_time" name="warranty_time"
	          aria-labelledby="warranty_time"><span class="highlight"></span><span
	          class="bar"></span>
	      <label for="warranty_time">Thời gian bảo hành (tháng)</label>
	  </div>
	</div>
	<div class="upload-image">
	  <h6>Ảnh thumbnail</h6>
	  <div>
	      <div class="presentation">
	          <input accept="image/*" multiple type="file" tabindex="-1" id="file-input"
	              style="display: none;" name="product_image">
	          <div class="image">
	              <img src="./illustration_upload.svg" alt=""
	                  style="max-width: 200px; max-height: 300px; object-fit: cover;">
	              <div style="margin-left: 40px;">
	                  <h5 class="">
	                      Nhấn vào để chọn file ảnh
	                  </h5>
	                  <p class="">
	                      Drop files here or click <span class="browse-link">browse</span>
	                      thorough your machine
	                  </p>
	              </div>
	          </div>
	      </div>
	  </div>
	</div>`;
	categoryList.appendChild(div);
	// add event listener to the newly added remove button
	const removeItem = div.querySelector(".remove-item-btn");
	removeItem.addEventListener("click", removeDynamicItem);

	// initialize file input and image preview
	const fileInput = div.querySelector("input[type='file']");
	const browseLink = div.querySelector(".upload-image");
	const imagePreview = div.querySelector(".image img");

	// Listen for file input changes
	fileInput.addEventListener("change", () => {
		const file = fileInput.files[0];
		// Validate that the selected file is an image
		if (!file.type.startsWith("image/")) {
			console.log("Invalid file type.");
			return;
		}

		// Create a new FileReader object
		const reader = new FileReader();

		// Listen for the FileReader load event
		reader.addEventListener("load", () => {
			// Set the src attribute of the image preview to the loaded data URL
			imagePreview.src = reader.result;
		});

		// Read the selected file as a data URL
		reader.readAsDataURL(file);
	});
	// Listen for browse link clicks
	browseLink.addEventListener("click", () => {
		fileInput.click();
	});
}

function removeDynamicItem(e) {
	e.preventDefault();
	const wrapper = this.closest(".wrapper");
	wrapper.parentNode.removeChild(wrapper);
	console.log("remove");
}

addBtn.addEventListener("click", addDynamicField);

// add event listener to existing remove buttons
//const removeItems = document.querySelectorAll(".remove-item-btn");
//removeItems.forEach((item) =>
//	item.addEventListener("click", removeDynamicItem)
//);

const form = document.querySelector(".form-create-product");

// upload image
const fileInput = document.getElementById("file-input");
const browseLink = document.querySelector(".upload-image");
const imagePreview = document.querySelector(".image img");

// Listen for file input changes
fileInput.addEventListener("change", () => {
	const file = fileInput.files[0];

	// Validate that the selected file is an image
	if (!file.type.startsWith("image/")) {
		console.log("Invalid file type.");
		return;
	}

	// Create a new FileReader object
	const reader = new FileReader();

	// Listen for the FileReader load event
	reader.addEventListener("load", () => {
		// Set the src attribute of the image preview to the loaded data URL
		imagePreview.src = reader.result;
	});

	// Read the selected file as a data URL
	reader.readAsDataURL(file);
});

// Listen for browse link clicks
browseLink.addEventListener("click", () => {
	fileInput.click();
});

function initializeImageUpload(container) {
	const fileInput = container.querySelector("input[type='file']");
	const browseLink = container.querySelector(".upload-image");
	const imagePreview = container.querySelector(".image img");

	// Listen for file input changes
	fileInput.addEventListener("change", () => {
		const file = fileInput.files[0];

		// Validate that the selected file is an image
		if (!file.type.startsWith("image/")) {
			console.log("Invalid file type.");
			return;
		}

		// Create a new FileReader object
		const reader = new FileReader();

		// Listen for the FileReader load event
		reader.addEventListener("load", () => {
			// Set the src attribute of the image preview to the loaded data URL
			imagePreview.src = reader.result;
		});

		// Read the selected file as a data URL
		reader.readAsDataURL(file);
	});

	// Listen for browse link clicks
	browseLink.addEventListener("click", () => {
		fileInput.click();
	});
}
// Initialize image upload for the initial field
const initialContainer = document.querySelector(".form-create-product");
initializeImageUpload(initialContainer);

// Initialize image upload for the dynamically added fields
const dynamicContainers = document.querySelectorAll(".category .wrapper");
dynamicContainers.forEach((container) => {
	initializeImageUpload(container);
});
