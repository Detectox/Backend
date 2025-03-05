package com.example.twitter;

import com.example.twitter.TwitterUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TwitterUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TwitterUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTwitterUser(TwitterUser user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId("default-id-test3");  // 기본 id값 설정
        }

        // PublicMetrics 값을 분리해서 사용
        String sql = "INSERT INTO twitter_user (id, name, username, created_at, description, declare_num, follower, following, tweet, listed, likes, media) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getDescription(),
                user.getDeclare_num(),
                user.getPublicMetrics().getFollower(),  // PublicMetrics 속성
                user.getPublicMetrics().getFollowing(),
                user.getPublicMetrics().getTweet(),
                user.getPublicMetrics().getListed(),
                user.getPublicMetrics().getLikes(),
                user.getPublicMetrics().getMedia()
        );
    }

//    @Transactional
//    public void updateTwitterUser(TwitterUser user) {
//        String sql = "UPDATE twitter_user SET name = ?, created_at = ?, description = ?, declare_num = ?, follower = ?, following = ?, tweet = ?, listed = ?, likes = ?, media = ? WHERE username = ?";
//        jdbcTemplate.update(sql,
//                user.getName(),
//                user.getCreatedAt(),
//                user.getDescription(),
//                user.getDeclare_num(),
//                user.getPublicMetrics().getFollower(),
//                user.getPublicMetrics().getFollowing(),
//                user.getPublicMetrics().getTweet(),
//                user.getPublicMetrics().getListed(),
//                user.getPublicMetrics().getLikes(),
//                user.getPublicMetrics().getMedia(),
//                user.getUsername()
//        );
//        System.out.println("Updating declare_num: " + user.getDeclare_num());
//    }

    @Transactional
    public void updateTwitterUser(TwitterUser user) {
        String sql = "UPDATE twitter_user SET name = ?, created_at = ?, description = ?, declare_num = ?, follower = ?, following = ?, tweet = ?, listed = ?, likes = ?, media = ? WHERE username = ?";
        jdbcTemplate.update(sql,
                user.getName(),
                user.getCreatedAt(),
                user.getDescription(),
                user.getDeclare_num(),
                user.getPublicMetrics().getFollower(),
                user.getPublicMetrics().getFollowing(),
                user.getPublicMetrics().getTweet(),
                user.getPublicMetrics().getListed(),
                user.getPublicMetrics().getLikes(),
                user.getPublicMetrics().getMedia(),
                user.getUsername()
        );
        System.out.println("Updating declare_num: " + user.getDeclare_num());
    }



    public TwitterUser getTwitterUser(String username) {
        String sql = "SELECT * FROM twitter_user WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                // PublicMetrics 객체 생성
                PublicMetrics publicMetrics = new PublicMetrics(
                        rs.getInt("follower"),
                        rs.getInt("following"),
                        rs.getInt("tweet"),
                        rs.getInt("listed"),
                        rs.getInt("likes"),
                        rs.getInt("media")
                );

                // TwitterUser 객체 반환
                return new TwitterUser(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("created_at"),
                        rs.getString("description"),
                        rs.getInt("declare_num"),
                        publicMetrics  // PublicMetrics 객체 전달
                );
            });
        } catch (EmptyResultDataAccessException e) {
            // 사용자 없을 때 처리 로직 추가
            return null;  // 예시: 사용자 없을 때 null 반환
        }
    }
}
