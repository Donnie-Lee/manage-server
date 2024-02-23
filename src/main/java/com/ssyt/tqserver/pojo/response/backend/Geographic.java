package com.ssyt.tqserver.pojo.response.backend;

import lombok.Data;

@Data
public class Geographic {
    private Location province;
    private Location city;
}

record Location(String key, String label) {
}
