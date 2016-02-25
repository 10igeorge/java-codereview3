import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String stylist;

  public Stylist(String stylist){
    this.stylist = stylist;
    this.id = id;
  }

  public String getStylist(){
    return stylist;
  }

  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylist().equals(newStylist.getStylist()) &&
        this.getId() == newStylist.getId();
    }
  }

  public static List<Stylist> all(){
    String sql = "SELECT id, stylist FROM stylists";
    try (Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stylist) VALUES (:stylist)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stylist", this.stylist)
      .executeUpdate()
      .getKey();
    }
  }
}
