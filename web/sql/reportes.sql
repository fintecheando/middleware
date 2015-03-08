CREATE TABLE adempiere.c_civil_work_concept (
c_civil_work_concept_id numeric(10,0) NOT NULL PRIMARY KEY,
ad_client_id numeric(10,0) NOT NULL,
ad_org_id numeric(10,0) NOT NULL,
isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
created timestamp without time zone DEFAULT now() NOT NULL,
createdby numeric(10,0) NOT NULL,
updated timestamp without time zone DEFAULT now() NOT NULL,
updatedby numeric(10,0) NOT NULL,
type character varying(255) NOT NULL,
name character varying(255) NOT NULL,
code character varying(255) NOT NULL
);
