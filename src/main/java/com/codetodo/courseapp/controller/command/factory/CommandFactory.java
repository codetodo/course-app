package com.codetodo.courseapp.controller.command.factory;

import com.codetodo.courseapp.controller.command.Command;

public interface CommandFactory {
	Command create(String operation);
}
