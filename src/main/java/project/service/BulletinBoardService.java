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
    
    public Page getBulletinBoardPage(int nowPage, int countPerPage) throws Exception{
        
        long totalBulletinBoards = bulletinBoardRepository.count();
        
        if(totalBulletinBoards !=0){
            return makePage(nowPage, countPerPage, totalBulletinBoards);
        }else
            throw new Exception("Nothing in bulletin board");
    }
    
    Page makePage(int nowPage, int countPerPage, long totalBulletinBoards) throws Exception{
        Page page = new Page();
        int totalPages = getTotalPages(countPerPage, totalBulletinBoards);
        
        if(totalPages < nowPage)
            throw new Exception("There is no that page");
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
        
        long endIndex = (long)nowPage*countPerPage;
        if(endIndex > totalBulletinBoards)
            return bulletinBoardRepository.findByIdBetween(endIndex-countPerPage+1, totalBulletinBoards);
        else
            return bulletinBoardRepository.findByIdBetween(endIndex-countPerPage+1, endIndex);
    }
}