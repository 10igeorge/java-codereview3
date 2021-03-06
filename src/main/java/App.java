import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

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

    post("/delete/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id =  Integer.parseInt(request.params(":id"));
      Stylist.delete(id);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      List<Client> clients = stylist.getClients();
      model.put("client", Client.all());
      model.put("clients", clients);
      model.put("stylist", stylist);
      model.put("template", "templates/clientlist.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/newclient", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String newClient = request.queryParams("newClient");
      Client client = new Client(newClient, stylist.getId());
      client.save();
      List<Client> clients = stylist.getClients();
      model.put("clients", clients);
      model.put("stylist", stylist);
      model.put("template", "templates/clientlist.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      Stylist stylist = Stylist.find(client.getStylistId());
      Client.delete(client.getId());
      List<Client> clients = stylist.getClients();
      model.put("stylist", stylist);
      model.put("clients", clients);
      model.put("template", "templates/clientlist.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
  }
}
