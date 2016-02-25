import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String stylist;

  public Stylist(String stylist){
    this.stylist = stylist;
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
}
