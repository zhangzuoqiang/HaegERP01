@echo off
net stop OracleServiceHAEGERP
net stop OracleOraDb11g_home1TNSListener
net stop OracleJobSchedulerHAEGERP
net stop OracleDBConsolehaegerp
net stop OracleOraDb11g_home1ClrAgent
net stop OracleMTSRecoveryService
net stop OracleVssWriterHAEGERP