package com.usuario.serviceusuario.infrastructure.out.tokenAdapter;


import com.usuario.serviceusuario.domain.spi.token.IToken;
import com.usuario.serviceusuario.infrastructure.exeption.NoDataFoundException;
import com.usuario.serviceusuario.infrastructure.security.TokenUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokenAdapter implements IToken {

    @Override
    public String getBearerToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @Override
    public String getMail(String token) {
        if (token == (null)) throw new NoDataFoundException();
        return TokenUtils.getMail(token.replace("Bearer ", ""));
    }

    @Override
    public Long getUserAuthenticatedId(String token) {
        if (token == (null)) throw new NoDataFoundException();
        return TokenUtils.getUserAuthenticatedId(token.replace("Bearer", ""));
    }

    @Override
    public String getUserAuthenticatedRole(String token) {
        if (token == (null)) throw new NoDataFoundException();
        return TokenUtils.getUserAuthenticatedRol(token.replace("Bearer ", ""));
    }
}
