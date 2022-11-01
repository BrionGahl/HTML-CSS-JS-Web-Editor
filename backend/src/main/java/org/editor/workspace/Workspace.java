package org.editor.workspace;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("workspace")
public class Workspace {
    @Id
    private String id;

    private String html;
    private String css;
    private String js;
    private String username;
}
