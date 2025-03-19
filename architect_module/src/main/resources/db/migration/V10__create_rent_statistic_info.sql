CREATE  TABLE IF NOT EXISTS statistic_info(

    id int8 primary key ,

    views_count int8 default 0,

    booking_count int8 default 0,

    total_sum_revenue int8 default 0

);
CREATE SEQUENCE statistic_info_sequence start 1 increment 1;