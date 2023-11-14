package com.example.file.controller;


import com.example.file.commons.response.JsonResponse;
import com.example.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther Kindow
 * @date 2023/11/9
 * @project imooc-bilibili
 */

@RestController
public class FileApi {

    @Autowired
    private FileService fileService;


    @PostMapping("/md5files")
    public JsonResponse<String> getFileMD5(@RequestBody MultipartFile file) throws Exception {
        String fileMD5 = fileService.getFileMD5(file);
        return new JsonResponse<>(fileMD5);
    }


    @PutMapping("/file-slices")
    public JsonResponse<String> uploadFileBySlices(MultipartFile slice,
                                                   String fileMd5,
                                                   Integer sliceNo,
                                                   Integer totalSliceNo) throws Exception {
        String filePath = fileService.uploadFileBySlices(slice,fileMd5,sliceNo,totalSliceNo);
        return new JsonResponse<>(filePath);

    }


}

