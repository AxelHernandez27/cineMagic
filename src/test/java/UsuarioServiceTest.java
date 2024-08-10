
import com.example.cineMagic.model.Usuario;
import com.example.cineMagic.repository.UsuarioRepository;
import com.example.cineMagic.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class UsuarioServiceTest {
@Mock
private UsuarioRepository usuarioRepository;

@InjectMocks
private UsuarioService usuarioService;


@BeforeEach
void setUp(){
    MockitoAnnotations.openMocks(this);
}


@Test
void testGetAllUsuarios(){

    Usuario usuario1 = new Usuario();
    Usuario usuario2 = new Usuario();
    when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1,usuario2));

    List<Usuario> usuarios =usuarioService.getAllUsuarios();

    assertEquals(2, usuarios.size());
    verify(usuarioRepository, times(1)).findAll();
}

  @Test
    void testSaveUsuario() {

        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario savedUsuario = usuarioService.saveUsuario(usuario);

        assertNotNull(savedUsuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testGetUsuarioById() {

        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario foundUsuario = usuarioService.getUsuarioById(1L);

        assertNotNull(foundUsuario);
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUsuarioByEmail() {

        Usuario usuario = new Usuario();
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> foundUsuario = usuarioService.getUsuarioByEmail("test@example.com");

        assertTrue(foundUsuario.isPresent());
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testDeleteUsuario() {
        // Act
        usuarioService.deleteUsuario(1L);

        // Assert
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

}
