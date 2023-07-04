package com.example.demo.configuration;

import com.example.demo.Dto.EntityDto.ContentDto;
import com.example.demo.Entity.Content;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Content, ContentDto> contentTypeMap = modelMapper.createTypeMap(Content.class, ContentDto.class);
        contentTypeMap.addMappings(
                mapper -> {
                    mapper.map(Content::getId, ContentDto::setId);
                    mapper.map(Content::getContentType, ContentDto::setContentType);
                    mapper.map(Content::getCreatedDate, ContentDto::setCreatedDate);
                }
        );

        TypeMap<ContentDto, Content> contentDtoTypeMap = modelMapper.createTypeMap(ContentDto.class, Content.class);
        contentDtoTypeMap.addMappings(
                mapper -> {
                    mapper.map(ContentDto::getId, Content::setId);
                    mapper.map(ContentDto::getContentType, Content::setContentType);

                    mapper.map(ContentDto::getCreatedDate, Content::setCreatedDate);
                }
        );
        return modelMapper;
    }
}
