/* ******************************************/
/* *			Dropping Script				*/
/* ******************************************/

DROP TABLE company;
DROP TABLE outstanding;
DROP TABLE clientoffer_article;
DROP TABLE clientoffer;
DROP TABLE clientbill;
DROP TABLE supplierorder_article;
DROP TABLE supplierorder;
DROP TABLE supplierbill;
DROP TABLE usergroup_permission;
DROP TABLE permission;
DROP TABLE log;
DROP TABLE employee;
DROP TABLE usergroup;
DROP TABLE division;
DROP TABLE salarycategory;
DROP TABLE client;
DROP TABLE clientcategory;
DROP TABLE supplier;
DROP TABLE businesspartner;
DROP TABLE articleHistory;
DROP TABLE article;
DROP TABLE articlecategory;
COMMIT;
/* ******************************************/
/* *				Articles				*/
/* ******************************************/

CREATE TABLE articlecategory(
	idArticleCategory	INTEGER			NOT NULL,
	name				VARCHAR2(50)	NOT NULL,
	description			VARCHAR(256),
	CONSTRAINT pk_articleCategory
		PRIMARY KEY (idArticleCategory)
);

CREATE TABLE article(
	idArticle			INTEGER			NOT NULL,
	idArticleCategory	INTEGER			NOT NULL,
	ean					INTEGER			NOT NULL,
	name				VARCHAR2(80)	NOT NULL,
	priceVat			NUMBER(5,2)		NOT NULL,
	priceGross			NUMBER(20,2)	NOT NULL,
	priceSupplier		NUMBER(20,2)	NOT NULL,
	stock				INTEGER,
	color				VARCHAR2(30),
	sizeH				NUMBER(20,3),
	sizeL				NUMBER(20,3),
	sizeW				NUMBER(20,3),
	description			VARCHAR2(256),
	CONSTRAINT pk_article
		PRIMARY KEY (idArticle),
	CONSTRAINT fk_article_articleCategory
		FOREIGN KEY (idArticleCategory)
		REFERENCES articlecategory (idArticleCategory)
);

CREATE TABLE articlehistory(
	idArticleHistory	INTEGER			NOT NULL,
	idArticle			INTEGER			NOT NULL,
	articleCategory		VARCHAR2(50)	NOT NULL,
	ean					INTEGER			NOT NULL,
	name				VARCHAR2(80)	NOT NULL,
	priceVat			NUMBER(5,2)		NOT NULL,
	priceGross			NUMBER(20,2)	NOT NULL,
	priceSupplier		NUMBER(20,2)	NOT NULL,
	CONSTRAINT pk_articleHistory
		PRIMARY KEY (idArticle, idArticleHistory)
);

/* ******************************************/
/* *			Business Partners			*/
/* ******************************************/

CREATE TABLE businesspartner(
	idBusinessPartner	INTEGER			NOT NULL,
	name				VARCHAR2(100)	NOT NULL,
	taxId				INTEGER			NOT NULL,
	address				VARCHAR2(100)	NOT NULL,
	zipCode				VARCHAR2(12)	NOT NULL,
	city				VARCHAR2(50)	NOT NULL,
	region				VARCHAR2(50),
	country				VARCHAR2(50)	NOT NULL,
	email				VARCHAR2(50)	NOT NULL,
	phoneNumber			VARCHAR2(20),
	mobileNumber		VARCHAR2(20),
	faxNumber			VARCHAR2(20),
	description			VARCHAR2(256),
	CONSTRAINT pk_businessPartner
		PRIMARY KEY (idBusinessPartner)
);

CREATE TABLE supplier(
	idBusinessPartner	INTEGER			NOT NULL,
	CONSTRAINT pk_supplier
		PRIMARY KEY (idBusinessPartner),
	CONSTRAINT fk_supplier_businessPartner
		FOREIGN KEY (idBusinessPartner)
		REFERENCES businesspartner (idBusinessPartner)
);

CREATE TABLE clientcategory(
	idClientCategory	INTEGER			NOT NULL,
	name				VARCHAR2(50)	NOT NULL,
	description			VARCHAR2(256),
	CONSTRAINT idClientCategory
		PRIMARY KEY (idClientCategory)
);

CREATE TABLE client(
	idBusinessPartner	INTEGER			NOT NULL,
	idClientCategory	INTEGER			NOT NULL,
	CONSTRAINT pk_client
		PRIMARY KEY (idBusinessPartner),
	CONSTRAINT fk_client_businessPartner
		FOREIGN KEY (idBusinessPartner)
		REFERENCES businesspartner (idBusinessPartner),
	CONSTRAINT fk_client_clientCategory
		FOREIGN KEY (idClientCategory)
		REFERENCES clientcategory (idClientCategory)
);

/* ******************************************/
/* *			Human Resources				*/
/* ******************************************/

CREATE TABLE salarycategory (
	idSalaryCategory	INTEGER			NOT NULL,
	salaryFrom			NUMBER(20,2)	NOT NULL,
	salaryTo			NUMBER(20,2)	NOT NULL,
	description			VARCHAR2(256),
	CONSTRAINT pk_salaryCategory
		PRIMARY KEY (idSalaryCategory)
);

CREATE TABLE division (
	idDivision		INTEGER			NOT NULL,
	name			VARCHAR2(50)	NOT NULL,
	description		VARCHAR2(256),
	CONSTRAINT pk_division
		PRIMARY KEY (idDivision)
);

CREATE TABLE usergroup (
	idUserGroup		INTEGER			NOT NULL,
	name			VARCHAR2(50)	NOT NULL,
	description		VARCHAR2(256),
	CONSTRAINT pk_usergroup
		PRIMARY KEY (idUserGroup)
);

CREATE TABLE employee (
	idEmployee			INTEGER			NOT NULL,
	idSalaryCategory	INTEGER			NOT NULL,
	idDivision			INTEGER			NOT NULL,
	idUserGroup			INTEGER			NOT NULL,
	username			VARCHAR2(50)	NOT NULL,
	password			VARCHAR2(32)	NOT NULL,
	idCard				INTEGER			NOT NULL,
	name				VARCHAR2(100)	NOT NULL,
	address				VARCHAR2(100)	NOT NULL,
	zipCode				VARCHAR2(15)	NOT NULL,
	city				VARCHAR2(30)	NOT NULL,
	region				VARCHAR2(30),
	country				VARCHAR2(30)	NOT NULL,
	email				VARCHAR2(50),
	phoneNumber			VARCHAR2(20),
	mobileNumber		VARCHAR2(20),
	dateLastAction		DATE,
	CONSTRAINT pk_employee
		PRIMARY KEY (idEmployee),
	CONSTRAINT fk_employee_salaryCategory
		FOREIGN KEY (idSalaryCategory)
		REFERENCES salarycategory (idSalaryCategory),
	CONSTRAINT fk_employee_division
		FOREIGN KEY (idDivision)
		REFERENCES division (idDivision),
	CONSTRAINT fk_employee_userGroup
		FOREIGN KEY (idUserGroup)
		REFERENCES usergroup (idUserGroup)
);

CREATE TABLE permission (
	idPermission	INTEGER			NOT NULL,
	moduleName		VARCHAR2(50)	NOT NULL,
	CONSTRAINT pk_permission
		PRIMARY KEY (idPermission)
);

CREATE TABLE usergroup_permission (
	idUserGroup		INTEGER		NOT NULL,
	idPermission	INTEGER		NOT NULL,
	CONSTRAINT pk_usergroup_permission
		PRIMARY KEY (idUserGroup, idPermission),
	CONSTRAINT fk_usergroup_permission
		FOREIGN KEY (idUserGroup)
		REFERENCES usergroup (idUserGroup),
	CONSTRAINT fk_permission_usergroup
		FOREIGN KEY (idPermission)
		REFERENCES permission (idPermission)
);

/* ******************************************/
/* *			Supplier Orders				*/
/* ******************************************/

CREATE TABLE supplierbill (
	idSupplierBill		INTEGER		NOT NULL,
	receivedDate		DATE		NOT NULL,
	paidDate			DATE,
	CONSTRAINT pk_supplierbill
		PRIMARY KEY (idSupplierBill)
);

CREATE TABLE supplierorder (
	idSupplierOrder		INTEGER		NOT NULL,
	idSupplierBill		INTEGER,
	idBusinessPartner	INTEGER		NOT NULL,
	idEmployee			INTEGER		NOT NULL,
	orderDate			DATE		NOT NULL,
	total				NUMBER(20,2),
	sendDate			DATE,
	description			VARCHAR2(256),
	CONSTRAINT pk_supplierorder
		PRIMARY KEY (idSupplierOrder),
	CONSTRAINT fk_supplierorder_supplierbill
		FOREIGN KEY (idSupplierBill)
		REFERENCES supplierbill (idSupplierBill),
	CONSTRAINT fk_supplierorder_supplier
		FOREIGN KEY (idBusinessPartner)
		REFERENCES supplier (idBusinessPartner),
	CONSTRAINT fk_supplierorder_employee
		FOREIGN KEY (idEmployee)
		REFERENCES employee (idEmployee)
);

CREATE TABLE supplierorder_article (
	idSupplierOrder		INTEGER			NOT NULL,
	idArticle			INTEGER			NOT NULL,
	idArticleHistory	INTEGER			NOT NULL,
	quantity			INTEGER			NOT NULL,
	discount			NUMBER(5,3)		NOT NULL,
	totalArticle		NUMBER(20,2)	NOT NULL,
	CONSTRAINT pk_supplierorder_article
		PRIMARY KEY (idSupplierOrder, idArticle, idArticleHistory),
	CONSTRAINT fk_supplierorder_article
		FOREIGN KEY (idSupplierOrder)
		REFERENCES supplierorder (idSupplierOrder),
	CONSTRAINT fk_articles_supplierorder
		FOREIGN KEY (idArticle, idArticleHistory)
		REFERENCES articlehistory (idArticle, idArticleHistory)
);

/* ******************************************/
/* *			Clients Orders				*/
/* ******************************************/

CREATE TABLE clientbill (
	idClientBill		INTEGER		NOT NULL,
	billedDate			DATE		NOT NULL,
	paidDate			DATE,
	CONSTRAINT pk_clientbill
		PRIMARY KEY (idClientBill)
);

CREATE TABLE clientoffer (
	idClientOffer		INTEGER		NOT NULL,
	idClientBill		INTEGER,
	idBusinessPartner	INTEGER		NOT NULL,
	idEmployee			INTEGER		NOT NULL,
	offerDate			DATE		NOT NULL,
	total				NUMBER(20,2),
	sendDate			DATE,
	description			VARCHAR2(256),
	CONSTRAINT pk_clientoffer
		PRIMARY KEY (idClientOffer),
	CONSTRAINT fk_clientoffer_clientbill
		FOREIGN KEY (idClientBill)
		REFERENCES clientbill (idClientBill),
	CONSTRAINT fk_clientoffer_client
		FOREIGN KEY (idBusinessPartner)
		REFERENCES client (idBusinessPartner),
	CONSTRAINT fk_clientoffer_employee
		FOREIGN KEY (idEmployee)
		REFERENCES employee (idEmployee)
);

CREATE TABLE clientoffer_article (
	idClientOffer		INTEGER			NOT NULL,
	idArticle			INTEGER			NOT NULL,
	idArticleHistory	INTEGER			NOT NULL,
	quantity			INTEGER			NOT NULL,
	discount			NUMBER(5,3)		NOT NULL,
	totalArticle		NUMBER(20,2)	NOT NULL,
	CONSTRAINT pk_clientoffer_article
		PRIMARY KEY (idClientOffer, idArticle, idArticleHistory),
	CONSTRAINT fk_clientoffer_article
		FOREIGN KEY (idClientOffer)
		REFERENCES clientoffer (idClientOffer),
	CONSTRAINT fk_article_clientoffer
		FOREIGN KEY (idArticle, idArticleHistory)
		REFERENCES articleHistory (idArticle, idArticleHistory)
);

CREATE TABLE outstanding (
	idOutstanding		INTEGER		NOT NULL,
	idClientBill		INTEGER		NOT NULL,
	expireDate			DATE		NOT NULL,
	emailDate			DATE,
	CONSTRAINT pk_outstanding
		PRIMARY KEY (idOutstanding),
	CONSTRAINT fk_outstanding_clientbill
		FOREIGN KEY (idClientBill)
		REFERENCES clientbill (idClientBill)
);

/* ******************************************/
/* *				Company					*/
/* ******************************************/

CREATE TABLE company (
	idCompany		INTEGER			NOT NULL,
	name			VARCHAR2(50),
	taxID			INTEGER,
	owner			VARCHAR2(100),
	sector			VARCHAR2(100),
	address			VARCHAR2(100),
	zipCode			VARCHAR2(15),
	city			VARCHAR2(30),
	region			VARCHAR2(50),
	country			VARCHAR2(30),
	phoneNumber		VARCHAR2(20),
	faxNumber		VARCHAR2(20),
	CONSTRAINT pk_company
		PRIMARY KEY (idCompany)
);

/* ******************************************/
/* *				Logging					*/
/* ******************************************/

CREATE TABLE log (
	idLog			INTEGER			NOT NULL,
	entity			VARCHAR2(100)	NOT NULL,
	operation		VARCHAR2(20)	NOT NULL,
	idEmployee		INTEGER			NOT NULL,
	operationDate	DATE			NOT NULL,
	CONSTRAINT pk_logs
		PRIMARY KEY (idLog),
	CONSTRAINT fk_log_employee
		FOREIGN KEY (idEmployee)
		REFERENCES employee (idEmployee)
);
COMMIT;
/* ******************************************/
/* *			Data Insertion				*/
/* ******************************************/

/* **********************************************/
/* *				Triggers					*/
/* **********************************************/

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

/* **********************************************/
/* *				Permission					*/
/* **********************************************/

INSERT INTO permission (idPermission, moduleName)
VALUES (1, 'Articles Administration');

INSERT INTO permission (idPermission, moduleName)
VALUES (2, 'Business Partners Administration');

INSERT INTO permission (idPermission, moduleName)
VALUES (3, 'Human Resources Administration');

INSERT INTO permission (idPermission, moduleName)
VALUES (4, 'Client Orders Administration');

INSERT INTO permission (idPermission, moduleName)
VALUES (5, 'Suppliers Order Administration');

INSERT INTO permission (idPermission, moduleName)
VALUES (6, 'Company Administration');

ALTER TABLE permission READ ONLY;

/* **********************************************/
/* *				Employee					*/
/* **********************************************/

INSERT INTO SALARYCATEGORY
(SALARYFROM, SALARYTO, IDSALARYCATEGORY)
VALUES
(30000, 40000, 1);

INSERT INTO DIVISION
(NAME, IDDIVISION)
VALUES
('Administration', 1);

INSERT INTO USERGROUP
(NAME, IDUSERGROUP)
VALUES ('Administration', 1);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 1);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 2);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 3);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 4);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 5);

INSERT INTO USERGROUP_PERMISSION
(IDUSERGROUP, IDPERMISSION)
VALUES (1, 6);

INSERT INTO EMPLOYEE
(IDSALARYCATEGORY, IDDIVISION, IDUSERGROUP, IDCARD, NAME, ADDRESS, ZIPCODE, CITY, COUNTRY, USERNAME, PASSWORD, IDEMPLOYEE)
VALUES
(1, 1, 1, 123456789, 'Ze Mario', 'Konigswinter Str. 200', '53299', 'Bonn', 'Deutschland', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);

/* **********************************************/
/* *				Company						*/
/* **********************************************/

INSERT INTO company(idCompany)
VALUES(1);

/* **********************************************/
/* *				Outstanding					*/
/* **********************************************/

INSERT INTO ArticleCategory (IDARTICLECATEGORY, NAME)
VALUES (0, 'Outstanding');

INSERT INTO Article (IDARTICLE, IDARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
VALUES (0, 0, 0000000000000, 'Outstanding Surcharge', 17, 20, 0);

INSERT INTO ArticleHistory (IDARTICLEHISTORY, IDARTICLE, ARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
VALUES (1, 0, 'Outstanding', 0000000000000, 'Outstanding Surcharge', 17, 20, 0);

COMMIT;

/* **********************************************/
/* *			Logs Triggers					*/
/* **********************************************/

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
