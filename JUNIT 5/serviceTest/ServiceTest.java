package com.example.TicTacToe.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.TicTacToe.model.Tic;
import com.example.TicTacToe.repository.TicRepo;
import com.example.TicTacToe.service.TicService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceTest {

	private Tic t = new Tic("1", "Ankesh");
	private Optional<Tic> g = Optional.of(t);

	@Mock
	private TicRepo ticRepo;

	@InjectMocks
	private TicService ticService;

	@BeforeEach
	void setup() {
		when(ticRepo.save(t)).thenReturn(t);
		when(ticRepo.findById(t.getId())).thenReturn(g);
	}

	@Test
	public void createTicTest() {
		assertEquals(t, ticService.createTic(t));
	}

	@Test
	public void getTicTest() {
		assertEquals(g, ticService.getTic(t.getId()));
	}

	@Test
	public void deleteTicTest() {
		assertEquals("Deleted", ticService.deleteTic(t.getId()));
	}

}
