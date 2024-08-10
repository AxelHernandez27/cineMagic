

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.cineMagic.model.Boleto;
import com.example.cineMagic.repository.BoletoRepository;
import com.example.cineMagic.service.BoletoService;


public class BoletoServiceTest {

    @Mock
    private BoletoRepository boletoRepository;

    @InjectMocks
    private BoletoService boletoService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBoletos(){
        
        Boleto boleto1 = new Boleto();
        Boleto boleto2 = new Boleto();
        List<Boleto> boletos = Arrays.asList(boleto1,boleto2);
        when(boletoRepository.findAll()).thenReturn(boletos);

        List<Boleto> result = boletoService.getAllBoletos();

        assertEquals(2, result.size());
        verify(boletoRepository,times(1)).findAll();

    }

    @Test
    public void testSaveBoleto() {

        Boleto boleto = new Boleto();
        when(boletoRepository.save(boleto)).thenReturn(boleto);

        Boleto result = boletoService.saveBoleto(boleto);

        assertNotNull(result);
        verify(boletoRepository, times(1)).save(boleto);
    }


    @Test
    public void testGetBoletoById() {
        
        Boleto boleto = new Boleto();
        when(boletoRepository.findById(1L)).thenReturn(Optional.of(boleto));

        Boleto result = boletoService.getBoletoById(1L);

        assertNotNull(result);
        verify(boletoRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBoleto() {

        boletoService.deleteBoleto(1L);

        verify(boletoRepository, times(1)).deleteById(1L);
    }

}
