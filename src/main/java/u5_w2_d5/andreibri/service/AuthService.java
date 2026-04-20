package u5_w2_d5.andreibri.service;

import org.springframework.stereotype.Service;
import u5_w2_d5.andreibri.config.TokenTools;
import u5_w2_d5.andreibri.entities.Dipendente;
import u5_w2_d5.andreibri.exception.NotFoundException;
import u5_w2_d5.andreibri.exception.UnauthorizedException;
import u5_w2_d5.andreibri.payloads.LoginDTO;

@Service
public class AuthService {
    private final DipendenteService usersService;
    private final TokenTools tokenTools;

    public AuthService(DipendenteService usersService, TokenTools tokenTools) {

        this.usersService = usersService;
        this.tokenTools = tokenTools;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        try {
            Dipendente found = this.usersService.findEntityByEmail(body.email());

            if (found.getPassword().equals(body.password())) {
                return this.tokenTools.generateToken(found);
            } else {
                throw new UnauthorizedException("Credenziali errate");
            }
        } catch (NotFoundException ex) {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}