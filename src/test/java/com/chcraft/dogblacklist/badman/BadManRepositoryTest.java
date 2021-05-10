package com.chcraft.dogblacklist.badman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BadManRepositoryTest {
	@Autowired
	BadManRepository badManRepository;

	@Test
	public void insertAndDeleteTest() {
		BadMan badMan = new BadMan();

		badMan.setName("홍길동");
		badMan.setPhone("01012341234");

		int insertBefore = (int)badManRepository.count();
		badManRepository.save(badMan);
		int insertAfter = (int)badManRepository.count();

		assertEquals(insertBefore + 1, insertAfter);

		//delete test
		Optional<BadMan> oBadMan = badManRepository.findByName(badMan.getName());
		assertEquals(true, oBadMan.isPresent());

		badManRepository.deleteById(oBadMan.get().getId());

		Optional<BadMan> deleted = badManRepository.findById(oBadMan.get().getId());
		assertEquals(false, deleted.isPresent());
	}

	@Test
	public void updateTest() {
		BadMan badMan = new BadMan();
		badMan.setName("홍길동");
		badMan.setPhone("01012341234");
		badManRepository.save(badMan);

		badMan = badManRepository.findByName(badMan.getName()).get();

		badMan.setName("고길동");
		badMan.setPhone("15882222");
		//update badMan
		badManRepository.save(badMan);

		BadMan modified = badManRepository.findById(badMan.getId()).get();

		assertEquals(true, badMan.equals(modified));

		badMan.setName("쓰레기");
		badMan.setPhone("558148664");
		//update badMan again
		badManRepository.save(badMan);

		modified = badManRepository.findById(badMan.getId()).get();

		assertEquals(true, badMan.equals(modified));
	}

	@Test
	public void countTest() {
		int count = (int)badManRepository.count();

		List<BadMan> badmanlist = badManRepository.findAll();

		assertEquals(count, badmanlist.size());
	}

	@Test
	public void findAllAndFindByIdTest() {
		List<BadMan> blacklist = badManRepository.findAll();

		for(BadMan badMan : blacklist) {
			BadMan tmp = badManRepository.findById(badMan.getId()).get();

			assertEquals(true, badMan.equals(tmp));
		}
	}

}
