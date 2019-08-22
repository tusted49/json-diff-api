package com.weas.jsondiffapi.gateways.http.controllers;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.gateways.http.resources.request.DataRequest;
import com.weas.jsondiffapi.usecases.CreateData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/diff")
@RequiredArgsConstructor
public class DiffController {

    private final CreateData createData;

    @PostMapping("/{id}/left")
    public ResponseEntity addLeft(@PathVariable final Long id, @RequestBody final DataRequest dataRequest) {

        return ResponseEntity.ok(createData.execute(Data.builder()
                .id(id)
                .left(dataRequest.getValue())
                .build()));
    }

    @PostMapping("/{id}/right")
    public ResponseEntity addRight(@PathVariable final Long id, @RequestBody final DataRequest dataRequest) {

        return ResponseEntity.ok(createData.execute(Data.builder()
                .id(id)
                .right(dataRequest.getValue())
                .build()));
    }

}
