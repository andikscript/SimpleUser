package com.andikscript.simpleuser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/path")
public class PathTestController {

    // simple path
    @GetMapping(value = "/one/{id}")
    public String getPath(@PathVariable(value = "id") String id) {
        return id;
    }

    // with 2 path variable
    @GetMapping(value = "/two/{id}/{name}")
    public List<String> getTwoPath(@PathVariable(value = "id") String id,
                                   @PathVariable(value = "name") String name) {
        return Arrays.asList(new String[]{id, name});
    }

    // with multiple path variable and handle with map
    @GetMapping(value = "/multiple/{id}/{name}/{old}")
    public String getMultiplePath(@PathVariable Map<String, String> pathMap) {
        if (pathMap.get("id") == null && pathMap.get("name") == null && pathMap.get("old") == null) {
            return "not body request";
        } else {
            return pathMap.get("id") +" "+ pathMap.get("name") +" "+ pathMap.get("old");
        }
    }

    // with path variable but optional
    @GetMapping(value = {"/third","/third/{id}" })
    public String getThirdPath(@PathVariable(value = "id", required = false) String id) {
        if (id == null) {
            return "not path variable";
        } else {
            return "with path variable: " + id;
        }
    }

    // with Optional type for check fill or not fill path variable
    @GetMapping(value = {"/fourth","/fourth/{id}"})
    public String getFourthPath(@PathVariable(value = "id") Optional<String> id) {
        if (id.isPresent()) {
            return "fill with " + id.get();
        } else {
            return "not fill";
        }
    }
}
