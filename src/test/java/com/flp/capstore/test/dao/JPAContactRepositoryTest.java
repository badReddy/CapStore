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

import com.flp.capstore.dao.ContactDao;
import com.flp.capstore.entity.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAnnotationBeanPostProcessor.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
public class JPAContactRepositoryTest {


	Contact contact;

	@Before
	public void setUp() throws Exception {
		contact = new Contact();
		contact.setContactId("2");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetchUser() {
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml")){
			ContactDao dao = (ContactDao) context.getBean(ContactDao.class);
			Contact resultContact = dao.fetchContact(contact.getContactId());
			assertEquals(resultContact.getState(), "AP");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{

		}
	}

}
