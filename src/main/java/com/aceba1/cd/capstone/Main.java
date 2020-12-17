package com.aceba1.cd.capstone;

import com.aceba1.cd.capstone.processor.ProcessorApplication;
import com.aceba1.cd.capstone.search.SearchApplication;
import com.aceba1.cd.capstone.users.UsersApplication;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    new Thread(() -> UsersApplication.main(args)).start();
//    SearchApplication.main(args);
    new Thread(() -> ProcessorApplication.main(args)).start();

    Scanner scanner = new Scanner(System.in);

//    while (true)
//      switch (scanner.next().toLowerCase()) {
//
//      }
  }
}
