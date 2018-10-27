package pl.stqa.pdt.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    for (String l : languages){
      System.out.println("Chcę nauczyć się " + l);
    }


    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

    /*
    for (int i = 0; i < languages2.size(); i++){
      System.out.println("Chcę nauczyć się " + languages2.get(i));
    }
    */
    for (String l : languages2){
      System.out.println("Chcę uczyć się języka " + l);
    }
  }
}
