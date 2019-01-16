package pl.parser.nbp;

class FileNameFilter {
    private String tableType;
    private String startDate;
    private String endDate;

    FileNameFilter(String tableType, String startDate, String endDate){
        this.tableType = tableType;
        this.startDate = startDate.substring(2,4) + startDate.substring(5,7) + startDate.substring(8,10);
        this.endDate = endDate.substring(2,4) + endDate.substring(5,7) + endDate.substring(8,10);
    }
    String getCorrectFileName(String input) {
        input = input.replace("\uFEFF", "");
        if (input.startsWith("c")) {
            if (input.substring(5, 11).compareTo(startDate) >= 0 && input.substring(5, 11).compareTo(endDate) <= 0) {
                return input;
            }
        }
        return null;
    }
}
