package com.teamproject.trainingrequest.datagenerater;

import com.teamproject.trainingrequest.model.Employee;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

public class DataGenerator {

    /*
    insert into Training_Request (id, employee_Id, description, location, requested_by_first_name, requested_by_last_name, cost) values (10000, 1,
    'spring boot', 'Jamaica' , 'Ceasar', 'Barbosa', 150);
     */

    static String UNAPPROVED = "insert into Training_Request (id, employee_Id, description, location, requested_by_first_name, requested_by_last_name, cost) " +
            "values (%d, %d, '%s', '%s' , '%s', '%s', %s);\n";
    static String APPROVED = "insert into Training_Request (id, employee_Id, description, location, requested_by_first_name, requested_by_last_name, cost,approved_by, " +
            "approved_date) " +
            "values (%d, %d, '%s', '%s' , '%s', '%s', %s, '%s', '%s');\n";

    static String[] classes = {"Spring Boot", "AWS", "Angular","CSS", "HTML"};

    static RestTemplate r = new RestTemplate();

    public static void main(String[] args) throws IOException {
        long currentId = 10000;
        NumberFormat nFormat = NumberFormat.getInstance();
        nFormat.setMaximumFractionDigits(2);
        nFormat.setMinimumFractionDigits(2);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/ceasarbarbosa/Training-Request/training-request/src/main/resources/stuff.txt"))){
            for(int i = 1; i < 10; i++){
                Employee ent = r.getForEntity("http://localhost:8080/employees/" + i, Employee.class).getBody();
                for(int j = 0; j < classes.length; j++){
                    String sql = (j % 2 == 0)?
                            String.format(UNAPPROVED,currentId++,i,classes[j],"Chicago",
                                    ent.getFirstName(), ent.getLastName(), nFormat.format(300 * Math.random())):
                            String.format(APPROVED, currentId++, i, classes[j], "Chicago",
                                    ent.getFirstName(), ent.getLastName(), nFormat.format(300 * Math.random()), "adan", "2018-07-25");
                   bw.write(sql);
                }

            }

        }

    }
}
