package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.repository.BulletinBoardRepository;

@Service
public class BulletinBoardService{
    
    @Autowired
    private BulletinBoardRepository bulletinBoardRepository;
    
}