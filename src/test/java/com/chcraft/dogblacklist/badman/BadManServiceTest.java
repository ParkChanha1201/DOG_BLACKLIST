package com.chcraft.dogblacklist.badman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
//	@Autowired
//	BadManService badManService;
	@Autowired
	BadManRepository badManRepository;

	int dataSize;

	@Before
	public void init() {
		String dir = System.getProperty("user.dir");
		String path = dir + "//src//test//java//com//chcraft//dogblacklist//badman//";
		File data = new File(path + "mock_data_badman.json");
		try (FileInputStream in = new FileInputStream(data);){
			JSONArray blacklist = new JSONArray(new String(in.readAllBytes()));

			int length = blacklist.length();
			dataSize = length;

			for(int i = 0; i < length; i++) {
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
	public void getAllUser() {
		List<BadMan> blacklist = badManRepository.findAll();

		assertEquals(dataSize, blacklist.size());
	}

}
