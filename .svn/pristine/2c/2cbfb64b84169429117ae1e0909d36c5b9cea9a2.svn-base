package com.common;

import static com.common.CachedProperties.dataInstance;
import static com.common.CachedProperties.instance;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.thoughtworks.selenium.SeleneseTestBase;

public class FrameworkDeclaration extends SeleneseTestBase {



	//public RemoteWebDriver driver;
    public CachedProperties cachedProperties = instance();
    public CachedProperties data = dataInstance();
    
    public boolean screenshot=Boolean.parseBoolean(cachedProperties.value("screenshot"));
    
    public HashMap < String, String > Metrics_readValuesFromCSV() {

        InputStream fin = this.getClass().getClassLoader().getResourceAsStream("Metrics/Metrics_OR.csv");

        DataInputStream in = new DataInputStream(fin);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        HashMap < String, String > values = new HashMap < String, String >();

        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                if ( !strLine.startsWith("###") ) {
                    String tokens[] = strLine.split(";");
                    values.put(tokens[0].trim(), tokens[1].trim());
                }
            }
            in.close();
        }
        catch (Exception e) {
        	
        	System.out.println("please check if the space is ter in OR");
        }
        return values;
    }

    

    

    public String getTime() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeDate = dateFormat.format(date);
        return timeDate;
    }

   

    public String putDate(int days) throws Exception {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, +days);
        Date s = c.getTime();
        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(s);
        return dateString;
    }

    public String putLogDate() throws Exception {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, +0);
        Date s = c.getTime();
        String dateString = new SimpleDateFormat("_EEE_ddMMMyyyy_hhmmss").format(s);
        return dateString;
    }
    public String putuser() throws Exception {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, +0);
        Date s = c.getTime();
        String dateString = new SimpleDateFormat("EEEddMMMhhmmss").format(s);
        return dateString;
    }
    

}
