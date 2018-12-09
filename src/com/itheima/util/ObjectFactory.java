package com.itheima.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

public class ObjectFactory {
	//方法返回一个代理对象
	public static AccountService getAccountService(){
		final AccountService  as= new AccountServiceImpl(); 
			
		
		AccountService proxy = (AccountService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object invoke = null;
				try {
					ManagerThreadLocal.startTransacation();//begin
					//执行的是真实对象的转账方法，即accountserviceimpl里面的转账方法
					invoke = method.invoke(as, args);
					
					ManagerThreadLocal.commit();//提交事务
				} catch (Exception e) {
					try {
						ManagerThreadLocal.rollback();//回滚事务
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}finally{
					try {
						ManagerThreadLocal.close();
					} catch (Exception e) {
						e.printStackTrace();
					}//关闭
				}
				return invoke;
			}
		});
		return proxy;	
	}
}
