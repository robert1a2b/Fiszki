package com.RobertMatysProject.Fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class WordRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    String temp;


    public List<Word> getWord() {

        return jdbcTemplate.query("SELECT * FROM WORDS"
                , BeanPropertyRowMapper.newInstance(Word.class));

    }

    public int randomNumber(){
        int size = getWord().size();
        int random = (int) ((Math.random() * (size - 1)) + 1);
        return random;
    }

    public Word getRandom(){

        Word randomWordsSet = new Word();
        List<Word> words = getWord();
       int random = randomNumber();
       randomWordsSet = words.get(random);
       temp = randomWordsSet.pl_word;
       return  randomWordsSet;
    }

    public Word getByWord(String pl_word){
        return jdbcTemplate.queryForObject("SELECT * FROM words where pl_word = ?"
                , BeanPropertyRowMapper.newInstance(Word.class), pl_word);
    }

    public String getTranslationFromPl(String pl, int languageCode){
        Word words;
        String translation = "";
            words = jdbcTemplate.queryForObject("SELECT * FROM words where pl_word = ?"
                , BeanPropertyRowMapper.newInstance(Word.class), pl);

        if (languageCode == 1) translation = words.en_word;
        else if (languageCode == 2) translation = words.de_word;
        else if (languageCode == 3) translation = words.es_word;

        return translation;
    }

    public String getTranslationToPl(String foreignWord, int languageCode){
        Word words;
        String sqlQuery = "";

        if (languageCode == 1) sqlQuery = "SELECT * FROM words where en_word = ?";
        else if (languageCode == 2) sqlQuery = "SELECT * FROM words where de_word = ?";
        else if (languageCode == 3) sqlQuery = "SELECT * FROM words where es_word = ?";

        words = jdbcTemplate.queryForObject(sqlQuery
                , BeanPropertyRowMapper.newInstance(Word.class), foreignWord);

        return words.pl_word;
    }

    public Map<String,String> getRandomPlForeignSet(int languageCode) throws InterruptedException {
        Map<String, String> plForeignMap = new HashMap<>();

        List<Word> words;
        words = jdbcTemplate.query("SELECT * FROM words"
                , BeanPropertyRowMapper.newInstance(Word.class));

        Collections.shuffle(words);

        if (languageCode == 1) {
            for(Word word : words){
                plForeignMap.put(word.pl_word, word.en_word);
            }
        }

        else if (languageCode == 2)  {
            for(Word word : words){
                plForeignMap.put(word.pl_word, word.de_word);
            }
        }

        else if (languageCode == 3)  {
            for(Word word : words){
                plForeignMap.put(word.pl_word, word.es_word);
            }
        }
        return plForeignMap;
    }

    public int save(List<Word> words){
        words.forEach(word -> jdbcTemplate
                .update("INSERT INTO words(pl_word, en_word, de_word, es_word) VALUES (?, ?, ?, ?)",
                        word.getPl_word(), word.getEn_word(), word.getDe_word(), word.getEs_word()));
        return 1;
    }

    public int delete(String pl_word){
        jdbcTemplate.update("DELETE * FROM words WHERE pl_word = ?", pl_word);
        return 1;
    }
}
