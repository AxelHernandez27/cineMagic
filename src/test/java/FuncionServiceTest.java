

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

import com.example.cineMagic.model.Funcion;
import com.example.cineMagic.repository.FuncionRepository;
import com.example.cineMagic.service.FuncionService;

public class FuncionServiceTest {
    @Mock
    private FuncionRepository funcionRepository;

    @InjectMocks
    private FuncionService funcionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllFunciones() {
        
        Funcion funcion1 = new Funcion();
        Funcion funcion2 = new Funcion();
        List<Funcion> funciones = Arrays.asList(funcion1, funcion2);
        when(funcionRepository.findAll()).thenReturn(funciones);

        
        List<Funcion> result = funcionService.getAllFunciones();

        
        assertEquals(2, result.size());
        verify(funcionRepository, times(1)).findAll();
    }

    @Test
    public void testSaveFuncion() {
        
        Funcion funcion = new Funcion();
        when(funcionRepository.save(funcion)).thenReturn(funcion);

        
        Funcion result = funcionService.saveFuncion(funcion);

        assertNotNull(result);
        verify(funcionRepository, times(1)).save(funcion);
    }

    @Test
    public void testGetFuncionById() {
        
        Funcion funcion = new Funcion();
        when(funcionRepository.findById(1L)).thenReturn(Optional.of(funcion));

        Funcion result = funcionService.getFuncionById(1L);

        assertNotNull(result);
        verify(funcionRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteFuncion() {
    
        funcionService.deleteFuncion(1L);

        verify(funcionRepository, times(1)).deleteById(1L);
    }

}
