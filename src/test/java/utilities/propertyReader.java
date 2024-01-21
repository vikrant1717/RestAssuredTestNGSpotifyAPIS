package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public  class propertyReader {

    public static String fetchPropertyData(String key) throws IOException {
        Properties prop = new Properties();
        File f = new File("./src/test/resources/token.properties");
        FileReader fr = new FileReader(f);
        prop.load(fr);
        return prop.getProperty(key);
    }
}
