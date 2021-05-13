package com.chcraft.dogblacklist.badman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("{id}")
	public BadManDTO getById(@PathVariable int id) {
		BadManDTO badMan = badManService.getById(id);

		return badMan;
	}

	@GetMapping("search")
	public List<BadManDTO> searchAll(@RequestParam(required = false, defaultValue = "")
									 String name,
									 @RequestParam(required = false, defaultValue = "")
									 String phone) {
		List<BadManDTO> blacklist = badManService.searchAllByPhone(phone);

		return blacklist;
	}




	@PostMapping
	public String insert(@RequestBody BadManDTO dto) {
		BadManDTO badMan = badManService.insert(dto);

		return "succeed";
	}

	@PostMapping("{id}")
	public String update(@RequestBody BadManDTO dto) {
		BadManDTO badMan = badManService.update(dto);

		return "succeed";
	}


	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		badManService.deleteById(id);
	}
}
