CREATE TABLE adempiere.c_obra (
 c_obra_id 		numeric(10,0) NOT NULL,
 ad_client_id 		numeric(10,0) NOT NULL,
 ad_org_id 		numeric(10,0) NOT NULL,
 obra			character varying(40),
 concurso 		character varying(30),
 fecha_concurso 	date,
 descripcion 		character varying(254),
 ubicacion 		character varying(80),
 obra_presupuesto 	numeric(20,2),
 fecha_inicio 		date,
 fecha_termino 		date, 
 presupuesto_version 	character varying(7),
 ad_user_id 		numeric(10),
 isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
 created timestamp without time zone DEFAULT now() NOT NULL,
 createdby numeric(10,0) NOT NULL,
 updated timestamp without time zone DEFAULT now() NOT NULL,
 updatedby numeric(10,0) NOT NULL,
CONSTRAINT c_obra_pkey 	PRIMARY KEY (c_obra_id),
CONSTRAINT cobra_aduser FOREIGN KEY (ad_user_id)
REFERENCES adempiere.ad_user (ad_user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY
DEFERRED
);


CREATE TABLE adempiere.c_partida (
 c_partida_id 		numeric(20,0) NOT NULL,
 ad_client_id 		numeric(10,0) NOT NULL,
 ad_org_id 		numeric(10,0) NOT NULL,
 c_obra_id 		numeric(10,0) NOT NULL,
 nivel 			numeric(2,0) NOT NULL,
 signo 			character varying(1) NOT NULL,
 clave 			character varying(30) NOT NULL,
 descripcion 		text,
 unidad			character varying(8),
 cantidad		numeric(20,0),
 precio_unitario	numeric(20,0),
 importe_total		numeric(20,0),
 fecha_inicio		date,
 fecha_termino		date,
 indirectos		numeric(20,0),
 indirectos_campo	numeric(20,0),
 financiamiento		numeric(20,0),
 utilidad		numeric(20,0),
 cargos_adicionales	numeric(20,0),
 ad_user_id 		numeric(10),
 isactive character(1) DEFAULT 'Y'::bpchar,
 created timestamp without time zone DEFAULT now(),
 createdby numeric(10,0),
 updated timestamp without time zone DEFAULT now(),
 updatedby numeric(10,0),
CONSTRAINT c_partida_pkey  PRIMARY KEY (c_partida_id),
CONSTRAINT cpartida_aduser FOREIGN KEY (ad_user_id)
REFERENCES adempiere.ad_user (ad_user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED,
CONSTRAINT cpartida_obra   FOREIGN KEY (c_obra_id)
REFERENCES adempiere.c_obra (c_obra_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED
);


CREATE TABLE adempiere.c_concepto (
 c_concepto_id 		numeric(20,0) NOT NULL,
 ad_client_id 		numeric(10,0) NOT NULL,
 ad_org_id 		numeric(10,0) NOT NULL,
 c_obra_id 		numeric(10,0) NOT NULL,
 c_partida_id 		numeric(20,0) NOT NULL,
 clave_partida		character varying(20),
 clave_concepto		character varying(20),
 concepto_prefijo_id	numeric(5,0),
 descripcion		text,
 unidad			character varying(8),
 cantidad 		numeric(20,0),
 costo_unitario		numeric(20,0),
 importe_total		numeric(20,0),
 ad_user_id 		numeric(10),
 isactive character(1) DEFAULT 'Y'::bpchar,
 created timestamp without time zone DEFAULT now(),
 createdby numeric(10,0),
 updated timestamp without time zone DEFAULT now(),
 updatedby numeric(10,0),
CONSTRAINT c_concepto_pkey  PRIMARY KEY (c_concepto_id),
CONSTRAINT cconcepto_aduser FOREIGN KEY (ad_user_id)
REFERENCES adempiere.ad_user (ad_user_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED,
CONSTRAINT cconcepto_obra   FOREIGN KEY (c_obra_id)
REFERENCES adempiere.c_obra (c_obra_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED,
CONSTRAINT cconcepto_partida   FOREIGN KEY (c_partida_id)
REFERENCES adempiere.c_partida (c_partida_id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED
);
