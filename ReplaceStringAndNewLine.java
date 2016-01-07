import java.io.*;
/*
找到字符串并替换，新建一行
*/
public class Main {

    public static void main(String[] args) {
        String MatchString = "PANEL_ERR_RESULT";
        String replaceString = "PANEL_MT_ERR_RESULT";
//        FileWriter fileWritter = null;
//        BufferedWriter bufferWritter = null;
        File Infile = new File("c:\\MTK_atedemo_V1.c");
        File Outfile = new File("c:\\result2.c");
        FileOutputStream out = null;
        BufferedReader reader = null;
        int converLine = 0;
        int totalLine = 0;

        try {
            if (!Outfile.exists()) {
                Outfile.createNewFile();
            }
            out = new FileOutputStream(Outfile);
//            fileWritter = new FileWriter(Outfile.getName(), true);
//            bufferWritter = new BufferedWriter(fileWritter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader(Infile));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                totalLine++;

//                bufferWritter.write(tempString);
//                bufferWritter.newLine();
                out.write(tempString.getBytes());
                out.write("\n".getBytes());
                if (tempString.contains(MatchString)) {
                    String copyString = new String(tempString);
                    copyString = copyString.replace(MatchString, replaceString);
                    if (copyString.contains("handle")) {
                        copyString = copyString.replace("handle", "MEITU_handle");
                    }
//                    bufferWritter.write(copyString);
//                    bufferWritter.newLine();
                    out.write(copyString.getBytes());
                    out.write("\n".getBytes());
                    converLine++;
                }
//                bufferWritter.flush();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
           out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Complete"+" Total:"+totalLine+" Convert:"+converLine);
    }
}

