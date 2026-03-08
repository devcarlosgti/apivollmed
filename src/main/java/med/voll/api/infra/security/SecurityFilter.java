package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        var tokenJWT = recuperarToken(request);
//
//        System.out.println("TOKEN RECEBIDO: " + tokenJWT);
//
//        if (tokenJWT != null) {
//
//            var subject = tokenService.getSubject(tokenJWT);
//            var usuario = repository.findByLogin(subject);
//
//            var authentication =
//                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        try {

            if (tokenJWT != null) {

                var subject = tokenService.getSubject(tokenJWT);
                var usuario = repository.findByLogin(subject);

                var authentication =
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (RuntimeException e) {
            System.out.println("Token inválido ou expirado");
        }

        filterChain.doFilter(request, response);
    }

//    @Autowired
//    private TokenService tokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var tokenJWT = recuperarToken(request);
//
//        filterChain.doFilter(request, response);
//
//        var subject = tokenService.getSubject(tokenJWT);
//        System.out.println(subject);
//
//        filterChain.doFilter(request, response);
//    }
//
//
//    private String recuperarToken(HttpServletRequest request) {
//        var authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null){
//            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
//        }
//        return authorizationHeader.replace("Bearer ", "");
//    }



//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
////        System.out.println("chamando filter!");
//
//        var tokenJWT = recuperarToken(request);
//
//        if (tokenJWT != null) {
//            var subject = tokenService.getSubject(tokenJWT);
//            var usuario = repository.findByLogin(subject);
//
//            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
////            System.out.println("Logado na requisicao");
//
//            //System.out.println(subject);
//        }
//
//        filterChain.doFilter(request, response);
//    }

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

//    private String recuperarToken(HttpServletRequest request) {
//        var authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null) {
//            return authorizationHeader.replace("Bearer ", "");
//            //return null;
//        }
//       //throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
//        return null;
//
//    }
}
