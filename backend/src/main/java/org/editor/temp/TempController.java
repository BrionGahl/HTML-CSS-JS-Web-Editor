package org.editor.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping(value = "/temp/{tempId}")
    public Temp getData(@PathVariable int tempId) {
        Temp temp = new Temp("Test Name", tempId);
        return temp;
    }
}
