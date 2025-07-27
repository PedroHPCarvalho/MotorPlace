// Define que essa classe é uma classe de configuração do Spring.
package com.motorplace.back_core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration // Indica ao Spring que essa classe contém configurações que devem ser carregadas na inicialização do contexto
public class SecurityConfig {

    // Define um bean do tipo DefaultSecurityFilterChain, que é o conjunto de filtros de segurança aplicados às requisições HTTP
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF (Cross-Site Request Forgery), que é uma proteção contra falsificação de requisições.
                // Normalmente é desabilitado em APIs REST, pois não utilizam sessões.
                .csrf().disable()

                // Define regras de autorização para requisições HTTP
                .authorizeHttpRequests()

                // Permite que qualquer requisição (independente do endpoint) seja aceita sem autenticação
                .anyRequest().permitAll();

        // Constrói e retorna o filtro de segurança com as configurações definidas acima
        return http.build();
    }
}
