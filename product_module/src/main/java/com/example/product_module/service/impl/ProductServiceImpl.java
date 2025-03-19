package com.example.product_module.service.impl;


import com.example.product_module.entity.ProductEntity;
import com.example.product_module.entity.UserEntity;
import com.example.product_module.repository.ProductRepository;
import com.example.product_module.repository.UserInfoRepository;
import com.example.product_module.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserInfoRepository userInfoRepository;

    @Override
    public void printReport() {

        File file = new File("/Users/a1/Documents/Java_Program/rent_apartment/report_template.xlsx");
        try (FileInputStream fileInputStream = new FileInputStream(file);

             XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream)){
            XSSFSheet sheet = workBook.getSheetAt(0);
            int rowNum = 1;

            List<ProductEntity> all = productRepository.findAll();
            for(ProductEntity productEntity : all){
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(productEntity.getDescription());
                row.createCell(1).setCellValue(productEntity.getPercentage().toString());
                row.createCell(2).setCellValue(productEntity.getAppTo());
            }
            List<UserEntity> allUser = userInfoRepository.findAll();
            for(UserEntity userEntity : allUser){
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(userEntity.getId());
                row.createCell(1).setCellValue(userEntity.getLogin());
                row.createCell(2).setCellValue(userEntity.getPassword());
                row.createCell(3).setCellValue(userEntity.getEmail());
                row.createCell(4).setCellValue(userEntity.getNickName());
                row.createCell(5).setCellValue(userEntity.getToken());

            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workBook.write(fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("Проблема с выгрузкой отчета");
        }
    }

}
