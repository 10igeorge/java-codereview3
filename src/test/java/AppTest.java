import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567");
      assertThat(pageSource()).contains("Stylists");
  }

  @Test
  public void stylistIsCreated() {
    goTo("http://localhost:4567");
    fill("#stylist").with("Mary");
    submit("#stylistCreate");
    assertThat(pageSource()).contains("Mary");
  }

  @Test
  public void stylistIsDeleted(){
    goTo("http://localhost:4567");
    Stylist newStylist = new Stylist("Mary");
    newStylist.save();
    newStylist.delete(newStylist.getId());
    assertThat((pageSource()).contains("Mary") == false);
  }

  @Test
   public void clientIsCreated() {
     goTo("http://localhost:4567");
     Stylist newStylist = new Stylist("Mary");
     newStylist.save();
     String stylistPage = String.format("http://localhost:4567/stylists/%d", newStylist.getId());
     goTo(stylistPage);
     assertThat(pageSource()).contains("Mary");
     fill("#newClient").with("Evan");
     submit("#clientCreate");
     assertThat(pageSource()).contains("Evan");
   }

   @Test
   public void clientIsDeleted(){
     goTo("http://localhost:4567");
     Stylist newStylist = new Stylist("Mary");
     newStylist.save();
     String stylistPage = String.format("http://localhost:4567/stylists/%d", newStylist.getId());
     goTo(stylistPage);
     assertThat(pageSource()).contains("Mary");
     Client client = new Client("Evan", newStylist.getId());
     client.save();
     client.delete(client.getId());
     assertThat((pageSource()).contains("Evan") == false);
   }
}
