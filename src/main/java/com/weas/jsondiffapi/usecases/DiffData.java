package com.weas.jsondiffapi.usecases;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.exceptions.NotFoundException;
import com.weas.jsondiffapi.gateways.DataRepository;
import com.weas.jsondiffapi.gateways.http.resources.response.DataResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class DiffData {

  private static final String EQUAL = "EQUAL";
  private static final String DIFFERENT_SIZE = "DIFFERENT_SIZE";
  private static final String DIFFERENT_CONTENT = "DIFFERENT_CONTENT";

  private final DataRepository dataRepository;

  public DataResponse diff(final Long id) throws NotFoundException {

    Assert.notNull(id, "Id cannot be null");

    final Data dataStored =
        dataRepository
            .findById(id)
            .orElseThrow(NotFoundException::new);

    byte[] leftValue = dataStored.getLeft().getBytes();
    byte[] rightValue = dataStored.getRight().getBytes();

    if (Arrays.equals(leftValue, rightValue)) {
      return DataResponse.builder()
          .type(EQUAL)
          .build();
    }

    if (leftValue.length != rightValue.length) {
      return DataResponse.builder()
          .type(DIFFERENT_SIZE)
          .build();
    }

    List<String> offsets = scanForDifferentOffsets(leftValue, rightValue);

    return DataResponse.builder()
        .type(DIFFERENT_CONTENT)
        .result("Different Offsets: " + Arrays.toString(offsets.toArray()) + " Length : " + offsets.size())
        .build();

  }

  private List<String> scanForDifferentOffsets(final byte[] left, final byte[] right) {
    List<String> offsets = new ArrayList<>();
    for (int i=0; i < right.length; i++) {
      if (left[i] != right[i]) {
        offsets.add(String.valueOf(i));
      }
    }
    return offsets;
  }
}
