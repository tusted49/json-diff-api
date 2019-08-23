package com.weas.jsondiffapi.gateways.http.controllers;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.exceptions.NotFoundException;
import com.weas.jsondiffapi.gateways.http.resources.request.DataRequest;
import com.weas.jsondiffapi.gateways.http.resources.response.DataResponse;
import com.weas.jsondiffapi.usecases.CreateData;
import com.weas.jsondiffapi.usecases.DiffData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
@RequiredArgsConstructor
public class DiffController {

  private final CreateData createData;
  private final DiffData diffData;

  @PostMapping("/{id}/left")
  public ResponseEntity addLeft(@PathVariable final Long id,
      @RequestBody final DataRequest dataRequest) {

    return ResponseEntity.ok(createData.execute(Data.builder()
        .id(id)
        .left(dataRequest.getValue())
        .build()));
  }

  @PostMapping("/{id}/right")
  public ResponseEntity addRight(@PathVariable final Long id,
      @RequestBody final DataRequest dataRequest) {

    return ResponseEntity.ok(createData.execute(Data.builder()
        .id(id)
        .right(dataRequest.getValue())
        .build()));
  }

  @GetMapping("/{id}")
  public DataResponse diff(@PathVariable final Long id) throws NotFoundException {
    return diffData.diff(id);
  }

}
