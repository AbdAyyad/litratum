ALTER TABLE public.normal_user_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;
ALTER TABLE public.normal_user_table ALTER COLUMN userId DROP DEFAULT;
ALTER TABLE public.normal_user_table ALTER COLUMN user_id TYPE varchar(64) USING user_id::varchar;
ALTER TABLE public.normal_user_table ALTER COLUMN license_id TYPE varchar(64) USING license_id::varchar;
ALTER TABLE public.proccessed_content_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;
ALTER TABLE public.proccessed_content_table ALTER COLUMN userId DROP DEFAULT;
ALTER TABLE public.proccessed_content_table ADD backstage_admin_id varchar(64) NOT NULL;
ALTER TABLE public.proccessed_content_table DROP CONSTRAINT proccessed_content_table_unprocessed_content_table_fk;

ALTER TABLE public.unprocessed_content_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;
ALTER TABLE public.unprocessed_content_table ALTER COLUMN userId DROP DEFAULT;
ALTER TABLE public.unprocessed_content_table ALTER COLUMN user_id TYPE varchar(64) USING user_id::varchar;
ALTER TABLE public.unprocessed_content_table ALTER COLUMN user_id TYPE varchar(64) USING user_id::varchar;
ALTER TABLE public.unprocessed_content_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;
ALTER TABLE public.unprocessed_content_table RENAME COLUMN user_id TO admin_id;


