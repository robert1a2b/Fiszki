package com.RobertMatysProject.Fiszki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class WordController<temp> {

    static String temp;
    @Autowired
    WordRepository wordRepository;

    //Set of all words from database in pl, en, de, es
    @GetMapping("/words")
    public List<Word> getWord() {
        return wordRepository.getWord();
    }

    //Set of random word in pl, en, de, es
    @GetMapping("/word")
    public Word getRandomSet(){
        return wordRepository.getRandom() ;
    }

    //Translation of typed pl word to en, de, es
    @GetMapping("/translate/{pl_word}")
    public Word getByPlWord(@PathVariable("pl_word") String pl_word){
    return wordRepository.getByWord(pl_word);
    }

    //Translation of random pl word to en, de, es
    @GetMapping("/pl")
    public String getRandomPl(){
        Word words = wordRepository.getRandom() ;
       temp = words.pl_word;
        return temp;
    }

            @GetMapping("/pl/en")
            public String getTranslationEn(){
               return wordRepository.getTranslationFromPl(temp, 1);
            }

            @GetMapping("/pl/de")
            public String getTranslationDe(){
                return wordRepository.getTranslationFromPl(temp, 2);
            }

            @GetMapping("/pl/es")
            public String getTranslationEs(){
                return wordRepository.getTranslationFromPl(temp, 3);
            }

    //Translation from en to pl
    @GetMapping("/en")
    public String getRandomEn(){
        Word words = wordRepository.getRandom();
        temp = words.en_word;
        return temp;
    }

            @GetMapping("/en/pl")
            public String getTranslationEnToPl(){
                return wordRepository.getTranslationToPl(temp, 1);
            }

    //Translation from de to pl
    @GetMapping("/de")
    public String getRandomDe(){
        Word words = wordRepository.getRandom();
        temp = words.de_word;
        return temp;
    }

            @GetMapping("/de/pl")
            public String getTranslationDeToPl(){
                return wordRepository.getTranslationToPl(temp, 2);
            }

    //Translation from es to pl
    @GetMapping("/es")
    public String getRandomEs(){
        Word words = wordRepository.getRandom();
        temp = words.es_word;
        return temp;
    }

            @GetMapping("/es/pl")
            public String getTranslationEsToPl(){
                return wordRepository.getTranslationToPl(temp, 3);
            }


   //Map of pl-en word pairs to create memo game
    @GetMapping("/memotest/en")
    public Map<String, String> getRandomPlEnSet() throws InterruptedException {
        return wordRepository.getRandomPlForeignSet(1);
    }

    //Map of pl-de word pairs to create memo game
    @GetMapping("/memotest/de")
    public Map<String, String> getRandomPlDeSet() throws InterruptedException {
        return wordRepository.getRandomPlForeignSet(2);
    }

    //Map of pl-es word pairs to create memo game
    @GetMapping("/memotest/es")
    public Map<String, String> getRandomPlEsSet() throws InterruptedException {
        return wordRepository.getRandomPlForeignSet(3);
    }

    @PostMapping("/words")
    public int add(@RequestBody List<Word> words){
        return wordRepository.save(words);
    }

    @DeleteMapping("/words/{pl_word}")
    public int delete(@PathVariable("pl_word") String pl_word){
        return wordRepository.delete(pl_word);
    }

}
