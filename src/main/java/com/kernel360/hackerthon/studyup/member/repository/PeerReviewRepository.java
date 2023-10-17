package com.kernel360.hackerthon.studyup.member.repository;

import com.kernel360.hackerthon.studyup.member.entity.Bookmark;
import com.kernel360.hackerthon.studyup.member.entity.PeerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeerReviewRepository extends JpaRepository<PeerReview, BigInteger> {
    public List<PeerReview> findAll();

    public Optional<PeerReview> findById(BigInteger id);

    public PeerReview save(PeerReview peerReview);

    public void delete(PeerReview peerReview);
}
