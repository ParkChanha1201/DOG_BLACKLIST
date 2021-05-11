package com.chcraft.dogblacklist.badman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chcraft.dogblacklist.entity.Convertable;

@Service
public class BadManServiceImpl implements BadManService, Convertable<BadMan, BadManDTO> {
	@Autowired
	BadManRepository badManRepository;

	@Override
	public List<BadManDTO> convertEntityList(List<BadMan> entityList) {
		if(entityList == null)
			return new ArrayList<BadManDTO>();

		List<BadManDTO> dtoList = new ArrayList<>();

		for(BadMan badMan : entityList)
			dtoList.add(new BadManDTO(badMan));

		return dtoList;
	}

	@Override
	public List<BadMan> convertDtoList(List<BadManDTO> dtoList) {
		if(dtoList == null)
			return new ArrayList<BadMan>();

		List<BadMan> entityList = new ArrayList<BadMan>();

		for(BadManDTO dto : dtoList)
			entityList.add(new BadMan(dto));

		return entityList;
	}

	@Override
	public BadManDTO convertEntity(BadMan entity) {
		if(entity == null)
			return new BadManDTO();

		return new BadManDTO(entity);
	}

	@Override
	public BadMan convertDto(BadManDTO dto) {
		if(dto == null)
			return new BadMan();

		return new BadMan(dto);
	}

	@Override
	public List<BadManDTO> getAll() {
		List<BadMan> badManList = badManRepository.findAll();
		List<BadManDTO> dtoList = convertEntityList(badManList);

		return dtoList;
	}

	@Override
	public List<BadManDTO> searchAllByName(String name) {
		List<BadMan> badManList = badManRepository.findAllByName(name);
		List<BadManDTO> dtoList = convertEntityList(badManList);

		return dtoList;
	}

	@Override
	public List<BadManDTO> searchAllByPhone(String phone) {
		List<BadMan> badManList = badManRepository.findAllByPhone(phone);
		List<BadManDTO> dtoList = convertEntityList(badManList);

		return dtoList;
	}

	@Override
	public BadManDTO getById(int id) {
		Optional<BadMan> oBadMan = badManRepository.findById(id);

		if(!oBadMan.isPresent())
			return null;

		return convertEntity(oBadMan.get());
	}

	@Override
	public BadManDTO insert(BadManDTO dto) {
		BadMan badMan = badManRepository.save(convertDto(dto));

		return new BadManDTO(badMan);
	}

	@Override
	public BadManDTO update(BadManDTO dto) {
		BadMan badMan = badManRepository.save(convertDto(dto));

		return new BadManDTO(badMan);
	}

	@Override
	public void delete(BadManDTO dto) {
		if(dto == null)
			return;

		badManRepository.deleteById(dto.getId());
	}

	@Override
	public void deleteById(int id) {
		badManRepository.deleteById(id);
	}
}
