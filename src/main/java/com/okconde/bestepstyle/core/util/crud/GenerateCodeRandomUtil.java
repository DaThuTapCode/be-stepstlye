package com.okconde.bestepstyle.core.util.crud;

import java.util.Random;

/**
 * Created by Trong Phu on 20/10/2024 13:34
 *
 * @author Trong Phu
 */
public class GenerateCodeRandomUtil {

    /**
     * Hàm gencode 10 ký tự, tự động
     * @param startWord là ký tự bắt đầu của mã
     * @param length độ dài đoạn ký tự phía sau*/
    public static String generateProductCode(String startWord, int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(startWord);
        Random random = new Random();

        // Tạo thêm 8 ký tự ngẫu nhiên từ A-Z và 0-9
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            code.append(chars.charAt(randomIndex));
        }

        return code.toString();
    }
}
