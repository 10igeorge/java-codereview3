import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String client;
  private int stylist_id;

  public Client (String client, int stylist_id) {
    this.id = id;
    this.client = client;
    this.stylist_id = stylist_id;
  }

  public int getId(){
    return id;
  }

  public int getStylistId(){
    return stylist_id;
  }

  public String getClientName(){
    return client;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName()) &&
        this.getId() == newClient.getId();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, client, stylist_id FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(client, stylist_id) VALUES (:client, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("client", client)
      .addParameter("stylist_id", stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Client find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public static void delete(int id) {
    String sql = "DELETE FROM clients WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
