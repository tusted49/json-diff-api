package com.weas.jsondiffapi.gateways.http.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataResponse {

  private String type;

  private String result;
}
