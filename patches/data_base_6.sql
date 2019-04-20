CREATE TABLE public.normal_user_table (
	id serial NOT NULL,
	user_id int NOT NULL,
	license_id varchar NULL
);

ALTER TABLE public.normal_user_table ADD CONSTRAINT normal_user_table_pk PRIMARY KEY (id);
ALTER TABLE public.normal_user_table ADD CONSTRAINT normal_user_table_user_id_un UNIQUE (user_id);
ALTER TABLE public.normal_user_table ALTER COLUMN license_id TYPE int USING license_id::int;
ALTER TABLE public.normal_user_table ADD CONSTRAINT normal_user_table_license_id_un UNIQUE (license_id);

ALTER TABLE public.normal_user_table ADD CONSTRAINT normal_user_table_user_table_fk FOREIGN KEY (user_id) REFERENCES public.user_table(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE public.normal_user_table ADD CONSTRAINT normal_user_table_license_fk FOREIGN KEY (license_id) REFERENCES public.license(id) ON DELETE CASCADE ON UPDATE CASCADE;

