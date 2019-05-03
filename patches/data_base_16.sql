alter table user_table
    drop column logged_in;

alter table unprocessed_content_table
    alter column file_name type varchar(100) using file_name::varchar(100);

alter table unprocessed_content_table
    alter column time_stamp type varchar(100) using time_stamp::varchar(100);

alter table user_table
    add license_id varchar(64);

create unique index user_table_license_id_uindex
    on user_table (license_id);

alter table user_table
    add constraint user_table_license_table_license_id_fk
        foreign key (license_id) references license_table (license_id)
            on update cascade on delete cascade;
