package pl.parser.nbp.nbp;

class FileNotDownloadedException extends RuntimeException {
    FileNotDownloadedException(String fileName) {
        super("File not downloaded: " + fileName);
    }
}
