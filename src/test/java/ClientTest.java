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
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client newClient = new Client("Kieran", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

}
