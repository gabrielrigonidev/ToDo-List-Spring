package br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.controller;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.model.Usuario;
import br.edu.fatecpg.tarefa.Sistema_Tarefas_SPRING.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios/")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cadastra um usuario")
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario user) {
        Usuario usuario = usuarioService.registrar(user);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Faz o login no sistema")
    @PostMapping("/login")
    public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario login, HttpSession session) {
        Usuario usuarioLogado = usuarioService.login(login);
        session.setAttribute("usuario", usuarioLogado);
        return ResponseEntity.ok(usuarioLogado);
    }

    @Operation(summary = "Ecenrra a secao ativa")
    @PostMapping("/logout")
    public ResponseEntity<String> deslogarUsuario(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}
