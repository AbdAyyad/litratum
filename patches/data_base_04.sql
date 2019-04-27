CREATE TABLE public.proccessed_content_table (
	userId serial NOT NULL,
	file_name varchar(40) NOT NULL,
	doi varchar(64) NOT NULL,
	unprocessed_id int NULL,
	time_stamp varchar(20) NOT NULL DEFAULT to_char(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH:MM'::text)
);

ALTER TABLE public.proccessed_content_table ADD CONSTRAINT proccessed_content_table_pk PRIMARY KEY (userId);
ALTER TABLE public.proccessed_content_table ADD CONSTRAINT proccessed_content_table_doi UNIQUE (doi);
ALTER TABLE public.proccessed_content_table ADD CONSTRAINT proccessed_content_table_unprocessed_id UNIQUE (unprocessed_id);

ALTER TABLE public.proccessed_content_table ADD CONSTRAINT proccessed_content_table_unprocessed_content_table_fk FOREIGN KEY (unprocessed_id) REFERENCES public.unprocessed_content_table(userId) ON DELETE CASCADE ON UPDATE CASCADE;

