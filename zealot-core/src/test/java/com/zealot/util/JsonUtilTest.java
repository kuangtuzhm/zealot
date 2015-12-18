package com.zealot.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonUtilTest {


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFromJson() {
		String str = "{teacher:\"方芳芳\",name:\"张三\",\"age\":12,\"course\":[{\"name\":\"语文\",\"score\":99},{\"name\":\"数学\",\"score\":100}]}";
		
		Student s = JsonUtil.fromJson(str, Student.class);
		
		System.out.println(s.getName());
	}

	@Test
	public void testToJson() {
		
		Student s = new Student();
		s.setName("张三");
		s.setAge(12);
		
		List<Course> list = new ArrayList<Course>();
		Course c1 = new Course();
		c1.setName("语文");
		c1.setScore(99);
		
		Course c2 = new Course();
		c2.setName("数学");
		c2.setScore(100);
		
		list.add(c1);
		list.add(c2);
		
		s.setCourse(list);
		
		String str = JsonUtil.toJson(s);
		System.out.println(str);
		
	}

}

