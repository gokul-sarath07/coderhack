package com.crio.coderhack.entity;

import com.crio.coderhack.constants.Badges;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private Integer score;
    private List<Badges> badges;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0;
        this.badges = new ArrayList<>();
    }
}
