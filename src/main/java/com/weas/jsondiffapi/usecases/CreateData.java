package com.weas.jsondiffapi.usecases;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.gateways.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class CreateData {

  private final DataRepository dataRepository;

  public Data execute(final Data data) {

    Assert.notNull(data.getId(), "Id cannot be null");

    Data dataStored =
        dataRepository.findById(data.getId())
            .orElse(Data.builder().id(data.getId()).build());

    return dataRepository.save(Data.builder()
        .id(data.getId())
        .left(isEmpty(data.getLeft()) ? dataStored.getLeft() : data.getLeft())
        .right(isEmpty(data.getRight()) ? dataStored.getRight() : data.getRight())
        .build());
  }

}
