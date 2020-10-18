package codefirst.spring.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import codefirst.spring.demo.util.Constans;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ArffCreator {
    static Logger logger = LoggerFactory.getLogger(ArffCreator.class);
    public int totalDataSize;

    public void createFile() throws IOException {
        File fileArff = new File(Constans.FILE_PATH_CSV);
        File fileTestArff = new File(Constans.FILE_PATH_TESTARFF);
        if (fileArff.exists()) {
            logger.info("Already there is a file");
        } else {
            fileArff.createNewFile();
        }
        if (fileTestArff.exists()) {
            logger.info("Already there is a test file");
        } else {
            fileTestArff.createNewFile();
        }

    }

    public void calculateArffDataSize() throws IOException {
        totalDataSize = Files.readAllLines(Paths.get(Constans.FILE_PATH_CSV)).size() - 1;
    }

    public void writeFile() throws IOException {
        FileWriter fw = new FileWriter(Constans.FILE_PATH_ARFF);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constans.FILE_PATH_CSV));
        FileWriter fwTest = new FileWriter(Constans.FILE_PATH_TESTARFF);
        fwTest.write("@RELATION wineTest ");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE FixedAcidity NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE VolatileAcidity NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE CitricAcid NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE ResidualSugar NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE Chlorides NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE FreeSulfurdioxide NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE TotalSulfurdioxide NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE Density NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE pH NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE Sulphates NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE Alcohol NUMERIC");
        fwTest.append("\n");
        fwTest.write("@ATTRIBUTE Quality NUMERIC");
        fwTest.append("\n");
        fwTest.write("@DATA");
        fwTest.append("\n");
        fw.write("@RELATION wine ");
        fw.append("\n");
        fw.write("@ATTRIBUTE FixedAcidity NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE VolatileAcidity NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE CitricAcid NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE ResidualSugar NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE Chlorides NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE FreeSulfurdioxide NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE TotalSulfurdioxide NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE Density NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE pH NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE Sulphates NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE Alcohol NUMERIC");
        fw.append("\n");
        fw.write("@ATTRIBUTE Quality NUMERIC");
        fw.append("\n");
        fw.write("@DATA");
        fw.append("\n");
        String row = null;
        bufferedReader.readLine();
        int count = 1;
        while ((row = bufferedReader.readLine()) != null && count <= ((totalDataSize * 9) / 10)) {
            String[] data = row.split(";");
            for (int i = 0; i < data.length; i++) {
                fw.append(data[i]);
                if (i < data.length - 1) {
                    fw.append(",");
                }
            }
            fw.append("\n");
            count++;
        }


        while ((row = bufferedReader.readLine()) != null) {
            String[] dataTest = row.split(";");
            for (int i = 0; i < dataTest.length; i++) {
                fwTest.append(dataTest[i]);
                if (i < dataTest.length - 1) {
                    fwTest.append(",");
                }
            }
            fwTest.append("\n");
        }

        fw.flush();
        fw.close();
        fwTest.flush();
        fwTest.close();
    }

}
