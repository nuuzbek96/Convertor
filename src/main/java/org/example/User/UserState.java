package org.example.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONPropertyIgnore;

public enum UserState {
    START,
    FROM_SOM,
    CHOOSE_CURRENY,
    ENTER_AMOUNT,
    END_FROM
}
