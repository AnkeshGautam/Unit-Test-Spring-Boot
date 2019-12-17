package com.example.TicTacToe.controllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.TicTacToe.controller.TicController;
import com.example.TicTacToe.model.Tic;
import com.example.TicTacToe.service.TicService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ExtendWith(SpringExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = { TicService.class, TicController.class })
@WebMvcTest(TicController.class)
public class TicControllerTest {

	private Tic t = new Tic("1", "Ankesh");
//	private Optional<Tic> g = Optional.of(t);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicService ticService;

//	@BeforeEach
//	void setup() {
//		Mockito.when(ticService.createTic(t)).thenReturn(t);
//	}

	@Test
	public void createTicTest() throws Exception {
		// execute
//		MvcResult result = 

		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/").content(asJsonString(t)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");
	}

	@Test
	public void getTicTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/{id}", t.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getTiccTest() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		mockMvc.perform(
				MockMvcRequestBuilders.get("/v1/").param("id", t.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(ok);

//		mockMvc.perform(MockMvcRequestBuilders.get("/v1/?id=" + t.getId()).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(ok);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
