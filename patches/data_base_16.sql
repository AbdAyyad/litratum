alter table user_table
    drop column logged_in;

alter table unprocessed_content_table
    alter column file_name type varchar(100) using file_name::varchar(100);

alter table unprocessed_content_table
    alter column time_stamp type varchar(100) using time_stamp::varchar(100);
