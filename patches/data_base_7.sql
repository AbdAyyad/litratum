CREATE TABLE public.lisence_count (
	userId serial NOT NULL,
	count int NOT NULL DEFAULT 0
);

ALTER TABLE public.lisence_count ADD CONSTRAINT lisence_count_pk PRIMARY KEY (userId);
ALTER TABLE public.license ADD CONSTRAINT license_un_type_with_id UNIQUE (license_id,"licenseType");

