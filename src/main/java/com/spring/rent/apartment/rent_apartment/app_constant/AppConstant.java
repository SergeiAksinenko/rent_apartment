package com.spring.rent.apartment.rent_apartment.app_constant;

public class AppConstant {

    /**
     * PATH_CONSTANT
     */
    public final static String BASE_PATH = "/api";

    public static final String BASE_PATH_RENT = "/api_rent_ap";

    public static final String REGISTRATION = BASE_PATH + "/registration";

    public static final String AUTHORIZATION = BASE_PATH + "/authorization";

    public static final String RATING_RENT_APARTMENT = BASE_PATH_RENT + "/rating_rent_ap";

    public static final String LOGOUT = BASE_PATH + "/logOut";

    /**
     * MESSAGE_CONSTANT
     */
    public static final String AUTH_NOT_FOUND_USER = "Пользователь с таким логином не найден";

    public static final String NOT_VALID_PASSWORD = "Введён неверный пароль";

    public static final String SAVE_APARTMENT_SUCCESSFULLY = "Аппартаменты успешно зарегистрированы";

}
