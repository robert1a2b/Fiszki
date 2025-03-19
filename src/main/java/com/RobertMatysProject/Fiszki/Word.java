package com.RobertMatysProject.Fiszki;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    public int id;

    public String pl_word;
    public String en_word;
    public String de_word;
    public String es_word;

}

