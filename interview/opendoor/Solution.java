package interview.opendoor;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  class Cell {
    int type; //0 number, 1 formula
    int value;
    String formula;
  }
  
  Map<String, Cell> spreadsheet = new HashMap<>();
  //Map<String, List<String>> depend
  
  /**
   *
   **/
  public boolean setCell(String pos, String value) {
    // go through reverse dependencies and update to new value
    Cell newCell = new Cell();
    
    if (value.charAt(0) == '=') {
      newCell.type = 1;
      newCell.value = 0;
      newCell.formula = value;
    }
    else {
      newCell.type = 0;
      newCell.value = Integer.parseInt(value);
      newCell.formula = "";
    }
    
    spreadsheet.put(pos, newCell);
    
    return true;
  }
  
  public int getValue(String pos) {
    Cell cell = spreadsheet.get(pos);
    if (cell == null) {
      return 0;
    }
    if (cell.type == 1) {
      String value = cell.formula.substring(1);
      String[] strs = value.split("\\+");
      Cell cell1 = spreadsheet.get(strs[0]);
      if (cell1 == null) return 0;
      Cell cell2 = spreadsheet.get(strs[1]);
      if (cell2 == null) return 0;
      
      int value1 = 0, value2 = 0;
      
      if (cell1.type == 1) {
        value1 = getValue(strs[0]);
      }
      else {
        value1 = cell1.value;
      }
      
      if (cell2.type == 1) {
        value2 = getValue(strs[1]);
      }
      else {
        value2 = cell2.value;
      }
      
      return value1 + value2;
    }
    else {
      return cell.value;
    }
  }
  
  public static void main(String[] args) {
    Solution sol = new Solution();
    sol.setCell("A1", "5");
    sol.setCell("B1", "10");
    sol.setCell("C2", "=A1+B1");
    sol.setCell("D2", "=C2+B1");
    
    // sol.setCell("AAAAAAAAAAAAAAAAA10000000000000000000", "102391028109283109823091283");
    
    System.out.println(sol.getValue("A1"));
    System.out.println(sol.getValue("B1"));
    System.out.println(sol.getValue("C2"));
    System.out.println(sol.getValue("D2"));
    
    sol.setCell("A1", "22");
    
    System.out.println(sol.getValue("C2"));
  }
}


/* 
Your previous Plain Text content is preserved below:

    A  B  C
1   5  10
2         =A1+B1
3

set_cell("A1", "5")
set_cell("B1", "10")
set_cell("C2", "=A1+B1")

get_value("A1") -> 5
get_value("B1") -> 10
get_value("C2") -> 15


 */