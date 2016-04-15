@ECHO OFF
title FME_launch
mode con cols=220 lines=36

FME C:\Users\ndrissi\Downloads\dynamic_format_list.fmw --FORMATS_LIST dwgshp^
	--SRS_LIST Lambert93WGS84^
	--POSTGIS_TABLE equipement_public.construction_lineare^
	--DEST C:\Users\ndrissi\Downloads\^
	--DestDataset_SHAPE C:\Users\ndrissi\Downloads\	


PAUSE