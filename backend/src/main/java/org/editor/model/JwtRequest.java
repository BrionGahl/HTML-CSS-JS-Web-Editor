package org.editor.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtRequest {
    String username;
    String password;
}
