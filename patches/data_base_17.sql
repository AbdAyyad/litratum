Alter table proccessed_content_table
    drop column backstage_admin_id;

alter table proccessed_content_table
    drop column time_stamp;

create table article_meta
(
    author varchar(50)  not null,
    doi    varchar(100) not null,
    date   varchar(100) not null,
    title  varchar(100) not null
);

alter table article_meta
    rename to article_meta_table;

drop table proccessed_content_table;

alter table article_meta_table
    add "unprocessed-id" varchar(64);

create unique index "article_meta_table_unprocessed-id_uindex"
    on article_meta_table (unprocessed_id);

alter table article_meta_table
    rename column "unprocessed-id" to unprocessed_id;

