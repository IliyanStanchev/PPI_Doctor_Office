package security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderService implements PasswordEncoder {

    PasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override
    public String encode( CharSequence rawPassword ) {

        return delegate.encode( rawPassword );
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword ) {

        return delegate.matches( rawPassword, encodedPassword );
    }
}
