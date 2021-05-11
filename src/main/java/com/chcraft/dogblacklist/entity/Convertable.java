package com.chcraft.dogblacklist.entity;

import java.util.List;

/***
 * 엔티티 클래스를 dto 클래스로 변환가능
 *
 * @param <E> entity class
 * @param <D> dto class
 */
public interface Convertable<E, D> {
	List<D> convertEntityList(List<E> entityList);
	List<E> convertDtoList(List<D> dtoList);
	D convertEntity(E entity);
	E convertDto(D dto);
}
