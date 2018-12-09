package com.itheima.test;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.util.ObjectFactory;

public class TestTransfer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		AccountService as = ObjectFactory.getAccountService();
		as.transfer("aaa", "bbb", 100);
	}

}
