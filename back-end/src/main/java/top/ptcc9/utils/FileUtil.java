package top.ptcc9.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    private static String basePath;

    static {
        basePath = CommonUtil.getResourcePath() + "/static/";
    }

    public static String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = CommonUtil.getSimpleUUID() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }


    public static void local2Url(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dir2Dir(String sourcePath,String targetPath) throws IOException {
        Path sourceDir = Paths.get("source/directory/path");
        Path targetDir = Paths.get("target/directory/path");
        Files.walk(sourceDir).forEach(source -> {
            Path target = targetDir.resolve(sourceDir.relativize(source));
            try {
                Files.copy(source, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void writeByTxt(String path,String content) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            closingQuietly(bw,fw);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readStrFromTxt(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            StringBuilder builder = new StringBuilder();
            List<String> lines = br.lines().collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++) {
                builder.append(lines.get(i));
                if (i < lines.size() - 1) {
                    builder.append("\n");
                }
            }
            closingQuietly(br,fr);
            return builder.toString();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    public static void createIfHasNoDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }


    public static void closingQuietly(Closeable... t) {
        for (Closeable cur : t) {
            if (cur != null) {
                try {
                    cur.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
