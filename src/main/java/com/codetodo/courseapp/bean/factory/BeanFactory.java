package com.codetodo.courseapp.bean.factory;

import com.codetodo.courseapp.controller.command.Command;

public interface BeanFactory {
	Command getBean(String name);
}
