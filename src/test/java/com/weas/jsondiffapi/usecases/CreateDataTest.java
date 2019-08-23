package com.weas.jsondiffapi.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.gateways.DataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateDataTest {

  @InjectMocks
  private CreateData createData;

  @Mock
  private DataRepository dataRepository;

  private Data data;

  @Before
  public void init() {
    data = new Data();
  }

  @Test
  public void shouldCreateDataWithLeftContent() {
    data.setId(1L);
    data.setLeft("Content");

    createData.execute(data);

    verify(dataRepository).save(any(Data.class));
  }

  @Test
  public void shouldCreateDataWithRightContent() {
    data.setId(1L);
    data.setRight("Content");

    createData.execute(data);

    verify(dataRepository).save(any(Data.class));
  }

}
