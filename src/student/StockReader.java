package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockReader {

    public static App read(String stockFilePath) throws Exception {
        File file = new File(stockFilePath);
        try{
            Scanner scanner = null;
            scanner = new Scanner(file);
            
            List<Brief> briefs = new ArrayList<>();
            List<Integer> qtys = new ArrayList<>();
    
            String line = "";
            while (scanner.hasNextLine()) {              // DO NOT MODIFY
                line = scanner.nextLine();               // DO NOT MODIFY
                String[] parts = line.split(",");  // DO NOT MODIFY
                if (parts.length == 3 && parts[1].contains("book")) {
                    briefs.add(new Brief(parts[0], parts[1], true));
                    qtys.add(Integer.valueOf(parts[2]));
                }
                if (parts.length == 3 && parts[1].contains("other")) {
                    briefs.add(new Brief(parts[0], parts[1], false));
                    qtys.add(Integer.valueOf(parts[2]));
                }
            }
            scanner.close();
            
            Stock briefStart = new Stock(briefs, qtys); 
            
            return new App(briefStart); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("has badpath" + e.getMessage());
        }
        return null;
    }
}