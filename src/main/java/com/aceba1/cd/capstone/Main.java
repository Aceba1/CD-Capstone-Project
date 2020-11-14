package com.aceba1.cd.capstone;

import com.aceba1.cd.capstone.processor.ProcessorApplication;
import com.aceba1.cd.capstone.search.SearchApplication;
import com.aceba1.cd.capstone.users.UsersApplication;

public class Main {
  public static void main(String[] args) {
    new Thread(() -> UsersApplication.main(args)).start();
    new Thread(() -> SearchApplication.main(args)).start();
    new Thread(() -> ProcessorApplication.main(args)).start();
  }
}
