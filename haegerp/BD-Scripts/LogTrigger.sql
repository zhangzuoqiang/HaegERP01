CREATE SEQUENCE log_seq
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

/* EMPLOYEE_CREATE */
CREATE OR REPLACE TRIGGER trg_Creation_Employee
BEFORE INSERT
ON EMPLOYEE
FOR EACH ROW

DECLARE
	v_DateLastAction DATE;
BEGIN
	SELECT SysDate INTO v_DateLastAction FROM DUAL;
	:NEW.dateLastAction := v_DateLastAction;
END;
/

/* ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_Article
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLE
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Article', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* ARTICLEHISTORY */
CREATE OR REPLACE TRIGGER trg_log_ArticleHistory
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLEHISTORY
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ArticleHistory', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* ARTICLECATEGORY */
CREATE OR REPLACE TRIGGER trg_log_ArticleCategory
AFTER INSERT OR UPDATE OR DELETE
ON ARTICLECATEGORY
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ArticleCategory', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* BUSINESSPARTNER */
CREATE OR REPLACE TRIGGER trg_log_BusinessPartner
AFTER INSERT OR UPDATE OR DELETE
ON BUSINESSPARTNER
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'BusinessPartner', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* SUPPLIER */
CREATE OR REPLACE TRIGGER trg_log_Supplier
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIER
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Supplier', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* CLIENTCATEGORY */
CREATE OR REPLACE TRIGGER trg_log_ClientCategory
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTCATEGORY
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ClientCategory', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* CLIENT */
CREATE OR REPLACE TRIGGER trg_log_Client
AFTER INSERT OR UPDATE OR DELETE
ON CLIENT
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Client', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* SALARYCATEGORY */
CREATE OR REPLACE TRIGGER trg_log_SalaryCategory
AFTER INSERT OR UPDATE OR DELETE
ON SALARYCATEGORY
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'SalaryCategory', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* DIVISION */
CREATE OR REPLACE TRIGGER trg_log_Division
AFTER INSERT OR UPDATE OR DELETE
ON DIVISION
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Division', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* USERGROUP */
CREATE OR REPLACE TRIGGER trg_log_UserGroup
AFTER INSERT OR UPDATE OR DELETE
ON USERGROUP
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'UserGroup', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* EMPLOYEE */
CREATE OR REPLACE TRIGGER trg_log_Employee
AFTER INSERT OR UPDATE OR DELETE
ON EMPLOYEE
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	IF NOT UPDATING ('dateLastAction') THEN
		SELECT SysDate INTO v_SystemDate FROM Dual;
		SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
		SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
		
		INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
		VALUES (log_seq.nextval, 'Employee', v_operationType, v_IdEmployee, v_SystemDate);
	END IF;
END;
/

/* PERMISSION */
CREATE OR REPLACE TRIGGER trg_log_Permission
AFTER INSERT OR UPDATE OR DELETE
ON PERMISSION
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Permission', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* USERGROUP_PERMISSION */
CREATE OR REPLACE TRIGGER trg_log_UserGroupPermission
AFTER INSERT OR UPDATE OR DELETE
ON USERGROUP_PERMISSION
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'UserGroupPermission', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* SUPPLIERBILL */
CREATE OR REPLACE TRIGGER trg_log_SupplierBill
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERBILL
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'SupplierBill', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* SUPPLIERORDER */
CREATE OR REPLACE TRIGGER trg_log_SupplierOrder
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERORDER
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'SupplierOrder', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* SUPPLIERORDER_ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_SupplierOrderArticle
AFTER INSERT OR UPDATE OR DELETE
ON SUPPLIERORDER_ARTICLE
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'SupplierOrderArticle', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* CLIENTBILL */
CREATE OR REPLACE TRIGGER trg_log_ClientBill
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTBILL
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ClientBill', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* CLIENTOFFER */
CREATE OR REPLACE TRIGGER trg_log_ClientOffer
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTOFFER
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ClientOffer', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* CLIENTOFFER_ARTICLE */
CREATE OR REPLACE TRIGGER trg_log_ClientOfferArticle
AFTER INSERT OR UPDATE OR DELETE
ON CLIENTOFFER_ARTICLE
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'ClientOfferArticle', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* OUTSTANDING */
CREATE OR REPLACE TRIGGER trg_log_Outstanding
AFTER INSERT OR UPDATE OR DELETE
ON OUTSTANDING
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Outstanding', v_operationType, v_IdEmployee, v_SystemDate);
END;
/

/* COMPANY */
CREATE OR REPLACE TRIGGER trg_log_Company
AFTER INSERT OR UPDATE OR DELETE
ON COMPANY
FOR EACH ROW

DECLARE
	v_operationType VARCHAR2(20);
	v_DateLastAction DATE;
	v_IdEmployee INTEGER;
	v_SystemDate DATE;
BEGIN
	IF DELETING THEN
		v_operationType := 'Delete';
	ELSE
		IF UPDATING THEN
			v_operationType := 'Update';
		ELSE
			v_operationType := 'Insert';
		END IF;
	END IF;
	
	SELECT SysDate INTO v_SystemDate FROM Dual;
	SELECT MAX(DateLastAction) INTO v_DateLastAction FROM Employee;
	SELECT IdEmployee INTO v_IdEmployee FROM Employee WHERE DateLastAction = v_DateLastAction;
	
	INSERT INTO Log (IDLOG, ENTITY, OPERATION, IDEMPLOYEE, OPERATIONDATE)
	VALUES (log_seq.nextval, 'Company', v_operationType, v_IdEmployee, v_SystemDate);
END;
/