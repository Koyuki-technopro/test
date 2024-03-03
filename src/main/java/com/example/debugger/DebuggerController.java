package com.example.debugger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debugger")
public class DebuggerController {

	@GetMapping
	public String index() {
		return "debugger/index";
	}

}