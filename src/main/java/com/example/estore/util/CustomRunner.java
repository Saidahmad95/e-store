package com.example.estore.util;

import com.example.estore.entities.PayTypeClass;
import com.example.estore.enums.PayType;
import com.example.estore.repos.PayTypeClassRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class CustomRunner implements ApplicationRunner {

  private final PayTypeClassRepo payTypeClassRepo;
  @Override
  public void run(ApplicationArguments args) throws Exception {

    for (PayType payType : PayType.values()) {
      payTypeClassRepo.save(PayTypeClass.builder().payType(payType).build());
    }
  }
}
