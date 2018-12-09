package com.itheima.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.util.C3P0Util;
import com.itheima.util.ManagerThreadLocal;

public class AccountServiceImpl implements AccountService {

	public void transfer(String fromname, String toname, double money) throws Exception {
	//	ad.updateAccount(fromname, toname, money);
		AccountDao ad = new AccountDaoImpl();
			//�ֱ�õ�ת����ת���˻�����
			Account fromAccount = ad.findAccountByName(fromname);
			Account toAccount = ad.findAccountByName(toname);
			
			//�޸��˻����ԵĽ��
			fromAccount.setMoney(fromAccount.getMoney()-money);
			toAccount.setMoney(toAccount.getMoney()+money);
			
			//���ת�˲���
			ad.updateAccout(fromAccount);
		int i = 10/0;
			ad.updateAccout(toAccount);
	}
}
