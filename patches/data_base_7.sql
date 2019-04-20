CREATE TABLE public.lisence_count (
	id serial NOT NULL,
	count int NOT NULL DEFAULT 0
);

ALTER TABLE public.lisence_count ADD CONSTRAINT lisence_count_pk PRIMARY KEY (id);
ALTER TABLE public.license ADD CONSTRAINT license_un_type_with_id UNIQUE (license_id,"type");

