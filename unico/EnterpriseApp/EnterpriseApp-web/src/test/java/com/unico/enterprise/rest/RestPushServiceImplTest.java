/**
 * 
 */
package com.unico.enterprise.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.unico.enterprise.SpringWithJNDIRunner;
import com.unico.enterprise.dao.GCDDao;
import com.unico.enterprise.dao.ParamsDao;
import com.unico.enterprise.domain.Params;

/**
 * @author jsingh
 *
 */
@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class RestPushServiceImplTest {

	@Autowired
	RestPushService restPushService;
	
	@Autowired
	ParamsDao paramsDao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		Collection<Params> paramsList = new ArrayList<Params>();
		paramsList.add( new Params(20, 40));
		paramsList.add( new Params(60, 80));
		paramsList.add( new Params(80, 90));
		doReturn(paramsList).when(paramsDao).getSavedParamsList();
	}

	/**
	 * Test method for {@link com.unico.enterprise.rest.RestPushServiceImpl#push(int, int)}.
	 */
	@Test
	public void testPush() {
		assertEquals("Recieved parameters :20 and 16",restPushService.push(20, 16));
	}

	/**
	 * Test method for {@link com.unico.enterprise.rest.RestPushServiceImpl#getList()}.
	 */
	@Test
	public void testGetList() {
		List<Integer> paramList = restPushService.getList();
		assertEquals(new Integer(20), paramList.get(0));
		assertEquals(new Integer(40), paramList.get(1));
		assertEquals(new Integer(60), paramList.get(2));
		assertEquals(new Integer(80), paramList.get(3));
		assertEquals(new Integer(80), paramList.get(4));
		assertEquals(new Integer(90), paramList.get(5));
		
	}

}
