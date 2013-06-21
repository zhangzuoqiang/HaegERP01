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
	idArticleCategory	INTEGER			NOT NULL,
	ean					INTEGER			NOT NULL,
	name				VARCHAR2(80)	NOT NULL,
	priceVat			NUMBER(5,2)		NOT NULL,
	priceGross			NUMBER(20,2)	NOT NULL,
	priceSupplier		NUMBER(20,2)	NOT NULL,
	CONSTRAINT pk_articleHistory
		PRIMARY KEY (idArticle, idArticleHistory),
	CONSTRAINT fk_articleH_articleCategory
		FOREIGN KEY (idArticleCategory)
		REFERENCES articlecategory (idArticleCategory)
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
	paidDate			DATE		NOT NULL,
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