import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_initializesAsEmpty() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Client newClient = new Client("Evan", 1);
    newClient.save();
    assertEquals(Client.all().get(0).getClientName(), "Evan");
  }

  @Test
  public void equals_returnsTrueIfClientNamesAretheSame() {
    Client firstClient = new Client("Evan", 1);
    Client secondClient = new Client("Evan", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_assignsStylistIdToClient() {
    Client newClient = new Client("Blue Ivey", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client newClient = new Client("Kieran", 1);
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertTrue(newClient.equals(savedClient));
  }
}
