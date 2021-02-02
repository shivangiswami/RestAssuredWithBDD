import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    public static void main(String[] args) throws IOException {
        ArrayList<String> myList=new ArrayList();
        File file=new File("src/main/resources/TestData.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheet("Sheet1");
        int rowCount=sheet.getLastRowNum();

       /* for(int i=1;i<=rowCount;i++)
        {
            for(int j=1;j<=3;j++) {
                System.out.println(sheet.getRow(i).getCell(j));

            }
        }*/
        Iterator<Row> rows=sheet.iterator();
        Row firstRow=rows.next();
        Iterator<Cell> cell=firstRow.cellIterator();
        Cell firstColumn=cell.next();
        Assert.assertTrue(firstColumn.getStringCellValue().equals("Testcases"));

            while (cell.hasNext()) {
                myList.add(cell.next().getStringCellValue());
        }

        for(String str:myList)
        {
            System.out.println(str);
        }

    }

}
