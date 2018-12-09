package com.itheima.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

public class ObjectFactory {
	//��������һ���������
	public static AccountService getAccountService(){
		final AccountService  as= new AccountServiceImpl(); 
			
		
		AccountService proxy = (AccountService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object invoke = null;
				try {
					ManagerThreadLocal.startTransacation();//begin
					//ִ�е�����ʵ�����ת�˷�������accountserviceimpl�����ת�˷���
					invoke = method.invoke(as, args);
					
					ManagerThreadLocal.commit();//�ύ����
				} catch (Exception e) {
					try {
						ManagerThreadLocal.rollback();//�ع�����
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}finally{
					try {
						ManagerThreadLocal.close();
					} catch (Exception e) {
						e.printStackTrace();
					}//�ر�
				}
				return invoke;
			}
		});
		return proxy;	
	}
}
