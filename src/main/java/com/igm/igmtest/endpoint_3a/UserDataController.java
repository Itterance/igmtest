package com.igm.igmtest.endpoint_3a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



@RestController
@RequestMapping(path = "api/3a/output_data")
public class UserDataController {

    private static final Logger logger = LoggerFactory.getLogger(UserDataController.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(8);

    @GetMapping
    public List<Map<String, String>> generateUserData() {
        List<Future<Map<String, String>>> futures = new ArrayList<>();

        String[] names = {
                "John Doe",
                "Jane Smith",
                "Robert Johnson",
                "Michael Williams",
                "Sarah Brown",
                "David Jones",
                "Emily Davis",
                "James Miller",
                "Jessica Wilson",
                "William Moore",
                "Elizabeth Taylor",
                "Richard Anderson",
                "Patricia Thomas",
                "Charles Jackson",
                "Linda White",
                "Joseph Harris",
                "Margaret Martin",
                "Thomas Thompson",
                "Susan Garcia",
                "Christopher Martinez"
        };

        String[] emails = {
                "johndoe@example.com",
                "janesmith@example.com",
                "robertjohnson@example.com",
                "michaelwilliams@example.com",
                "sarahbrown@example.com",
                "davidjones@example.com",
                "emilydavis@example.com",
                "jamesmiller@example.com",
                "jessicawilson@example.com",
                "williammoore@example.com",
                "elizabethtaylor@example.com",
                "richardanderson@example.com",
                "patriciathomas@example.com",
                "charlesjackson@example.com",
                "lindawhite@example.com",
                "josephharris@example.com",
                "margaretmartin@example.com",
                "thomasthompson@example.com",
                "susangarcia@example.com",
                "christophermartinez@example.com"
        };

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            futures.add(executorService.submit(() -> {
                Map<String, String> data = new HashMap<>();
                data.put("id", Integer.toString(finalI));
                data.put("name", names[finalI]);
                data.put("email", emails[finalI]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("Error generating User Data structures", e);
                }

                return data;
            }));
        }

        List<Map<String, String>> finalUserData = new ArrayList<>();

        for (Future<Map<String, String>> future : futures) {
            try {
                finalUserData.add(future.get());
            } catch (Exception e) {
                logger.error("Error generating User Data table", e);
            }
        }
        return finalUserData;
    }
}
