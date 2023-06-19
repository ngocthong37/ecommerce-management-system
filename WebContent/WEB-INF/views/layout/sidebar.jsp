<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<aside class="layout">
	<div class="sidebar">
		<button class="sidebar_button" id="sidebar_button">
			<i class="fa-solid fa-x"></i>
		</button>
		<div class="sidebar__header">
			<a href="#" class="sidebar__header-navigate">
				<div>
					<img src="<c:url value='/common/images/logo/logo.png'/>" alt="" />
				</div>
			</a> <a href="#" class="sidebar__header-profile">
				<div class="content">
					<div class="avatar">
						<img
							src="https://images.unsplash.com/photo-1613200510878-1629a870b983?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
							alt="avatar" />
					</div>
					<div class="information">
						<h6>
							<c:if test="${not empty CUSTOMER_MODEL}">
								${CUSTOMER_MODEL.userName}
							</c:if>
							<c:if test="${not empty USER_MODEL}">
								${USER_MODEL.username}
							</c:if>

						</h6>
						<p>
							<c:if test="${not empty CUSTOMER_MODEL}">
								customer
							</c:if>
							<c:if test="${not empty USER_MODEL}">
								${USER_MODEL.userPermission.value}
							</c:if>
						</p>
					</div>
				</div>
			</a>
		</div>
		<div class="menu">
			<h4>Chung</h4>
			<ul class="menu">
			
				<c:if test="${not empty USER_MODEL && USER_MODEL.userPermission.value == 'ROLE_SHIPPER'}">
					<li class="menu-item"><a
						href="${contextPath}/delivery/listDeliveryOrder.htm" class="menu-item-btn-s">
							<div class="icon">
								<span><i class="fa-solid fa-chart-line"></i></span>
							</div>
							<div class="label">
								<span>Danh sách đơn hàng đang vận chuyển</span>
							</div>
					</a></li>
				</c:if>
				<c:if
					test="${not empty CUSTOMER_MODEL || empty CUSTOMER_MODEL && empty USER_MODEL}">

					<li class="menu-item"><a
						href="${contextPath}/e-commerce/shop.htm" class="menu-item-btn-s">
							<div class="icon">
								<span><i class="fa-solid fa-gauge-high"></i></span>
							</div>
							<div class="label">
								<span>Trang chủ</span>
							</div>
					</a></li>
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-shop"></i></span>
								</div>
								<div class="label">
									<span>E-Commerce</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/e-commerce/shop.htm">Cửa hàng</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/e-commerce/cart.htm">Thanh toán</a></li>
						</ul>
					</div>
					<c:if test="${not empty CUSTOMER_MODEL}">
						<div class="cf-menu">
							<li class="menu-item"><span class="menu-item-btn">
									<div class="icon">
										<span><i class="fa-solid fa-id-card-clip"></i></span>
									</div>
									<div class="label">
										<span>Người dùng</span>
									</div>
									<div class="icon">
										<span><i class="fa-solid fa-angle-down"></i></span>
									</div>
							</span></li>
							<ul class="submenu">
								<li class="submenu-item"><a
									href="${contextPath}/customer/profile.htm">Trang cá nhân</a></li>
								<li class="submenu-item"><a
									href="${contextPath}/customer/orderManage.htm">Quản lý đơn
										hàng</a></li>
							</ul>
						</div>
					</c:if>
				</c:if>
				<c:if
					test="${not empty USER_MODEL && (USER_MODEL.userPermission.value == 'ROLE_ADMIN' || USER_MODEL.userPermission.value == 'ROLE_SUPER_ADMIN')}">
					<li class="menu-item"><a
						href="${contextPath}/admin/analytics.htm" class="menu-item-btn-s">
							<div class="icon">
								<span><i class="fa-solid fa-chart-line"></i></span>
							</div>
							<div class="label">
								<span>Thống kê</span>
							</div>
					</a></li>
				</c:if>

			</ul>
		</div>
		<c:if test="${not empty USER_MODEL && USER_MODEL.userPermission.value != 'ROLE_SHIPPER'}">

			<div class="menu">
				<h4>Quản lý</h4>
				<ul class="menu">
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-id-card-clip"></i></span>
								</div>
								<div class="label">
									<span>Quản lý đơn hàng</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/manage-ordered/list.htm">Danh
									sách đơn hàng đã đặt</a></li>
						</ul>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/delivery/listDeliveryOrder.htm">Đang
									vận chuyển</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-id-card-clip"></i></span>
								</div>
								<div class="label">
									<span>Quản lý người dùng</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/user/profile.htm">Thông tin cá nhân</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/user/list.htm">Danh sách nhân viên</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/user/new.htm">Tạo nhân viên</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-id-card-clip"></i></span>
								</div>
								<div class="label">
									<span>Quản lý khách hàng</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a href="${contextPath}/admin/customer/list.htm">Danh sách khách
									hàng</a></li>

						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-truck-ramp-box"></i></span>
								</div>
								<div class="label">
									<span>Đại lý</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/agency/list.htm">Danh sách đại lý</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/agency/create.htm">Thêm đại lý</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/inventory.htm">Nhập hàng</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/inventory/list.htm">Danh sách đơn nhập</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><span class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-receipt"></i></span>
								</div>
								<div class="label">
									<span>Hoá đơn</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</span></li>
						<ul class="submenu">
							<li class="submenu-item"><a href="#">Danh sách hoá đơn</a></li>
							<li class="submenu-item"><a href="#">Thêm hoá đơn</a></li>
						</ul>
					</div>
				</ul>
			</div>

			<div class="menu">
				<h4>Sản phẩm</h4>
				<ul class="menu">
					<div class="cf-menu">
						<li class="menu-item"><a class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-mobile-retro"></i></span>
								</div>
								<div class="label">
									<span>Quản lý sản phẩm</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</a></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/list.htm">Danh sách sản
									phẩm</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/new.htm">Tạo mới sản phẩm</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/product-config/list.htm">Cấu
									hình sản phẩm</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/new.htm">Nhập sản phẩm</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><a class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-tags"></i></span>
								</div>
								<div class="label">
									<span>Quản lý nhãn</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</a></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/category/list.htm">Danh
									sách nhãn</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/category/new.htm">Tạo mới
									nhãn</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><a class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-atom"></i></span>
								</div>
								<div class="label">
									<span>Quản lý thuộc tính</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</a></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/variation/list.htm">Danh
									sách thuộc tính</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/variation/new.htm">Tạo
									mới thuộc tính</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><a class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-percent"></i></span>
								</div>
								<div class="label">
									<span>Quản lý khuyến mãi</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</a></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/promotion/list.htm">Danh
									sách khuyến mãi</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/promotion/create.htm">Tạo
									mới khuyến mãi</a></li>
						</ul>
					</div>
					<div class="cf-menu">
						<li class="menu-item"><a class="menu-item-btn">
								<div class="icon">
									<span><i class="fa-solid fa-shield"></i></span>
								</div>
								<div class="label">
									<span>Quản lý bảo hành</span>
								</div>
								<div class="icon">
									<span><i class="fa-solid fa-angle-down"></i></span>
								</div>
						</a></li>
						<ul class="submenu">
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/warranty/list.htm">Danh
									sách bảo hành</a></li>
							<li class="submenu-item"><a
								href="${contextPath}/admin/product/warranty/new.htm">Tạo mới
									bảo hành</a></li>
						</ul>
					</div>
				</ul>
			</div>
		</c:if>
	</div>
</aside>
<header class="area-header">
	<button class="button-display-nav" tabindex="0" type="button">
		<svg xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true"
			role="img" class="MuiBox-root css-1t9pz9x iconify iconify--eva"
			width="1em" height="1em" viewBox="0 0 24 24">
                    <g id="iconifyReact555">
                        <g id="iconifyReact556">
                            <g id="iconifyReact557" fill="currentColor">
                                <circle cx="4" cy="12" r="1"></circle>
                                <rect width="14" height="2" x="7" y="11"
				rx=".94" ry=".94"></rect>
                                <rect width="18" height="2" x="3" y="16"
				rx=".94" ry=".94"></rect>
                                <rect width="18" height="2" x="3" y="6"
				rx=".94" ry=".94"></rect>
                            </g>
                        </g>
                    </g>
                </svg>
		<span class="MuiTouchRipple-root css-w0pj6f"></span>
	</button>
	<div class="header-tab">
		<div class="tab-wrapper">
			<c:if test="${not empty USER_MODEL}">
				<div class="btn-home">
					<img src="<c:url value ='/common/assets/icons/ic_logo.svg'/>"
						alt="header_menu_item_home"><a
						href="${contextPath}/admin/analytics.htm">Trang chủ</a>
				</div>
			</c:if>
			<c:if test="${not empty CUSTOMER_MODEL || (empty USER_MODEL && empty CUSTOMER_MODEL)}">
				<div class="btn-home">
					<img src="<c:url value ='/common/assets/icons/ic_logo.svg'/>"
						alt="header_menu_item_home"><a
						href="${contextPath}/e-commerce/shop.htm">Trang chủ</a>
				</div>
			</c:if>
			
		</div>
		<c:if test="${empty USER_MODEL && empty CUSTOMER_MODEL}">

			<div class="tab-wrapper">
				<div class="btn-register">
					<a href="${contextPath}/e-commerce/login.htm">Đăng nhập</a>
				</div>
			</div>
			<div class="tab-wrapper">
				<div class="btn-register">
					<a href="${contextPath}/e-commerce/signIn.htm">Đăng ký</a>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty USER_MODEL || not empty CUSTOMER_MODEL}">
			<div class="tab-wrapper" tabindex="0"
				style="transform: scale(1.09) translateZ(0px);">
				<button id="dropdown-btn" tabindex="0" type="button">
					<div class="avatar-warapper">
						<img alt="Minimal UI"
							src="https://images.unsplash.com/photo-1685703206477-aa1df00a1f0e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzfHx8ZW58MHx8fHx8&auto=format&fit=crop&w=500&q=60">
					</div>
					<span class="touchable"></span>
					<div class="overlay"></div>
				</button>
				<div id="dropdown-content">
					<div class="dropdown-item">
						<h6>Minimal UI</h6>
						<p>demo@minimals.cc</p>
					</div>
					<hr>
					<div class="dropdown-list">
						<c:if test="${not empty CUSTOMER_MODEL}">
							<a href="${contextPath}/e-commerce/shop.htm">
								<li class="list-item" tabindex="-1" role="menuitem">Trang
									chủ<span class="MuiTouchRipple-root css-w0pj6f"></span>
							</li>
							</a>
							<a href="${contextPath}/customer/profile.htm">
								<li class="list-item" tabindex="-1" role="menuitem">Thông
									tin cá nhân<span class="MuiTouchRipple-root css-w0pj6f"></span>
							</li>
							</a>
						</c:if>
						<c:if test="${not empty USER_MODEL}">
							<a href="${contextPath}/admin/analytics.htm">
								<li class="list-item" tabindex="-1" role="menuitem">Trang
									chủ<span class="MuiTouchRipple-root css-w0pj6f"></span>
							</li>
							</a>
							<a href="${contextPath}/user/profile.htm">
								<li class="list-item" tabindex="-1" role="menuitem">Thông
									tin cá nhân<span class="MuiTouchRipple-root css-w0pj6f"></span>
							</li>
							</a>
						</c:if>
					</div>
					<hr>
					<div class="dropdown-list">
						<a href="${contextPath}/e-commerce/logOut.htm">
							<li class="list-item" tabindex="-1" role="menuitem">Đăng
								xuất<span class="MuiTouchRipple-root css-w0pj6f"></span>
						</li>
						</a>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</header>