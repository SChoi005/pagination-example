package project.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.entity.BulletinBoard;

@Getter
@Setter
public class Page{
    
    private int startPage;
    
    private int endPage;
    
    private int nowPage;
    
    private Long total;
    
    private int countPerPage;
    
    private List<BulletinBoard> bulletinBoards;
    
}