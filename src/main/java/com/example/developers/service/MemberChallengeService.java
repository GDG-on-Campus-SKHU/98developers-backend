package com.example.developers.service;

import com.example.developers.domain.Member;
import com.example.developers.domain.MemberChallenge;
import com.example.developers.repository.MemberChallengeRepository;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberChallengeService {
    @Value("${bucket.name}") // application.yml에 써둔 bucket 이름
    private String bucketName;

    private final Storage storage;

    private final MemberChallengeRepository memberChallengeRepository;

    // 회원 정보 수정
    public void updateMemberChallengeInfo(
            Member member,
            MultipartFile image,
            Long challengeId
    ) throws IOException {
//        MemberChallenge memberChallenge = findByChallengeAndMember(challengeId,member.getId());
        MemberChallenge memberChallenge = findByChallengeAndMember(challengeId,1);

        // !!!!!!!!!!!이미지 업로드 관련 부분!!!!!!!!!!!!!!!
        String uuid = "user/" + UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = image.getContentType(); // 파일의 형식 ex) JPG
        log.info("UUID" + uuid);

        // Cloud에 이미지 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build(),
                image.getInputStream()
        );

        memberChallenge.imageUpdate("https://storage.googleapis.com/"+bucketName+"/"+uuid);

        memberChallengeRepository.save(memberChallenge);
    }

    public MemberChallenge findByChallengeAndMember(Long challengeId, int userId){
        return memberChallengeRepository.findByChallengeIdAndMemberId(challengeId,userId);
    }
}
