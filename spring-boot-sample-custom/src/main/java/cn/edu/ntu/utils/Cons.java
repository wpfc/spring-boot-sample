package cn.edu.ntu.utils;

import java.math.BigDecimal;

/**
 *
 * @author luoyh(Roy) - May 24, 2017
 */
public final class Cons {
	private Cons() {}
	
	public static final int OK = 200;
	public static final int ERR = 300;
	public static final String BOSS_SESSION_KEY = "f^ck";

	public static final BigDecimal PERCENT = new BigDecimal("100");
	public static final BigDecimal ZERO = new BigDecimal("0");
	
	
	/**
	 * session的描�?
	 */
	public static final String SESSION_INFO = "wsessionInfo" ;
	
	public static final String EMPLOYEE_POWER ="employee_power";
	
	
	
	/**
	 * 是否是�?�店 1是，0不是
	 * @author yangjing
	 *
	 */
	public interface IS_FIRST_EMPLOYE{
		/**
		 * 总店
		 */
		public static final String IS_FIRST = "1";
		/**
		 * 分店
		 */
		public static final String IS_FIRST_BRANCH = "0";
	}
	
	/**
	 * 门店状�??
	 */
	public interface STORE_STATUS {
		/**
		 * 停业
		 */
		public static final String CLOSED_STATUS = "0";
		/**
		 * �?�?
		 */
		public static final String OPEN_STATUS = "1";
	}
	
	/**
	 * 商品状�??   0无效 1有效 2删除
	 */
	public interface MENU_STATUS {
		/**
		 * 无效
		 */
		public static final String INVALID_STATUS = "0";
		/**
		 * 有效
		 */
		public static final String NORMAL_STATUS = "1";
		/**
		 * 删除
		 */
		public static final String DELETE_STATUS = "2";
	}

	/**
	 * 员工状�??
	 */
	public interface EMPLOYEE_STATUS {
		/**
		 * 无效
		 */
		public static final String CLOSED_STATUS = "0";
		/**
		 * 有效
		 */
		public static final String OPEN_STATUS = "1";
	}
	
	/**
	 * 采购订单状�??
	 *  0待审�? 1 已审�? 2 已采�?  3 无效订单
	 */
	public interface ORDER_STATUS {
		/**
		 * 待审�?
		 */
		public static final String  PENDING_STATUS = "0";
		/**
		 * 已审�?
		 */
		public static final String  APPROVED_STATUS = "1";
		/**
		 * 已采�?
		 */
		public static final String  ACHIEVE_STATUS = "2";
		/**
		 * 无效订单
		 */
		public static final String INVALID_STATUS = "3";
	}
	
	/**
	 * 采购状�??
	 * 0未入�? 1已入�?
	 */
	public interface IS_IN_STORAGE {
		/**
		 * 未入�?
		 */
		public static final String ACHIEVE_STORAGE = "0";
		/**
		 * 已入�?
		 */
		public static final String INVALID_STORAGE = "1";
	}

	/**
	 * �?货订�?   0待审�?   1：已审批   3：无效订单（审核不�?�过�?
	 */
	public interface RETURN_ORDER_STATUS {
		/**
		 * 待审�?
		 */
		public static final String WAITTING_STATUS = "0";
		/**
		 * 已审�?
		 */
		public static final String AGREE_STATUS = "1";
		/**
		 * 无效订单（审核不通过�?
		 */
		public static final String DISAGREE_STATUS = "2";
	}
	
	/**
	 * 是否自动升级(1:是，0�?)
	 * @author yangjing
	 *
	 */
	public interface GRADE_IS_AUTO_UPDATE {
		/**
		 * 自动升级:1
		 */
		public static final String GRADE_AUTO_UPDATE = "1";
		
		/**
		 * 不能自动升级:0
		 */
		public static final String GRADE_NO_AUTO_UPDATE = "0";
	}
	
	/**
	 * 状�??  0无效 1有效
	 * @author yangjing
	 *
	 */
	public interface COUPON_STATUS{
		/**
		 * 1有效
		 */
		public static final String COUPON_VALIDITY = "1";
		/**
		 *  0无效
		 */
		public static final String COUPON_NOT_VALIDITY = "0";
	}
	
}
