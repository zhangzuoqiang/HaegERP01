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