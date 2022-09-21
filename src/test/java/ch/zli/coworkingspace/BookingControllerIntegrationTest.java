package ch.zli.coworkingspace;

import ch.zli.coworkingspace.repository.BookingRepository;
import ch.zli.coworkingspace.repository.MemberRepository;
import ch.zli.coworkingspace.repository.PlaceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.zli.coworkingspace.model.BookingEntity;
import ch.zli.coworkingspace.security.JwtServiceHMAC;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jdk.jfr.Description;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerIntegrationTest {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtServiceHMAC jwtService;
    @Autowired
    private ObjectMapper objectMapper;
    private String accessToken;

    @BeforeEach
    public void init() {
        accessToken = jwtService.createNewJWT(UUID.randomUUID().toString(), "9135f12e-1b66-4ee6-bbae-df37303cc154", "admin", List.of("ADMIN"));
    }

    // GET
    @Test
    @Description("All free Bookings ")
    public void allBookingsShouldBeReturnedFromService() throws Exception {

        val response = mockMvc.perform(get("/bookings").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk()).andDo(print()).andReturn();
        List<BookingEntity> bookings = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(2, bookings.size());
    }

    // POST
    @Test
    @Description("User is created")
    public void bookingShouldBeAdded() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        BookingEntity booking = new BookingEntity();
        booking.setIs_accepted(true);
        Date date = dateFormat.parse("2014-08-14 11:47:52.069");
        long time = date.getTime();
        booking.setStartDate(new Timestamp(time));
        booking.setEndDate(new Timestamp(time));
        booking.setId(UUID.fromString("3ebe0e2d-89fb-4896-8941-f64804aabff1"));
        if (bookingRepository.findById(UUID.fromString("413e2297-b84b-42ef-97ed-16a8a9d1d671")).isEmpty()) {
            throw new Exception("Nothing found");
        }


        booking.setPlaceEntity(placeRepository.findById(UUID.fromString("4be5f5bf-8eb5-44ea-8eb5-a5e807856d09")).get());
        booking.setMemberEntity(memberRepository.findById(UUID.fromString("9135f12e-1b66-4ee6-bbae-df37303cc154")).get());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String req = ow.writeValueAsString(booking);
        val response = mockMvc.perform(MockMvcRequestBuilders.post("/bookings").contentType(MediaType.APPLICATION_JSON).content(req).header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk()).andDo(print()).andReturn();
        BookingEntity bookingEntity = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(bookingEntity.getMemberEntity().getId(), UUID.fromString("9135f12e-1b66-4ee6-bbae-df37303cc154"));
    }


    // PUT
    @Test
    @Description("Testing deletion function")
    public void testPut() throws Exception {
        // configure date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        BookingEntity bookingEntity = new BookingEntity();
        Date date = dateFormat.parse("2014-08-14 11:47:52.069");
        long time = date.getTime();
        bookingEntity.setStartDate(new Timestamp(time));
        bookingEntity.setEndDate(new Timestamp(time));
        bookingEntity.setIs_accepted(true);
        bookingEntity.setId(UUID.fromString("3ebe0e2d-89fb-4896-8941-f64804aabff1"));
        if (placeRepository.findById(UUID.fromString("4be5f5bf-8eb5-44ea-8eb5-a5e807856d09")).isEmpty() || memberRepository.findById(UUID.fromString("9135f12e-1b66-4ee6-bbae-df37303cc154")).isEmpty()) {
            throw new Exception("Data in db couldn't be found");
        }
        bookingEntity.setPlaceEntity(placeRepository.findById(UUID.fromString("4be5f5bf-8eb5-44ea-8eb5-a5e807856d09")).get());
        bookingEntity.setMemberEntity(memberRepository.findById(UUID.fromString("9135f12e-1b66-4ee6-bbae-df37303cc154")).get());
        bookingRepository.save(bookingEntity);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String req = ow.writeValueAsString(bookingEntity);
        val res = mockMvc.perform(get("/bookings/413e2297-b84b-42ef-97ed-16a8a9d1d671").contentType(MediaType.APPLICATION_JSON)
                        .content(req)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    // DELETE
    @Test
    @Description("delete booking by the given id")
    public void bookingShouldBeDeleted() throws Exception {
        BookingEntity booking = new BookingEntity();
        if (bookingRepository.findById(UUID.fromString("413e2297-b84b-42ef-97ed-16a8a9d1d671")).isEmpty()) {
            throw new Exception("Nothing found");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String req = ow.writeValueAsString(booking);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bookings/{id}", UUID.fromString("413e2297-b84b-42ef-97ed-16a8a9d1d671"))

                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}


