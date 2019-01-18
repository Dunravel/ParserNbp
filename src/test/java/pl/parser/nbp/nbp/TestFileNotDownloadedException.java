package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;

public class TestFileNotDownloadedException {

    @Test
    public void shouldReturnTextWithFileName(){
        //given
        String fileName = "test.xml";
        //when
        String message = new FileNotDownloadedException().getMessage();
        //then
        Assert.assertEquals("File not downloaded: test.xml",message);
    }
}
