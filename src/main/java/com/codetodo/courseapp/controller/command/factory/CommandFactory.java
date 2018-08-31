package com.codetodo.courseapp.controller.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.codetodo.courseapp.controller.command.Command;

public interface CommandFactory {
	Command create(HttpServletRequest request);
}
