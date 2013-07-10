CREATE SEQUENCE log_seq
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

/* ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_Article
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLE
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Article';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

CREATE OR REPLACE TRIGGER trg_log_ArticleHistory
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLEHISTORY
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'ArticleHistory';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* ARTICLECATEGORY */
CREATE OR REPLACE TRIGGER trg_log_ArticleCategory
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLECATEGORY
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'ArticleCategory';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* BUSINESSPARTNER */
CREATE OR REPLACE TRIGGER trg_log_BusinessPartner
AFTER INSERT OR UPDATE OR DELETE
ON BUSINESSPARTNER
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'BusinessPartner';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* SUPPLIER */
CREATE OR REPLACE TRIGGER trg_log_Supplier
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIER
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Supplier';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* CLIENTCATEGORY */
CREATE OR REPLACE TRIGGER trg_log_ClientCategory
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTCATEGORY
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'ClientCategory';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* CLIENT */
CREATE OR REPLACE TRIGGER trg_log_Client
AFTER INSERT OR UPDATE OR DELETE
ON CLIENT
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Client';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* SALARYCATEGORY */
CREATE OR REPLACE TRIGGER trg_log_SalaryCategory
AFTER INSERT OR UPDATE OR DELETE
ON SALARYCATEGORY
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'SalaryCategory';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* DIVISION */
CREATE OR REPLACE TRIGGER trg_log_Division
AFTER INSERT OR UPDATE OR DELETE
ON DIVISION
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Division';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* USERGROUP */
CREATE OR REPLACE TRIGGER trg_log_UserGroup
AFTER INSERT OR UPDATE OR DELETE
ON USERGROUP
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'UserGroup';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* EMPLOYEE */
CREATE OR REPLACE TRIGGER trg_log_Employee
AFTER INSERT OR UPDATE OR DELETE
ON EMPLOYEE
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Employee';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* PERMISSION */
CREATE OR REPLACE TRIGGER trg_log_Permission
AFTER INSERT OR UPDATE OR DELETE
ON PERMISSION
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Permission';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* SUPPLIERBILL */
CREATE OR REPLACE TRIGGER trg_log_SupplierBill
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERBILL
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'SupplierBill';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* SUPPLIERORDER */
CREATE OR REPLACE TRIGGER trg_log_SupplierOrder
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERORDER
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'SupplierOrder';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* SUPPLIERORDER_ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_SupplierOrderArticle
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERORDER_ARTICLE
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
	v_idEmployeeModify INTEGER;
	v_lastModifiedDate DATE;
BEGIN
	v_entity := 'SupplierOrderDetail';
	IF DELETING THEN
		SELECT idEmployeeModify
			INTO v_idEmployeeModify
		FROM SUPPLIERORDER
		WHERE IDSUPPLIERORDER = :OLD.IDSUPPLIERORDER;
		
		SELECT lastModifiedDate
			INTO v_lastModifiedDate
		FROM SUPPLIERORDER
		WHERE IDSUPPLIERORDER = :OLD.IDSUPPLIERORDER;
	ELSE
		SELECT idEmployeeModify
			INTO v_idEmployeeModify
		FROM SUPPLIERORDER
		WHERE IDSUPPLIERORDER = :NEW.IDSUPPLIERORDER;
		
		SELECT lastModifiedDate
			INTO v_lastModifiedDate
		FROM SUPPLIERORDER
		WHERE IDSUPPLIERORDER = :NEW.IDSUPPLIERORDER;
	END IF;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, v_entity, 'Update', v_idEmployeeModify, v_lastModifiedDate);
END;
/

/* CLIENTBILL */
CREATE OR REPLACE TRIGGER trg_log_ClientBill
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTBILL
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'ClientBill';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* CLIENTOFFER */
CREATE OR REPLACE TRIGGER trg_log_ClientOffer
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTOFFER
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'ClientOffer';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* CLIENTOFFER_ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_ClientOfferArticle
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTOFFER_ARTICLE
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
	v_idEmployeeModify INTEGER;
	v_lastModifiedDate DATE;
BEGIN
	v_entity := 'ClientOfferDetails';
	IF DELETING THEN
		SELECT idEmployeeModify
			INTO v_idEmployeeModify
		FROM CLIENTOFFER
		WHERE IDCLIENTOFFER = :OLD.IDCLIENTOFFER;
		
		SELECT lastModifiedDate
			INTO v_lastModifiedDate
		FROM CLIENTOFFER
		WHERE IDCLIENTOFFER = :OLD.IDCLIENTOFFER;
	ELSE
		SELECT idEmployeeModify
			INTO v_idEmployeeModify
		FROM CLIENTOFFER
		WHERE IDCLIENTOFFER = :NEW.IDCLIENTOFFER;
		
		SELECT lastModifiedDate
			INTO v_lastModifiedDate
		FROM CLIENTOFFER
		WHERE IDCLIENTOFFER = :NEW.IDCLIENTOFFER;
	END IF;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, v_entity, 'Update', v_idEmployeeModify, v_lastModifiedDate);
END;
/

/* OUTSTANDING */
CREATE OR REPLACE TRIGGER trg_log_Outstanding
AFTER INSERT OR UPDATE OR DELETE
ON OUTSTANDING
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Outstanding';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/

/* COMPANY */
CREATE OR REPLACE TRIGGER trg_log_Company
AFTER INSERT OR UPDATE OR DELETE
ON COMPANY
FOR EACH ROW

DECLARE
	v_entity VARCHAR2(100);
BEGIN
	v_entity := 'Company';
	CASE
		WHEN INSERTING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Insert', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN UPDATING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Update', :NEW.idEmployeeModify, :NEW.LastModifiedDate);
		WHEN DELETING THEN
			INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
			VALUES (log_seq.nextval, v_entity, 'Delete', :OLD.idEmployeeModify, :OLD.LastModifiedDate);
	END CASE;
END;
/