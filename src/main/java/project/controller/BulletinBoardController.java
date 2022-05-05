package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.service.BulletinBoardService;

@RestController
@RequestMapping("/api")
public class BulletinBoardController{
    
    @Autowired
    private BulletinBoardService bulletinBoardService;
    
    @GetMapping("/bulletin-board/pages")
    public ResponseEntity<?> getBulletinBoardPage(@RequestParam("nowPage") int nowPage, @RequestParam("countPerPage") int countPerPage){
        return ResponseEntity.ok().body(bulletinBoardService.getBulletinBoardPage(nowPage, countPerPage));
    }
}