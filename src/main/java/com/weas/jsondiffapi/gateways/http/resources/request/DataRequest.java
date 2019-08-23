package com.weas.jsondiffapi.gateways.http.resources.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRequest {

  /**
   * Property used to obtain JSON value
   */
  private String value;


}
