package com.chcraft.dogblacklist.badman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BadManServiceTest {
	@Autowired
	BadManService badManService;
	@Autowired
	BadManRepository badManRepository;

	int dataSize;

	String searchName = "고길동";
	int searchNameCount = 16;

	String searchPhone = "99999999999";
	int searchPhoneCount = 4;

	@Before
	public void init() {
		String dir = System.getProperty("user.dir");
		String path = dir + "//src//test//java//com//chcraft//dogblacklist//badman//";
		// 100명의 데이터가 있다. 16명의 이름은 고길동이다.
		File data = new File(path + "mock_data_badman.json");
		try (FileInputStream in = new FileInputStream(data);) {
			JSONArray blacklist = new JSONArray(new String(in.readAllBytes()));

			int length = blacklist.length();
			dataSize = length;

			for (int i = 0; i < length; i++) {
				String name = blacklist.getJSONObject(i).getString("name");
				String phone = blacklist.getJSONObject(i).getString("phone");
				BadMan badman = new BadMan(name, phone);
				badManRepository.save(badman);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllTest() {
		List<BadManDTO> blacklist = badManService.getAll();

		assertEquals(dataSize, blacklist.size());
	}

	@Test
	public void searchAllByNameTest() {
		List<BadManDTO> searched = badManService.searchAllByName(searchName);

		assertEquals(searchNameCount, searched.size());
	}

	@Test
	public void searchAllByPhoneTest() {
		List<BadManDTO> searched = badManService.searchAllByPhone(searchPhone);

		assertEquals(searchPhoneCount, searched.size());
	}

	@Test
	public void insertAndUpdateAndDeleteTest() {
		// insert
		BadManDTO badman = new BadManDTO();
		badman.setName("절대존재하지않을이름");
		badman.setPhone("01012341234");

		int countBefore = badManService.getAll().size();
		badman = badManService.insert(badman);
		int countAfter = badManService.getAll().size();

		assertEquals(countBefore + 1, countAfter);

		// update
		badman.setName("이것또한존재할수없을것");
		badman.setPhone("01098765432");

		BadManDTO modified = badManService.update(badman);

		assertEquals(true, badman.equals(modified));

		badman.setName("이무슨말도안되는");
		badman.setPhone("01045678912");

		modified = badManService.update(badman);
		assertEquals(true, badman.equals(modified));

		// delete
		badManService.delete(badman);

		BadManDTO deleted = badManService.getById(badman.getId());
		assertEquals(null, deleted);

		BadManDTO badman2 = new BadManDTO();
		badman2.setName("삭제되기위하여태어남");
		badman2.setPhone("01043214321");

		badman2 = badManService.insert(badman2);

		badManService.deleteById(badman2.getId());
		deleted = badManService.getById(badman2.getId());
		assertEquals(null, deleted);
	}

	@Test
	public void searchAllByConditionTest() {
		// prepare data for test
		String name = "무시무시한참치";
		String phone = "01016567894";

		final int ONLY_NAME_MATCH_COUNT = 15;
		final int ONLY_PHONE_MATCH_COUNT = 10;
		final int MATCH_BOTH_COUNT = 4;

		String dummyNamePrefix = "dummy_";
		int dummyCount = 1;
		Random rand = new Random();

		// insert BadMan ,has name "" and has other phone number;
		for (int i = 0; i < ONLY_NAME_MATCH_COUNT; i++) {
			badManService.insert(new BadManDTO(name, rand.nextInt(99999999) + "1"));
		}

		for (int i = 0; i < ONLY_PHONE_MATCH_COUNT; i++) {
			badManService.insert(new BadManDTO(dummyNamePrefix + dummyCount++, phone));
		}

		for (int i = 0; i < MATCH_BOTH_COUNT; i++) {
			badManService.insert(new BadManDTO(name, phone));
		}

		// searchAllByName Test
		List<BadManDTO> nameMatch = badManService.searchAllByName(name);
		assertEquals(ONLY_NAME_MATCH_COUNT + MATCH_BOTH_COUNT, nameMatch.size());

		// searchAllByPhone Test
		List<BadManDTO> phoneMatch = badManService.searchAllByPhone(phone);
		assertEquals(ONLY_PHONE_MATCH_COUNT + MATCH_BOTH_COUNT, phoneMatch.size());

		// searchAllByCondition Test
		List<BadManDTO> allMatch = badManService.searchAllByCondition(name, phone);
		assertEquals(MATCH_BOTH_COUNT, allMatch.size());
	}
}
