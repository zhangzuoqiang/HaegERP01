/* ******************************************/
/* *			Data Insertion				*/
/* ******************************************/

/* **********************************************/
/* *				Permission					*/
/* **********************************************/

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (1, 'Articles Administration', SYSDATE, 1);

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (2, 'Business Partners Administration', SYSDATE, 1);

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (3, 'Human Resources Administration', SYSDATE, 1);

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (4, 'Client Orders Administration', SYSDATE, 1);

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (5, 'Suppliers Order Administration', SYSDATE, 1);

INSERT INTO permission (idPermission, moduleName, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (6, 'Company Administration', SYSDATE, 1);

ALTER TABLE permission READ ONLY;

/* **********************************************/
/* *				Employee					*/
/* **********************************************/

INSERT INTO SALARYCATEGORY
(SALARYFROM, SALARYTO, IDSALARYCATEGORY, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES
(30000, 40000, 1, SYSDATE, 1);

INSERT INTO DIVISION
(NAME, IDDIVISION, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES
('Administration', 1, SYSDATE, 1);

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
(IDSALARYCATEGORY, IDDIVISION, IDUSERGROUP, IDCARD, NAME, ADDRESS, ZIPCODE, CITY, COUNTRY, USERNAME, PASSWORD, IDEMPLOYEE, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES
(1, 1, 1, 123456789, 'Ze Mario', 'Konigswinter Str. 200', '53299', 'Bonn', 'Deutschland', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, SYSDATE, 1);

/* ******************************************/
/* *				Company					*/
/* ******************************************/

INSERT INTO company(idCompany)
VALUES(1);

/* ******************************************/
/* *			Outstanding					*/
/* ******************************************/

INSERT INTO ArticleCategory (IDARTICLECATEGORY, NAME, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (0, 'Outstanding', SYSDATE, 1);

INSERT INTO Article (IDARTICLE, IDARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER, STOCK, SIZEH, SIZEL, SIZEW, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (0, 0, 0000000000000, 'Outstanding Surcharge', 17, 20, 0, 0, 0, 0, 0, SYSDATE, 1);

INSERT INTO ArticleHistory (IDARTICLEHISTORY, IDARTICLE, ARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER, LASTMODIFIEDDATE, IDEMPLOYEEMODIFY)
VALUES (1, 0, 'Outstanding', 0000000000000, 'Outstanding Surcharge', 17, 20, 0, SYSDATE, 1);