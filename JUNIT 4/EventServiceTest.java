package com.tricon.EventConfigurationService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tricon.EventConfigurationService.model.EventConfiguration;
import com.tricon.EventConfigurationService.model.Filter;
import com.tricon.EventConfigurationService.repository.TenantConfigurationRepo;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

	static final EventConfiguration ECONFIG = new EventConfiguration("5daf0b35360079705b99555f", "google",
			"button click", "click", true, new ArrayList<Filter>());

	@InjectMocks
	private EventService eventService;
	@Mock
	private MongoTemplate mongoTemplate;
	@Mock
	private TenantConfigurationRepo tenantConfigRepo;

	@Before
	public void setup() {
		Mockito.when(tenantConfigRepo.save(ECONFIG)).thenReturn(ECONFIG);

		List<EventConfiguration> l = new ArrayList<EventConfiguration>();
		l.add(ECONFIG);

		Query query = new Query();
		query.addCriteria(Criteria.where("tenantId").is(ECONFIG.getTenantId()));
		Mockito.when(mongoTemplate.find(query, EventConfiguration.class)).thenReturn(l);

	}

	@Test
	public void createClientConfigurationTest() throws Exception {
		assertEquals("Configuration Added to tenantId: " + ECONFIG.getTenantId(),
				eventService.createClientConfiguration(ECONFIG, ECONFIG.getTenantId()));

		assertEquals("Incorrect Tenant ID", eventService.createClientConfiguration(ECONFIG, "facebook"));
	}

	@Test
	public void getClientConfigurationTest() throws Exception {
		List<EventConfiguration> l = new ArrayList<EventConfiguration>();
		l.add(ECONFIG);
		assertEquals(l, eventService.getClientConfiguration(ECONFIG.getTenantId()));
	}

	@Test
	public void deleteClientConfigurationTest() throws Exception {

		//assertEquals("Configuration Deleted", eventService.deleteClientConfiguration(ECONFIG.getId()));

		assertEquals("Configuration ID Doesn't Exists", eventService.deleteClientConfiguration("alaska"));
	}

}
