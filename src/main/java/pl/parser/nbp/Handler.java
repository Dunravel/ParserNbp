package pl.parser.nbp;

import pl.parser.nbp.domain.*;
import pl.parser.nbp.nbp.NbpData;
import pl.parser.nbp.nbp.NbpDownloader;
import pl.parser.nbp.validate.FileNameFilter;
import pl.parser.nbp.validate.InputCheck;

class Handler {

    void run(String[] args){
        InputData inputData = getInputData(args);

        NbpData nbpData = new NbpData(inputData.getStartDate(),inputData.getEndDate());
        FileList fileList = new FileList(nbpData.createFileList(new NbpDownloader()));

        filterFileList(inputData, fileList);

        CurrencyDataSet currencyDataSet = getCurrencyDataSet(inputData, fileList);
        nbpData.getFilesContent(currencyDataSet);

        displayResults(currencyDataSet);
    }

    void displayResults(CurrencyDataSet currencyDataSet) {
        Displayer displayer = new Displayer();
        displayer.displayDoubleToScreen(currencyDataSet.getAverageBuyRate());
        displayer.displayDoubleToScreen(currencyDataSet.getDeviationSellRate());
    }

    CurrencyDataSet getCurrencyDataSet(InputData inputData, FileList fileList) {
        CurrencyDataSetFactory currencyDataSetFactory = new CurrencyDataSetFactory();
        return currencyDataSetFactory.create(inputData.getCurrency(),fileList.getFileList());
    }

    void filterFileList(InputData inputData, FileList fileList) {
        FileNameFilter fileNameFilter = new FileNameFilter(inputData);
        fileNameFilter.filter(fileList);
    }



    InputData getInputData(String[] args) {
        InputCheck inputCheck = new InputCheck();
        if(inputCheck.verify(args)){
            return new InputData(args);
        }
        return null;
    }
}
