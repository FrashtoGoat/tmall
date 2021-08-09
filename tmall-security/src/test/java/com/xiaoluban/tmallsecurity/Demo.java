package com.xiaoluban.tmallsecurity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: txb
 * @Date: 20210121
 */
public class Demo {

    @Test
    public void DeLegatingPasswordEncoder(){
        String idForEncode = "bcrypt";
        Map encoders = new HashMap();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder =
                new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Test
    public void bcryptPasswordEncoder(){
        // Create an encoder with strength 16
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        System.out.println(encoder.matches("myPassword", result));
    }

    @Test
    public void passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = "$2a$10$nql9DzL47J6a1JPVkrUE8O3VN24VqgI5b0oEFvyuRjGiK8alZK2qO";
        result="$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa";
        System.out.println(encoder.matches("123456", result));
    }

    @Test
    public void passwordEncoder2(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234567"));
        System.out.println(encoder.encode("xique666"));
    }
}
