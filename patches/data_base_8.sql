CREATE TABLE public.license_subscription (
	userId serial NOT NULL,
	end_date varchar(20) NOT NULL DEFAULT to_char(CURRENT_TIMESTAMP, 'YYYY-MM-DD'::text)
);

ALTER TABLE public.license_subscription ADD CONSTRAINT license_subscription_pk PRIMARY KEY (userId);
ALTER TABLE public.license ADD id_rand varchar(20) NOT NULL;
ALTER TABLE public.license DROP COLUMN license_id;
ALTER TABLE public.license ADD CONSTRAINT license_id_un UNIQUE (id_rand);
ALTER TABLE public.license ADD license_id_rand varchar(64) NOT NULL;
ALTER TABLE public.license ALTER COLUMN id_rand TYPE varchar(64) USING id_rand::varchar;
ALTER TABLE public.license ADD CONSTRAINT license_fk_un UNIQUE (license_id_rand);
ALTER TABLE public.lisence_count ADD id_rand varchar(64) NOT NULL;
ALTER TABLE public.lisence_count ADD CONSTRAINT lisence_count_un UNIQUE (id_rand);
ALTER TABLE public.license_subscription ADD rand_id varchar(64) NOT NULL;
ALTER TABLE public.license_subscription ADD CONSTRAINT license_subscription_un UNIQUE (rand_id);

