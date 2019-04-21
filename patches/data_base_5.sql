CREATE TABLE public.license (
	userId serial NOT NULL,
	"licenseType" int NOT NULL,
	license_id int NOT NULL,
	time_stamp varchar(20) NOT NULL DEFAULT to_char(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH:MM'::text)
);

ALTER TABLE public.license ADD CONSTRAINT license_pk PRIMARY KEY (userId);
ALTER TABLE public.license ADD CONSTRAINT license_id_un UNIQUE (license_id);

ALTER TABLE public.license DROP CONSTRAINT license_id_un;
ALTER TABLE public.user_table DROP COLUMN lisence_id;

