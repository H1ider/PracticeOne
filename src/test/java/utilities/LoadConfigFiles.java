package utilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


public class LoadConfigFiles {
    private static final Logger LOGGER = LogManager.getLogger(LoadConfigFiles.class);

    public Properties readPropertyValues() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = null;    //initialize with null value
        try {
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if(inputStream != null){
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property File " + propFileName + " Not Found in the classpath");
            }

        } catch (Exception e){
            LOGGER.error("Exception is "+ e.getMessage());

        } finally {
            inputStream.close();

        }
        return prop;
    }
}
