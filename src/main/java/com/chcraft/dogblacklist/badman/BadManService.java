package com.chcraft.dogblacklist.badman;

import java.util.List;

public interface BadManService {
	List<BadManDTO> getAll();
	List<BadManDTO> searchAllByName(String name);
	List<BadManDTO> searchAllByPhone(String phone);
	List<BadManDTO> searchAllByCondition(String name, String phone);
	BadManDTO getById(int id);

	BadManDTO insert(BadManDTO dto);
	BadManDTO update(BadManDTO dto);
	void delete(BadManDTO dto);
	void deleteById(int id);
}