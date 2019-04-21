ALTER TABLE public.license_table RENAME COLUMN "licenseType" TO license_type;
ALTER TABLE public.proccessed_content_table ALTER COLUMN unprocessed_id TYPE varchar(64) USING unprocessed_id::varchar;
ALTER TABLE public.proccessed_content_table ALTER COLUMN unprocessed_id SET NOT NULL;
