package cn.edu.scau.cmi.rgbstudy.domain;

/**
 * Created by Mr_Chen on 2018/6/6.
 */

public class Project {
   public int id;
   public String name;
   public String memo;
   public int type_id;

   public Project() {
   }

   public Project(String name, String memo) {
      this.name = name;
      this.memo = memo;
   }

   @Override
   public String toString() {
      return name;
   }
}
