package project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dto.Page;
import project.entity.BulletinBoard;
import project.repository.BulletinBoardRepository;

@Service
public class BulletinBoardService{
    
    @Autowired
    private BulletinBoardRepository bulletinBoardRepository;
    
    public Page getBulletinBoardPage(int nowPage, int countPerPage){
        
        long totalBulletinBoards = bulletinBoardRepository.count();
        
        if(totalBulletinBoards !=0){
            return makePage(nowPage, countPerPage, totalBulletinBoards);
        }else
            return null;
    }
    
    Page makePage(int nowPage, int countPerPage, long totalBulletinBoards){
        
        Page page = new Page();
        int totalPages = getTotalPages(countPerPage, totalBulletinBoards);
        
        if(totalPages < nowPage)
            return null;
        else{
            page.setStartPage(1);
            page.setEndPage(totalPages);
            page.setNowPage(nowPage);
            page.setTotal(totalBulletinBoards);
            page.setCountPerPage(countPerPage);
            page.setBulletinBoards(getBulletinBoardsOfPage(nowPage, countPerPage, totalBulletinBoards));
            return page;
        }
    }
    
    int getTotalPages(int countPerPage, long totalBulletinBoards){
        return (int)Math.ceil((double)totalBulletinBoards/countPerPage);
    }
    
    List<BulletinBoard> getBulletinBoardsOfPage(int nowPage, int countPerPage, long totalBulletinBoards){
        
        List<BulletinBoard> bulletinBoards = bulletinBoardRepository.findAll();
        
        long endIndex = (long)nowPage*countPerPage;
        if(endIndex > totalBulletinBoards)
            return bulletinBoards.subList(nowPage*countPerPage-countPerPage, (int)totalBulletinBoards);
        else
            return bulletinBoards.subList(nowPage*countPerPage-countPerPage, (int)endIndex);
    }
    
}