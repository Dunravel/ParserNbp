package pl.parser.nbp.domain;

import java.util.Set;

public class FileList {
    private Set<String> fileList;

    public FileList(Set<String> fileList) {
        this.fileList = fileList;
    }

    public Set<String> getFileList() {
        return fileList;
    }
}
