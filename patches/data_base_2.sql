CREATE TABLE public.unprocessed_content (
	id serial NOT NULL,
	file_name varchar(20) NOT NULL,
	user_id int NOT NULL,
	status int NOT NULL DEFAULT 0
);

ALTER TABLE public.unprocessed_content ADD CONSTRAINT unprocessed_content_pk PRIMARY KEY (id);

ALTER TABLE public.unprocessed_content ADD time_stamp varchar(30) NOT NULL DEFAULT TO_CHAR(CURRENT_TIMESTAMP,'YYYY-MM-DDTHH:MM');

ALTER TABLE public.unprocessed_content ALTER COLUMN time_stamp SET DEFAULT to_char(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH:MM'::text);




