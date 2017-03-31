package bootRest;


import bootRest.models.Account;
import bootRest.models.AccountRepo;
import bootRest.models.GameRecord;
import bootRest.models.GameRecordRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by aaron on 3/30/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class GameRecordRestControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  private MockMvc mockMvc;

  private String userName = "bdussault";

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  private Account account;

  private List<GameRecord> gameRecordList = new ArrayList<>();

  @Autowired
  private GameRecordRepo gameRecordRepo;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private AccountRepo accountRepo;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

    assertNotNull("the JSON message converter must not be null",
            this.mappingJackson2HttpMessageConverter);
  }

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();

    this.gameRecordRepo.deleteAllInBatch();
    this.accountRepo.deleteAllInBatch();

    this.account = accountRepo.save(new Account(userName));
    this.gameRecordList.add(gameRecordRepo.save(new GameRecord(account, "HoN", 101, 789)));
    this.gameRecordList.add(gameRecordRepo.save(new GameRecord(account, "WoW", 120, 987)));
  }

  @Test
  public void userNotFound() throws Exception {
    String url = String.format("/api/%s/gameRecords", "fakeUsername01");

    mockMvc.perform(post(url)
            .content(this.json(new GameRecord()))
            .contentType(contentType))
            .andExpect(status().isNotFound());
  }

  @Test
  public void readSingleGameRecord() throws Exception {
    String url = String.format("/api/%s/gameRecords/%d", userName, gameRecordList.get(0).getId());

    mockMvc.perform(get(url))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.id", is(this.gameRecordList.get(0).getId().intValue())))
            .andExpect(jsonPath("$.gameName", is("HoN")))
            .andExpect(jsonPath("$.score", is(101)))
            .andExpect(jsonPath("$.gameTime", is(789)));
  }

  @Test
  public void readGameRecords() throws Exception {
    String url = String.format("/api/%s/gameRecords", userName);

    mockMvc.perform(get(url))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(this.gameRecordList.get(0).getId().intValue())))
            .andExpect(jsonPath("$[0].gameName", is("HoN")))
            .andExpect(jsonPath("$[0].score", is(101)))
            .andExpect(jsonPath("$[0].gameTime", is(789)))
            .andExpect(jsonPath("$[1].id", is(this.gameRecordList.get(1).getId().intValue())))
            .andExpect(jsonPath("$[1].gameName", is("WoW")))
            .andExpect(jsonPath("$[1].score", is(120)))
            .andExpect(jsonPath("$[1].gameTime", is(987)));
  }

  @Test
  public void createGameRecord() throws Exception {
    String url = String.format("/api/%s/gameRecords", userName);
    String gameRecordJson = json(new GameRecord(
            this.account, "WoW2.0", 99, 180));


    this.mockMvc.perform(post(url)
            .contentType(contentType)
            .content(gameRecordJson))
            .andExpect(status().isCreated());
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(
            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }
}