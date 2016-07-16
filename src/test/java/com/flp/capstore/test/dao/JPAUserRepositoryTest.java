package com.flp.capstore.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.flp.capstore.dao.UserDao;
import com.flp.capstore.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAnnotationBeanPostProcessor.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
public class JPAUserRepositoryTest {


	User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserName("badReddy");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetchUser() {
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml")){
			UserDao dao = (UserDao) context.getBean(UserDao.class);
			User resultUser = dao.fetchUser(user.getUserName());
			assertEquals(resultUser.getEmail(), "badreddy@yahoo.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{

		}
	}

}