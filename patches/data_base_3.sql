CREATE TABLE public."admin" (
	id serial NOT NULL,
	user_id int NOT NULL
);

ALTER TABLE public."admin" ADD CONSTRAINT admin_pk PRIMARY KEY (id);

ALTER TABLE public."admin" ADD CONSTRAINT admin_user_table_fk FOREIGN KEY (user_id) REFERENCES public.user_table(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE public."admin" ADD CONSTRAINT admin_un UNIQUE (user_id);

ALTER TABLE public.unprocessed_content ADD CONSTRAINT unprocessed_content_admin_fk FOREIGN KEY (user_id) REFERENCES public."admin"(user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE public."admin" RENAME TO admin_table;

ALTER TABLE public.unprocessed_content RENAME TO unprocessed_content_table;



