package org.editor.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BadCredentialsResponse {
    private String error = "Bad Credentials.";
}
