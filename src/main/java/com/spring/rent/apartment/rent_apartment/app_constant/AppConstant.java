package com.spring.rent.apartment.rent_apartment.app_constant;

public class AppConstant {

    /**
     * PATH_CONSTANT
     */
    public final static String BASE_PATH_USER = "/api_user";

    public static final String REGISTRATION = BASE_PATH_USER + "/registration";

    public static final String AUTHORIZATION = BASE_PATH_USER + "/authorization";

    public static final String LOGOUT = BASE_PATH_USER + "/logOut";

    public final static String BASE_PATH_APART = "/api_apart";

    public static final String ADD_NEW_APARTMENT =BASE_PATH_APART + "/add_rent_ap";

    public static final String ADD_NEW_COMMENT =BASE_PATH_APART + "/comment_rent_ap";

    public static final String FIND_APARTMENT =BASE_PATH_APART +  "/find_apart";

    public static final String BY_LOCATION = BASE_PATH_APART + "/by_location";

    public static final String BY_WEATHER = BASE_PATH_APART + "/by_weather";

    public static final String APART_BOOKING = BASE_PATH_APART + "/apart_booking";

    public static final String BASE_ARCHITECT_DB = "/architect";

    public static final String TABLE_FUNCTION = BASE_ARCHITECT_DB + "/table_function";

    public static final String DELETE_TABLE = BASE_ARCHITECT_DB + "/delete_table";

    public static final String BASE_PRODUCT = "/product";

    public static final String PREPARE_DISCOUNT = BASE_PRODUCT + "/prepare_discount";
    /**
     * MESSAGE_CONSTANT
     */
    public static final String AUTH_NOT_FOUND_USER = "Пользователь с таким логином не найден";

    public static final String NOT_VALID_PASSWORD = "Введён неверный пароль";

    public static final String SAVE_APARTMENT_SUCCESSFULLY = "Апартаменты успешно зарегистрированы";

    public static final String APARTMENT_NOT_FOUND = "Апартаменты не найдены, попробуйте повторить поиск";

}
