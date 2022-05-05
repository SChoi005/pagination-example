package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entity.BulletinBoard;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long>{
    
}