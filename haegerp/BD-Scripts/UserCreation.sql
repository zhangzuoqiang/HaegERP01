/*
Open SQLPlus

User: sys as sysdba
Password: <The one you specified in the installation>

*/

CREATE USER haegerp_admin
IDENTIFIED BY haegerOracleDatabaseERP;

GRANT CONNECT, RESOURCE
TO haegerp_admin;
