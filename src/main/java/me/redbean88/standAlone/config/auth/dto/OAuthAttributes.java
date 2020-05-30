package me.redbean88.standAlone.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import me.redbean88.standAlone.domain.user.Role;
import me.redbean88.standAlone.domain.user.User;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameArributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameArributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameArributeKey = nameArributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //OAuth2User is return Map
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameArributeKey(userNameAttributeName)
                .build();
    }

    //create User Entity in init Sigh in service
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
