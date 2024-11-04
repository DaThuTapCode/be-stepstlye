package com.okconde.bestepstyle.core.util.file;


import com.okconde.bestepstyle.core.exception.FileExceededSizeException;
import com.okconde.bestepstyle.core.exception.InvalidFileTypeException;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Created by Trong Phu on 17/09/2024 23:43
 *
 * @author Trong Phu
 */
public class FileUpLoadUtil {

    private static final Tika tika = new Tika();
    public static final String FILE_TYPE_IMAGE = "image/";

    private static boolean fileIsImage(MultipartFile file) {
        try {
            String mimiType = tika.detect(file.getInputStream());
            return mimiType.startsWith(FILE_TYPE_IMAGE);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Kiểm tra file đầu vào
     * <br>
     * Kiểm tra kích thước file: Ném ngoại lệ {@link FileExceededSizeException}
     * <br>
     * Kiểm tra định dạng file: Ném ngoại lệ {@link InvalidFileTypeException}
     *
     * @param multipartFile file được upload, đối tượng {@link MultipartFile}
     * @param conditionSize kích thước tối đa cho phép của file, tính bằng MB
     * @param conditionType kiểu file mà file phải tuân theo
     */
    public static boolean checkFileUpload(
            MultipartFile multipartFile,
            String conditionType,
            Long conditionSize

    ) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false;
        }

        if (multipartFile.getSize() > conditionSize * 1024 * 1024) {
            throw new FileExceededSizeException("Kích thước file quá giới hạn", conditionSize);
        }

        if (!fileIsImage(multipartFile)) {
            throw new InvalidFileTypeException("Định dạng file không hợp lệ", conditionType);
        }

        return true;
    }

    /**
     * Lưu file uploads
     */
    public static String uploadFile(MultipartFile file, String conditionType, Long conditionSize) throws IOException {
        if (checkFileUpload(file, conditionType, conditionSize)) {
                //Chuẩn bị tên file mới
                UUID uuid = UUID.randomUUID();
                String newFileName = uuid + "_" + file.getOriginalFilename();

                //Định nghĩa thư mục lưu trữ;
                Path uploadDir = Paths.get("uploads");
                //Kiểm tra thư mục có tồn tại hay không chưa thì tạo mới
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                Path destination = Paths.get(uploadDir.toString(), newFileName);
                Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
                return newFileName;
        }
        return null;
    }

    /**
     * Xóa file
     */
    public static boolean deleteFileByName(String fileName) {
        Path uploadDir = Paths.get("uploads");
        Path destination = Paths.get(uploadDir.toString(), fileName);
        File file = new File(String.valueOf(destination));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
