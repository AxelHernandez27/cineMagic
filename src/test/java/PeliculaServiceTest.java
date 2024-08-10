import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.example.cineMagic.model.Pelicula;
import com.example.cineMagic.repository.PeliculaRepository;
import com.example.cineMagic.service.PeliculaService;

public class PeliculaServiceTest {
    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPeliculas() {

        Pelicula pelicula1 = new Pelicula();
        Pelicula pelicula2 = new Pelicula();
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);

        when(peliculaRepository.findAll()).thenReturn(peliculas);

        List<Pelicula> result = peliculaService.getAllPeliculas();
        assertEquals(2, result.size());
        verify(peliculaRepository, times(1)).findAll();
    }

    @Test
    void testSavePelicula() {
        // Datos simulados
        Pelicula pelicula = new Pelicula();
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        // Prueba
        Pelicula result = peliculaService.savePelicula(pelicula);
        assertNotNull(result);
        verify(peliculaRepository, times(1)).save(pelicula);
    }

    @Test
    void testGetPeliculaById() {
        // Datos simulados
        Pelicula pelicula = new Pelicula();
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        // Prueba
        Pelicula result = peliculaService.getPeliculaById(1L);
        assertNotNull(result);
        verify(peliculaRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPeliculaById_NotFound() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.empty());

        Pelicula result = peliculaService.getPeliculaById(1L);
        assertNull(result);
        verify(peliculaRepository, times(1)).findById(1L);
    }

    @Test
    void testDeletePelicula() {
        Long id = 1L;

        peliculaService.deletePelicula(id);
        verify(peliculaRepository, times(1)).deleteById(id);
    }
}


