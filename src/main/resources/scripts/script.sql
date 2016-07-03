 CREATE TABLE cliente
(
  id bigserial NOT NULL,
  cpf character varying(255) NOT NULL,
  nome character varying(255),
  CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

CREATE TABLE fornecedor
(
  id bigserial NOT NULL,
  cnpj character varying(255) NOT NULL,
  nome character varying(255),
  CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
);

CREATE TABLE produto
(
  id bigserial NOT NULL,
  descricao character varying(255) NOT NULL,
  saldo numeric(12,2),
  valor numeric(12,2) NOT NULL,
  fornecedor_id bigint,
  CONSTRAINT produto_pkey PRIMARY KEY (id),
  CONSTRAINT fornecedor_fkey FOREIGN KEY (fornecedor_id)

);


CREATE TABLE venda
(
  id bigserial NOT NULL,
  datavenda date NOT NULL,
  valor numeric(12,2),
  cliente_id bigint NOT NULL,
  CONSTRAINT venda_pkey PRIMARY KEY (id),
  CONSTRAINT clinte_fkey FOREIGN KEY (cliente_id)
);

CREATE TABLE venda_item
(
  id bigserial NOT NULL,
  quantidade numeric(12,2),
  valor numeric(12,2),
  produto_id bigint,
  venda_id bigint,
  CONSTRAINT venda_item_pkey PRIMARY KEY (id),
  CONSTRAINT venda_fkey FOREIGN KEY (venda_id)
  CONSTRAINT produto_fkey FOREIGN KEY (produto_id)
);


 
 CREATE OR REPLACE FUNCTION atlz_saldo_prod() RETURNS TRIGGER AS $TVENDA$
    BEGIN
       
        IF (TG_OP = 'DELETE') THEN
            UPDATE PRODUTO SET SALDO = SALDO + OLD.QUANTIDADE WHERE OLD.PRODUTO_ID = PRODUTO.ID;
        ELSIF (TG_OP = 'INSERT') THEN
            UPDATE PRODUTO SET SALDO = SALDO - NEW.QUANTIDADE WHERE NEW.PRODUTO_ID = PRODUTO.ID;
        END IF;
        RETURN NULL;
    END;
    $TVENDA$ language plpgsql;

CREATE TRIGGER TVENDA
  AFTER INSERT OR UPDATE OR DELETE ON VENDA_ITEM
    FOR EACH ROW EXECUTE PROCEDURE atlz_saldo_prod();