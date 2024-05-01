package com.flash_card.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flash_card.demo.entity.Words;

@Repository
public class WordsRepositoryImpl implements WordsRepository {

    private final JdbcTemplate jdbcTemplate;

    public WordsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    @Override
    public int insertWord(Words words) {
        String sql = "INSERT INTO words (category_id, word, description, user_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                words.getCategoryId(),
                words.getWord(),
                words.getDescription(),
                words.getUserId());
    };

    @Override
    public int updateDescription(Words words) {
        String sql = "UPDATE words SET description = ?, WHERE word = ?";
        return jdbcTemplate.update(sql,
                words.getDescription(),
                words.getUserId(),
                words.getWord());
    };

    @Override
    public int deleteWord(Integer id) {
        String sql = "DELETE FROM words WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Words> selectWords() {
        String sql = "SELECT * FROM words";
        List<Words> list = this.executeSqlCommand(sql);

        return list;
    }

    @Override
    public List<Words> selectUserWords(Integer id) {
        String sql = "SELECT * FROM words WHERE user_id=" + id;
        List<Words> list = this.executeSqlCommand(sql);

        return list;
    }

    @Override
    public List<Integer> numberOfWordsInEachCategory(Integer userId) {
        String sql = "SELECT COALESCE(COUNT(words.category_id), 0) " +
                "FROM categories LEFT JOIN words " +
                "ON categories.category_id = words.category_id " +
                "WHERE categories.user_id = ? " +
                "GROUP BY categories.category_id";
        List<Integer> counts = jdbcTemplate.queryForList(sql, Integer.class, userId);
        return counts;
    }

    @Override
    public List<Words> selectWordsFromUserCategory(Integer id, Integer categoryId) {
        String sql = "SELECT * FROM words WHERE user_id = ? AND category_id = ?";
        return jdbcTemplate.queryForList(sql, new Object[] { id, categoryId }).stream()
                .map(result -> new Words(
                        (Integer) result.get("id"),
                        (Integer) result.get("category_id"),
                        (String) result.get("word"),
                        (String) result.get("description"),
                        (Integer) result.get("user_id"),
                        (LocalDateTime) result.get("created_at")))
                .collect(Collectors.toList());
    }

    private List<Words> executeSqlCommand(String sql) {
        List<Map<String, Object>> categoryListObject = jdbcTemplate.queryForList(sql);
        List<Words> list = categoryListObject.stream().map(result -> new Words(
                (Integer) result.get("id"),
                (Integer) result.get("category_id"),
                (String) result.get("word"),
                (String) result.get("description"),
                (Integer) result.get("user_id"),
                (LocalDateTime) result.get("created_at"))).collect(Collectors.toList());
        return list;
    }
}
