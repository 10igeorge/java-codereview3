import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylist = request.queryParams("stylist");
      Stylist newStylist = new Stylist(stylist);
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    // get("/stylists/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    //   List<Client> clients = stylist.getClients();
    //   model.put("clients", clients);
    //   model.put("stylist", stylist);
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView (model, layout);
    // }, new VelocityTemplateEngine());
  }
}
