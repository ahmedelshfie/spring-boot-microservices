package com.maf.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.maf.besthotels.app.BestHotelsServiceApplication;
import com.maf.besthotels.domain.model.BestHotel;
import com.maf.besthotels.repository.BestHotelsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BestHotelsServiceApplication.class)
public class BestHotelRepositoryTest {

	@MockBean
	BestHotelsRepository bestHotelRepo;

	Map<Integer, BestHotel> HotelRepository;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		Stream<String> hotelsStream = Stream.of("1-Hitlon-4-25.659-Swna, Bean Bags , Double Size Bed",
				"2-Four Seasons-4.5-36.2023-Table Lamp, Envelopes");
		HotelRepository = BestHotelsRepository.createBestHotels(hotelsStream);
	}

	@Test
	public void givenValidInputStreamAsBestHotels_ThenGetHotelById() {

		BestHotel testHotel = new BestHotel(1, "Hitlon", 4.0, 25.66, "Swna, Bean Bags , Double Size Bed");
		when(bestHotelRepo.findById(1L)).thenReturn(testHotel);

		assertThat(HotelRepository.get(1)).isEqualToComparingFieldByField(bestHotelRepo.findById(1L));
	}

	@Test
	public void givenValidInputStreamAsBestHotels_ThenCreateBestHotels() {
		BestHotel testHotel = new BestHotel(1, "Hitlon", 4.0, 25.66, "Swna, Bean Bags , Double Size Bed");
		assertThat(HotelRepository.get(1)).isEqualToComparingFieldByField(testHotel);
	}

	@Test
	public void givenValidInputStreamAsBestHotels_ThenFindAllBestHotels() {
		Collection<BestHotel> hotels = new HashMap<Integer, BestHotel>() {
			{
				put(1, new BestHotel(1, "Hitlon", 4.0, 25.659, "Swna, Bean Bags , Double Size Bed"));
				put(2, new BestHotel(2, "Four Seasons", 4.5, 36.2023, "Table Lamp, Envelopes"));
			}
		}.values();

		when(bestHotelRepo.findAll()).thenReturn(hotels);
		assertThat(HotelRepository.values().containsAll(hotels));

	}

}
