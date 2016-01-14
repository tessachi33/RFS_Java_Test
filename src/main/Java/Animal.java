import java.util.Map;
import java.util.HashMap;
import java.io.Console;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class Animal {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal.vtl");

      String animalOne = request.queryParams("animalOne");

      String animal = animal(animalOne);

      model.put("animalOne", animalOne);
      model.put("animal", animal(animalOne));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

    public static String animal(String animalOne)  {

    String animal1 = animalOne.toLowerCase();

    Map<String, String> dogHash = new HashMap<String, String>();
    Map<String, String> catHash = new HashMap<String, String>();
    Map<String, String> bearHash = new HashMap<String, String>();

    dogHash.put("dog", "Bark.");

    catHash.put("cat", "Meow");

    bearHash.put("bear", "RUN, no time for sounds, there is a bear behind you!");

    if(animal1.equals("dog")){

      return dogHash.get(animal1);

    } else if(animal1.equals("cat")){

      return catHash.get(animal1);

    } else if (animal1.equals("bear")){

      return bearHash.get(animal1);

    }else{
      return ("You didn't choose Dog, Cat or Bear, did you?");
    }

  }
}
