/* ******************************************/
/* *			Data Insertion				*/
/* ******************************************/

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

/* ******************************************/
/* *				Company					*/
/* ******************************************/

INSERT INTO company(idCompany)
VALUES(1);

/* ******************************************/
/* *			Outstanding					*/
/* ******************************************/

INSERT INTO ArticleCategory (IDARTICLECATEGORY, NAME)
VALUES (0, 'Outstanding');

INSERT INTO Article (IDARTICLE, IDARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
VALUES (0, 0, 0000000000000, 'Outstanding Surcharge', 17, 20, 0);

INSERT INTO ArticleHistory (IDARTICLEHISTORY, IDARTICLE, IDARTICLECATEGORY, EAN, NAME, PRICEVAT, PRICEGROSS, PRICESUPPLIER)
VALUES (1, 0, 0, 0000000000000, 'Outstanding Surcharge', 17, 20, 0);