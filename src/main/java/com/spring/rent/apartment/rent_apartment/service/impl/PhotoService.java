package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.ApartmentNotFoundException;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.APARTMENT_NOT_FOUND;

@Service
public class PhotoService {

    private ApartmentRepository apartmentRepository;

    @Autowired
    public PhotoService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public void savePhoto(Long apartmentId, MultipartFile file) throws IOException {

        System.out.println("Загрузка фото для апартамента с ID: " + apartmentId);

        ApartmentEntity apartment = apartmentRepository.findById(apartmentId).orElseThrow(() -> new ApartmentNotFoundException(APARTMENT_NOT_FOUND, 700));

        Path photoDir = Paths.get("photos");
        System.out.println("Путь к папке для фото: " + photoDir.toAbsolutePath());

        // Проверяем, существует ли папка
        if (!Files.exists(photoDir)) {
            System.out.println("Папка не существует, создаем...");
            Files.createDirectories(photoDir);  // создаем папку и все необходимые родительские директории
        }

        // Путь к файлу фото
        Path photoPath = photoDir.resolve(file.getOriginalFilename());
        System.out.println("Путь к фото: " + photoPath.toAbsolutePath());

        // Копируем фото
        try {
            Files.copy(file.getInputStream(), photoPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Фото успешно загружено!");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке фото: " + e.getMessage());
            throw e;  // выбрасываем исключение, если ошибка
        }

        // Сохраняем путь фото в базе данных
        apartment.setPhotoPath(photoPath.toString());
        apartmentRepository.save(apartment);
        System.out.println("Путь к фото успешно сохранен в базе данных.");
    }
}
