package com.automation.test;

import com.automation.body.Payload;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/*







    5. Print no of copies sold by RPA Course

    6. Verify if Sum of all Course prices matches with Purchase Amount*/
public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(Payload.coursePrice());

      //  1. Print No of courses returned by API
      int  coursesNUm =  jsonPath.getInt("courses.size()");
        System.out.println(coursesNUm);

       // 2.Print Purchase Amount

       int totalAmount=jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // 3. Print Title of the first course

         String firstTitle = jsonPath.get("courses[0].title");
        System.out.println(firstTitle);

        // 4. Print All course titles and their respective Prices
          List<String> allTitles = jsonPath.getList("courses.title");

        System.out.println("The titles of all the courses is:"+"\n" + allTitles);
        System.out.println();
        for (int i = 0; i <coursesNUm ; i++) {
       HashMap<String,String> courseTitles = jsonPath.get("courses["+i+"]");
            System.out.println(courseTitles);



            

        }
    }
}
