package com.kernel360.hackerthon.studyup.study.service;

import com.kernel360.hackerthon.studyup.member.entity.Bookmark;
import com.kernel360.hackerthon.studyup.member.entity.Member;
import com.kernel360.hackerthon.studyup.member.repository.MemberRepository;
import com.kernel360.hackerthon.studyup.study.dto.StudyDTO;
import com.kernel360.hackerthon.studyup.study.dto.StudyGroupDetailDTO;
import com.kernel360.hackerthon.studyup.study.dto.StudyMemberDTO;
import com.kernel360.hackerthon.studyup.study.entity.Study;
import com.kernel360.hackerthon.studyup.study.entity.StudyMember;
import com.kernel360.hackerthon.studyup.study.entity.StudyMemberId;
import com.kernel360.hackerthon.studyup.study.entity.StudySchedule;
import com.kernel360.hackerthon.studyup.study.exception.StudyNotFoundException;
import com.kernel360.hackerthon.studyup.study.repository.StudyMemberRepository;
import com.kernel360.hackerthon.studyup.study.repository.StudyRepository;
import com.kernel360.hackerthon.studyup.study.repository.StudyReviewRepository;
import com.kernel360.hackerthon.studyup.study.repository.StudyScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudyServiceImpl implements StudyService {


    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final MemberRepository memberRepository;
    private final StudyReviewRepository studyReviewRepository;
    private final StudyScheduleRepository studyScheduleRepository;


    @Override
    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    @Override
    public StudyGroupDetailDTO getStudyById(BigInteger studyId) {
        // id로 Study를 조회
        Optional<Study> optionalStudy = studyRepository.findById(studyId);


        // StudyGroupDetailDTO에 저장한다.
        if (optionalStudy.isPresent()) {
            Study study = optionalStudy.get();

            List<BigInteger> memberIds = studyMemberRepository.findStudyMembersByStudyMemberId(studyId);
            //List<Member> studyMembers = memberRepository.findByIdIn(memberIds);
            List<StudyMemberDTO> studyMembers = memberRepository.findDTOByMemberIds(memberIds);


            // StudyGroupDetailDTO 생성
            StudyGroupDetailDTO studyGroupDetailDTO = StudyGroupDetailDTO.builder()
                    .studyId(study.getStudyId())
                    .studyTitle(study.getStudyTitle())
                    .summary(study.getSummary())
                    .content(study.getContent())
                    .memberNum(study.getMemberNum())
                    .category(study.getCategory())
                    .views(study.getViews())
                    .studyStatus(study.getStudyStatus())
                    .deadline(study.getDeadline())
                    .startDate(study.getStartDate())
                    .endDate(study.getEndDate())
                    .createdAt(study.getCreatedAt())
                    .modifiedAt(study.getModifiedAt())
                    .deletedAt(study.getDeletedAt())
                    .studyMembers(studyMembers)
                    .build();
        }
        return null;
    }


    @Override
    public int createStudy(Study study) {
        return 0;
    }

    // 나중에 업데이트된 건수로 int 표현하기
    // 1: 업데이트 반영 성공
    // 0: 업데이트 반영 실패
    @Override
    public int updateStudy(Long studyId, StudyDTO studyDTO) {
        Optional<Study> optionalStudy = studyRepository.findById(BigInteger.valueOf(studyId));

        // 수정은 제목 + content만 바꿀 수 있다.
        if (optionalStudy.isPresent()) {
            Study study = optionalStudy.get();
            study.setStudyTitle(studyDTO.getStudyTitle());
            study.setContent(studyDTO.getContent());

            studyRepository.save(study);
        }

        return optionalStudy.isPresent() ? 1 : 0;
    }

    @Override
    public int deleteStudy(Long id) {
        return 0;
    }

    @Override
    public List<StudyMember> getAllStudyMembers() {
        return null;
    }

    @Override
    public List<StudySchedule> getAllStudySchedules() {
        return null;
    }

    @Override
    public StudySchedule getStudySchedule(int scheduleId) {
        return null;
    }

    @Override
    public Study getStudyByStudyId(int studyTitle) {
        return null;
    }

    @Override
    public int createStudySchedule(StudySchedule studySchedule) {
        return 0;
    }

    @Override
    public int updateStudySchedule(StudySchedule studySchedule) {
        return 0;
    }

    @Override
    public int deleteStudyScheduleByStudyDate(int studyId, Date date) {
        return 0;
    }

    @Override
    public int createBookmark(Bookmark bookmark) {
        return 0;
    }

    @Override
    public List<Study> getStudyByBookmarkId(int bookmarkId) {
        return null;
    }

    @Override
    public int updateBookmarkByBookmarkId(int bookmarkId) {
        return 0;
    }
}
