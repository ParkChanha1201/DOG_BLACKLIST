package com.chcraft.dogblacklist.badman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("badman")
public class BadManRestController {
	@Autowired
	BadManService badManService;

	@GetMapping("list")
	public List<BadManDTO> getAll(){
		List<BadManDTO> badManList = badManService.getAll();

		return badManList;
	}
}
