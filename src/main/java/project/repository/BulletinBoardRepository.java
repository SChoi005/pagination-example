package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entity.BulletinBoard;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long>{
    List<BulletinBoard> findByIdBetween(long start, long end);
}