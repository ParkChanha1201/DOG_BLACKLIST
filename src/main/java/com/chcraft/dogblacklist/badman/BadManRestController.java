package com.chcraft.dogblacklist.badman;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("badman")
public class BadManRestController {
	@GetMapping("list")
	public List<BadMan> getAll(){
		return null;
	}
}
