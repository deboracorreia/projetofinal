    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.example.demo.controller;

    /**
     *
     * @author debora
     */

    import com.example.demo.dto.UsuarioDTO;
    import com.example.demo.dto.UsuarioLoginDTO;
    import com.example.demo.model.Usuario;
    import com.example.demo.service.UsuarioService;
    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/usuarios")
    public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @GetMapping("/{id}")
        public UsuarioDTO buscarPorId(@PathVariable Long id) {
            return usuarioService.buscarPorId(id);
        }

        @GetMapping("/atual")
        public UsuarioDTO obterUsuarioAtual (@AuthenticationPrincipal Usuario usuario){
            System.out.println("Usuário autenticado: " + usuario);
            return new UsuarioDTO (usuario);
        }

        @GetMapping
        public List<UsuarioDTO> buscarTodos () {
            List<Usuario>usuarios = usuarioService.buscarTodos();
            return usuarios.stream().map(UsuarioDTO::new).toList();
        }

        @PostMapping
        public UsuarioDTO criar(@RequestBody UsuarioLoginDTO usuarioLoginDto) {
            Usuario usuario = usuarioService.salvar(usuarioLoginDto);
            return new UsuarioDTO(usuario);
        }

        @PutMapping("/{id}")
        public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
            usuarioDTO.setIdusuario(id);
            Usuario usuarioAtualizado = usuarioService.atualizar(id, usuarioDTO);
            return new UsuarioDTO(usuarioAtualizado);
        }

        @DeleteMapping("/{id}")
        public void excluir(@PathVariable Long id) {
            usuarioService.excluir(id);
        }
}

 
