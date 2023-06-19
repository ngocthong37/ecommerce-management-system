
function addProduct() {

	let productList = document.getElementById("product-list");
	let newProduct = document.createElement("div");
	newProduct.className = "row mb-3 mt-3";
	newProduct.innerHTML = `
                <div class="col">
                  <input type="text" class="form-control" placeholder="ID sản phẩm" name="productItem">
                </div>
                <div class="col">
                  <input type="text" class="form-control" placeholder="Số lượng" name="qty">
                </div>
                <div class="col">
                  <input type="text" class="form-control" placeholder="Đơn giá" name="price">
                </div>
                <div class="col-1">
                  <button type="button" class="btn btn-danger" onclick="removeProduct(this)"><i class="bi bi-trash"></i> Xoá</button>
                </div>`;
	productList.appendChild(newProduct);
}
function removeProduct(button) {
	button.closest(".row").remove();
}
