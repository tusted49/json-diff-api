package com.weas.jsondiffapi.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.weas.jsondiffapi.domains.Data;
import com.weas.jsondiffapi.exceptions.NotFoundException;
import com.weas.jsondiffapi.gateways.DataRepository;
import com.weas.jsondiffapi.gateways.http.resources.response.DataResponse;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiffDataTest {

  @InjectMocks
  private DiffData diffData;

  @Mock
  private DataRepository dataRepository;

  private Data data;

  @Before
  public void init() {
    data = new Data();
  }

  @Test
  public void shouldReturnEqual() throws NotFoundException {
    data.setId(1L);
    data.setLeft("Test Content");
    data.setRight("Test Content");

    when(dataRepository.findById(data.getId())).thenReturn(Optional.ofNullable(data));

    String type = diffData.diff(data.getId()).getType();
    assertEquals(type, "EQUAL");
  }

  @Test
  public void shouldReturnDifferentSize() throws NotFoundException {
    data.setId(1L);
    data.setLeft("Test Content Left");
    data.setRight("Test Content");

    when(dataRepository.findById(data.getId())).thenReturn(Optional.ofNullable(data));
    String type = diffData.diff(data.getId()).getType();
    assertEquals(type, "DIFFERENT_SIZE");
  }

  @Test
  public void shouldReturnDifferentContentAndOffsetsWithLenght() throws NotFoundException {
    data.setId(1L);
    data.setLeft("Test Tnetnoc");
    data.setRight("Test Content");

    when(dataRepository.findById(data.getId())).thenReturn(Optional.ofNullable(data));
    DataResponse dataResponse = diffData.diff(data.getId());
    assertEquals(dataResponse.getType(), "DIFFERENT_CONTENT");
    assertEquals(dataResponse.getResult(), "Different Offsets: [5, 6, 7, 9, 10, 11] Length : 6");
  }

  @Test(expected = NotFoundException.class)
  public void shouldNotDiffWithoutData() throws NotFoundException {
    data.setId(1L);
    when(dataRepository.findById(data.getId())).thenReturn(Optional.empty());

    diffData.diff(data.getId());
  }

}
