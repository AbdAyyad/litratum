ALTER TABLE public.admin_table ADD CONSTRAINT admin_table_pk PRIMARY KEY (userId);
ALTER TABLE public.admin_table ALTER COLUMN userId DROP DEFAULT;
ALTER TABLE public.admin_table ADD CONSTRAINT admin_table_un UNIQUE (user_id);
ALTER TABLE public.backstage_admin_table ALTER COLUMN userId TYPE varchar(64) USING userId::varchar;
ALTER TABLE public.backstage_admin_table ALTER COLUMN userId DROP DEFAULT;
ALTER TABLE public.backstage_admin_table RENAME COLUMN user_id_rand TO user_id;
ALTER TABLE public.backstage_admin_table RENAME COLUMN user_id TO admin_id;
ALTER TABLE public.user_table RENAME COLUMN user_id_rand TO user_id;
ALTER TABLE public.user_table RENAME COLUMN user_id TO userId;
ALTER TABLE public.user_table ADD CONSTRAINT user_table_pk PRIMARY KEY (userId);
ALTER TABLE public.license_subscription_table DROP CONSTRAINT license_subscription_pk;
ALTER TABLE public.license_subscription_table DROP COLUMN userId;
ALTER TABLE public.license_subscription_table RENAME COLUMN rand_id TO userId;
ALTER TABLE public.normal_user_table DROP CONSTRAINT normal_user_table_license_fk;
ALTER TABLE public.license_table DROP CONSTRAINT license_pk;
ALTER TABLE public.license_table DROP COLUMN userId;
ALTER TABLE public.license_table RENAME COLUMN id_rand TO userId;
ALTER TABLE public.license_table RENAME COLUMN license_id_rand TO license_id;
ALTER TABLE public.lisence_count_table DROP CONSTRAINT lisence_count_pk;
ALTER TABLE public.lisence_count_table DROP COLUMN userId;
ALTER TABLE public.lisence_count_table RENAME COLUMN id_rand TO userId;










