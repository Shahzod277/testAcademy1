package uz.jurayev.academy.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SecurityConstant {

    //Api HEMIS
    public static final String GENERATE_TOKEN_URL = "http://ministry.hemis.uz/app/rest/v2/oauth/token";
    public static final String GET_STUDENT_URL = "http://ministry.hemis.uz/app/rest/v2/services/student/get?pinfl=";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Basic Y2xpZW50OnNlY3JldA==";
    public static final String GRANT_TYPE = "password";
    public static final String USERNAME = "railway";
    public static final String PASSWORD = "5pk2zzM5F7c6dHW";

    //Custom token

    public static final Long EXPIRE_AT =172_000_000L ;
    public static final Long REFRESH_TOKEN_EXPIRE_AT = 604_800_800L;
    public static final String TOKEN_HEADER  = "Bearer ";
    public static final String AUTHORITIES = "Auth172_800_000Lorities";
}
