package me.redbean88.standAlone.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
public abstract class BaseTimeEntity { //왜 추상메소드??

    @CreatedDate // 생성시간 생성
    private LocalDateTime createdDate;

    @LastModifiedDate // 수정시간 생성
    private LocalDateTime modifiedDate;



}
