CREATE TABLE public.user_table (
	id serial NOT NULL,
	user_name varchar(25) NOT NULL,
	user_email varchar(64) NOT NULL,
	user_password varchar(64) NOT NULL,
	lisence_id int NULL
);

ALTER TABLE public.user_table ADD CONSTRAINT user_table_pk PRIMARY KEY (id);
ALTER TABLE public.user_table ADD CONSTRAINT user_name_un UNIQUE (user_name);
ALTER TABLE public.user_table ADD CONSTRAINT user_email_un UNIQUE (user_email);
ALTER TABLE public.user_table ADD CONSTRAINT user_lisence_id_un UNIQUE (lisence_id);

