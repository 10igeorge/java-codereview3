import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void getStylist_returnsNameOfStylist_true() {
    Stylist newStylist = new Stylist("Mary");
    assertTrue(newStylist.getStylist().equals("Mary"));
  }

  @Test
  public void getId_returnsIdOfStylist_true(){
    Stylist newStylist = new Stylist("Mary");
    assertEquals(newStylist.getId(), 0);
  }

  @Test
  public void all_initalizesAsEmpty(){
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist newStylist = new Stylist("Mary");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Mary");
    Stylist secondStylist = new Stylist("Mary");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist newStylist = new Stylist("Mary");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(newStylist.equals(savedStylist));
  }

  @Test
    public void getClients_retrievesAllClientsFromDatabase_ClientList() {
      Stylist newStylist = new Stylist("Mary");
      newStylist.save();
      Client firstClient = new Client("Kieran", newStylist.getId());
      firstClient.save();
      Client secondClient = new Client("Evan", newStylist.getId());
      secondClient.save();
      Client[] clients = new Client[] { firstClient, secondClient };
      assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
