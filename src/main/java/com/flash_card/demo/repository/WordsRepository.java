package com.flash_card.demo.repository;

import java.util.List;

import com.flash_card.demo.entity.Words;

public interface WordsRepository {
    int insertWord(Words words);

    int deleteWord(Integer id);

    int updateDescription(Words words);

    List<Words> selectWords();

    List<Integer> numberOfWordsInEachCategory(Integer userId);

    List<Words> selectUserWords(Integer id);

    List<Words> selectWordsFromUserCategory(Integer id, Integer categoryId);
}