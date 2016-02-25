import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_initalizesAsEmpty(){
    assertEquals(Stylist.all().size(), 0);
  }
  
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist firstStylist = new Stylist("Mary");
    Stylist secondStylist = new Stylist("Mary");
    assertTrue(firstStylist.equals(secondStylist));
  }
}
