CREATE TABLE public.backstage_admin_table (
	userId serial NOT NULL,
	user_id_rand varchar(64) NOT NULL
);
ALTER TABLE public.backstage_admin_table ADD CONSTRAINT backstage_admin_table_pk PRIMARY KEY (userId);
ALTER TABLE public.user_table ADD user_id_rand varchar(64) NOT NULL;

ALTER TABLE public.normal_user_table DROP CONSTRAINT normal_user_table_user_table_fk;
ALTER TABLE public.admin_table DROP CONSTRAINT admin_user_table_fk;
ALTER TABLE public.user_table DROP COLUMN userId;

ALTER TABLE public.unprocessed_content_table DROP CONSTRAINT unprocessed_content_admin_fk;
ALTER TABLE public.admin_table DROP CONSTRAINT admin_un;
ALTER TABLE public.admin_table DROP CONSTRAINT admin_pk;

ALTER TABLE public.admin_table ALTER COLUMN user_id TYPE varchar(64) USING user_id::varchar;
ALTER TABLE public.admin_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;

