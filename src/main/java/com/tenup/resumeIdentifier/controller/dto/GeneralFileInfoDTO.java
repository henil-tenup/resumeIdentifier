package com.tenup.resumeIdentifier.controller.dto;

import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralFileInfoDTO {

    private String originalFileName;

    private File originalFile;

    private Long fileSize;

    private String fileContentType;
}
