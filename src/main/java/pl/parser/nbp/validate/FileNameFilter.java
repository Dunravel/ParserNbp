package pl.parser.nbp.validate;

import pl.parser.nbp.domain.FileList;
import pl.parser.nbp.domain.InputData;

import java.util.Set;

public class FileNameFilter {
    private static final String CURRENCY_TABLE_TYPE = "c";
    private final String startDate;
    private final String endDate;

    public FileNameFilter(String startDate, String endDate){
        this.startDate = convertDate(startDate);
        this.endDate = convertDate(endDate);
    }

    public FileNameFilter(InputData inputData) {
        this.startDate = convertDate(inputData.getStartDate());
        this.endDate = convertDate(inputData.getEndDate());
    }

    private String convertDate(String date) {
        return date.substring(2, 4) + date.substring(5, 7) + date.substring(8, 10);
    }

    public void filter(FileList fileList) {
        Set<String> fileSet = fileList.getFileList();
        fileSet.forEach(this::removeIncorrectCharacters);
        fileSet.removeIf(this::isNotCorrectFileName);
    }

    String removeIncorrectCharacters(String input) {
        if(input.startsWith("\uFEFF")) {
            input = input
                    .trim()
                    .replace("\uFEFF", "");
        }
        return input;
    }

    boolean isNotCorrectFileName(String fileName) {
        if (fileName.startsWith(CURRENCY_TABLE_TYPE)) {
            String datePart = getDatePart(fileName);
            if (datePart.compareTo(startDate) >= 0 && datePart.compareTo(endDate) <= 0) {
                return false;
            }
        }
        return true;
    }

    private String getDatePart(String fileName) {
        return fileName.substring(5, 11);
    }

}
