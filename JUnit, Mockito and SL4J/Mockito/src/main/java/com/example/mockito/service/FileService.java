package com.example.mockito.service;

import com.example.mockito.file.FileReader;
import com.example.mockito.file.FileWriter;

public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        return "Processed " + content;
    }
}
