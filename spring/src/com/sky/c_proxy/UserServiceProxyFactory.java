package com.sky.c_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sky.service.UserService;
import com.sky.service.UserServiceImpl;

public class UserServiceProxyFactory implements InvocationHandler{

	private UserService us;
	
	public UserServiceProxyFactory(UserService us) {
		super();
		this.us = us;
	}

	public UserService getUserServiceProxy() {
		//生成动态代理
		UserService usProxy = (UserService) Proxy.newProxyInstance(UserServiceProxyFactory.class.getClassLoader(),
				UserServiceImpl.class.getInterfaces(), 
				this);
		//返回
		return usProxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("打开事务！");
		Object invoke = method.invoke(us, args);
		System.out.println("提交事务！");
		return invoke;
	}
	
}
