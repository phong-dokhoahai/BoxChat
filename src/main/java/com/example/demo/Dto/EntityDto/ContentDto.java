package com.example.demo.Dto.EntityDto;

import com.example.demo.Entity.Content;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private Long id;
    private Date createdDate;
    private Content.ContentType contentType;
    private String nickName;
    private String content;

    public ContentDto(Long id,String content,String nickName,Date createdDate) {
        this.id = id;
        this.content=content;
        this.nickName=nickName;
        this.createdDate=createdDate;
    }
}
