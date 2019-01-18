package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;

public class TestFileNotDownloadedException {

    @Test
    public void shouldReturnTextWithFileName(){
        //given
        String fileName = "test.xml";
        //when
        String message = new FileNotDownloadedException(fileName).getMessage();
        //then
        Assert.assertEquals("File download not successful: test.xml",message);
    }
}
