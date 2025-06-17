package com.example.inventorymanagementsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InventoryManagementSystemApplication  implements CommandLineRunner {

    private  final PasswordEncoder passwordEncoder;

    public InventoryManagementSystemApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        String  pass = passwordEncoder.encode("admin123");
        System.out.println(pass);



    }
}
