package ptithcm.constant;

public class SystemConstant {
	public static final String MODEL = "model";

	public static class Model {
		public static final String USER_MODEL = "USER_MODEL";
		public static final String CUSTOMER_MODEL = "CUSTOMER_MODEL";
	}

	// Authorization
	public static class Authorization {
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String USER = "ROLE_USER";
		public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";
		public static final String CUSTOMER = "CUSTOMER";
		public static final String SHIPPER = "ROLE_SHIPPER";
	}

	public static class OrderStatus {
		public static final String ON_HOLD = "ON_HOLD";
		public static final String PROCESSING = "PROCESSING";
		public static final String COMPLETED = "COMPLETED";
		public static final String CANCELLED = "CANCELLED";
	}

	public static class ProductStatus {
		public static final String ACTIVE = "ACTIVE";
		public static final String INACTIVE = "INACTIVE";
		public static final String OUT_OF_STOCK = "OUT_OF_STOCK";
		public static final String ON_SALE = "ON_SALE";
	}

	public static class ReceivedStatus {
		public static final String PAID = "PAID";
		public static final String UNPAID = "UNPAID";
		public static final String OVER_DUE = "OVER_DUE";
		public static final String DRAFT = "DRAFT";
	}

	public static class WarrantyStatus {
		public static final String PROCESSING = "PROCESSING";
		public static final String ON_HOLD = "ON_HOLD";
		public static final String COMPLETED = "COMPLETED";
		public static final String DRAFT = "DRAFT";
	}

}
