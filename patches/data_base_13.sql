ALTER TABLE public.admin_table RENAME COLUMN userId TO admin_id;
ALTER TABLE public.backstage_admin_table RENAME COLUMN userId TO backstage_admin_id;
ALTER TABLE public.backstage_admin_table RENAME COLUMN admin_id TO user_id;
ALTER TABLE public.license_subscription_table RENAME COLUMN userId TO license_subscription_id;
ALTER TABLE public.license_table RENAME COLUMN license_id TO actual_license_id;
ALTER TABLE public.license_table RENAME COLUMN userId TO license_id;
ALTER TABLE public.lisence_count_table RENAME COLUMN userId TO license_count_id;
ALTER TABLE public.normal_user_table RENAME COLUMN userId TO normal_user_id;
ALTER TABLE public.proccessed_content_table RENAME COLUMN userId TO processed_content_id;
ALTER TABLE public.unprocessed_content_table RENAME COLUMN userId TO unprocessed_content_id;
ALTER TABLE public.user_table RENAME COLUMN userId TO user_id;
ALTER TABLE public.license_table RENAME COLUMN "type" TO license_type;

