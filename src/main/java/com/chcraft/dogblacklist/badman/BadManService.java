package com.chcraft.dogblacklist.badman;

import java.util.List;

public interface BadManService {
	List<BadManDTO> getAll();
	List<BadManDTO> searchAllByName();
	BadManDTO searchByPhone(String phone);

	BadManDTO insert(BadManDTO dto);
	BadManDTO update(BadManDTO dto);
	void delete(BadManDTO dto);
	void deleteById(int id);
}