package com.titian.innovation.file.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String imgPath;
    private String filePreview;
    private String videoPreview;
}
