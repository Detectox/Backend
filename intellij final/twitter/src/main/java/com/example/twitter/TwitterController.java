package com.example.twitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/twitter")
public class TwitterController {
    private final TwitterService twitterService;
    private final TwitterUserRepository twitterUserRepository;
    private final ApplicationContext applicationContext;
    String userInfoJson;
    TwitterUser existingUser;
    TwitterUser user;
    ObjectMapper jsonMapper;
    TwitterUserResponse response;
    String username_save;


    public TwitterController(TwitterService twitterService, TwitterUserRepository twitterUserRepository, ApplicationContext applicationContext) {
        this.twitterService = twitterService;
        this.twitterUserRepository = twitterUserRepository;
        this.applicationContext = applicationContext;
    }


    // Twitter 정보 조회 후, DB에 저장하고 화면에 반환
    @PostMapping("/user")
    public String getUser(@RequestParam("username") String username, Model model, HttpSession session) throws Exception {
        // Twitter API로 사용자 정보 가져오기
        System.out.println(username);
        System.out.println("프론트로부터 데이터 받기 성공");

        session.setAttribute("username_save", username);
        userInfoJson = twitterService.getUserInfo(username);


        // JSON을 TwitterUser 객체로 변환
        jsonMapper = new ObjectMapper();
        response = jsonMapper.readValue(userInfoJson, TwitterUserResponse.class);
        user = response.getData();

        existingUser = twitterUserRepository.getTwitterUser(user.getUsername());

        if (existingUser == null) {
            // DB에 해당 username이 없으면 새로운 사용자 정보 추가
            twitterUserRepository.saveTwitterUser(user);
            System.out.println("User added to the database.");
        } else {
            // DB에 해당 계정에 대한 레코드가 있으면 사용자 정보 업데이트
            twitterUserRepository.updateTwitterUser(user);
            System.out.println("User already exists in the database. User updated to the database.");
        }

        // 모델에 정보를 추가하고 화면에 반환
        model.addAttribute("user", user);
        return "userProfile";  // userProfile.html로 화면을 리턴
    }

    // declareUser 메서드 수정
//    @PostMapping("/declare")
//    public String declareUser(HttpSession session, Model model) throws Exception {
//        String username = (String) session.getAttribute("username_save");
//
//        if (username == null) {
//            System.out.println("Username is null!");
//            return "errorPage";  // errorPage.html로 화면을 리턴 (예시)
//        }
//
//        userInfoJson = twitterService.getUserInfo(username);
//
//        jsonMapper = new ObjectMapper();
//        response = jsonMapper.readValue(userInfoJson, TwitterUserResponse.class);
//        user = response.getData();
//
//        existingUser = twitterUserRepository.getTwitterUser(user.getUsername());
//        System.out.println(user.getUsername());
//
//        if (existingUser != null) {
//            // declare_num 값 증가
//            int newDeclareNum = existingUser.getDeclare_num() + 1;
//            System.out.println("Before set: " + existingUser.getDeclare_num());
//
//            // declare_num 값을 업데이트
//            existingUser.setDeclare_num(newDeclareNum);
//            System.out.println("Before update: " + existingUser.getDeclare_num());
//
//            // DB에서 사용자 정보를 업데이트
//            twitterUserRepository.updateTwitterUser(existingUser);
//
//            // DB에서 새롭게 업데이트된 사용자 정보 다시 불러오기
//            existingUser = twitterUserRepository.getTwitterUser(existingUser.getUsername());
//            System.out.println("After update: " + existingUser.getDeclare_num());
//
//            System.out.println("User declared. Updated declare_num in the database.");
//        } else {
//            System.out.println("User not found in the database.");
//        }
//
//        // 모델에 정보를 추가하고 화면에 반환
//        model.addAttribute("user", user);
//        return "userProfile";  // userProfile.html로 화면을 리턴
//    }

    @PostMapping("/declare")
    public String declareUser(HttpSession session, Model model) throws Exception {
        String username = (String) session.getAttribute("username_save");

        if (username == null) {
            System.out.println("Username is null!");
            return "errorPage";  // errorPage.html로 화면을 리턴 (예시)
        }

        userInfoJson = twitterService.getUserInfo(username);

        jsonMapper = new ObjectMapper();
        response = jsonMapper.readValue(userInfoJson, TwitterUserResponse.class);
        user = response.getData();

        existingUser = twitterUserRepository.getTwitterUser(user.getUsername());
        System.out.println(user.getUsername());

        if (existingUser != null) {
            int newDeclareNum = existingUser.getDeclare_num() + 1;
            System.out.println("Before set: " + existingUser.getDeclare_num());

            existingUser.setDeclare_num(newDeclareNum);
            System.out.println("Before update: " + existingUser.getDeclare_num());

            // DB에서 사용자 정보를 업데이트
            twitterUserRepository.updateTwitterUser(existingUser);

            // 업데이트 후 바로 DB에서 새로운 정보를 가져오는 대신 이미 업데이트된 값 사용
            System.out.println("After update: " + existingUser.getDeclare_num());

            System.out.println("User declared. Updated declare_num in the database.");
        } else {
            System.out.println("User not found in the database.");
        }

        // 모델에 정보를 추가하고 화면에 반환
        model.addAttribute("user", user);
        return "userProfile";  // userProfile.html로 화면을 리턴
    }



}

