#!/usr/bin/python
# -*- coding: utf-8 -*-

import psycopg2
import psycopg2.extras
import os
import subprocess
import sys
sys.path.append("C:\\Program Files\\FME\\fmeobjects\\python27")
import fmeobjects

# ----------------------##### VARIABLES #####---------------------- #
#
# formatList : liste des formats determines par l'admin dans la BDD
# srsList : liste des SRS determines par l'admin dans la BDD
# mdataList : liste des donnees eligibles
# cheminTelevers : chemin vers le serveur des données extraites determine par l'admin dans la BDD
#
# ###---------------------- RECUPERATION DES FORMATS ----------------------### #

con = None

try:
    
    con = psycopg2.connect("dbname='chimn2' user='postgres' port='5433'")
    
    cur = con.cursor()
    cur.execute("SELECT nameformat FROM preferenceformat WHERE activateformat = 'True'")
    
    rows = cur.fetchmany(7)
    resultFormat=[]
	
    formatList = ""
    for row in rows:
        resultFormat.append(row[0])

    for i in range(0, len(resultFormat)):
        formatList = formatList + resultFormat[i]
    print formatList # parametre publie format

# ###---------------------- RECUPERATION DES SRS ----------------------### #

    cur = con.cursor()
    cur.execute("SELECT epsg FROM preferencesrs WHERE activatesrs='True'")
    
    rows = cur.fetchmany(3)
    resultSRS=[]

    srsList = ""
    for row in rows:
        srsList = srsList + str(row[0])
    print srsList

# #---------------------- RECUPERATION DES DONNEES ELIGIBLES ----------------------# #

    cur = con.cursor()
    cur.execute("SELECT title FROM metadata WHERE metadata.changed ='True' AND metadata.asked = 'True'")
    
    rows = cur.fetchmany(3) #limite à revoir
    resultData=[]
    for row in rows:
        resultData.append(row[0])
	
    mdataList = ""
    for z in range(0, len(resultData)):
        mdataList = mdataList + " " + resultData[z]
        # print resultData[z]
        # resultData[z].split('.',2)
        # for partie in resultData[z].split('.',2) :
			# print partie
    print mdataList # Titre des donnees eligibles      

	
# #---------------------- RECUPERER CHEMIN SERVEUR EXTRACTION DONNEE ----------------------# #
    cur = con.cursor()
    cur.execute("SELECT tlurl FROM parameters")
    
    rows = cur.fetchmany(3) #limite à revoir
    resultchemin=[]
    for row in rows:
        resultchemin.append(row[0])
	
    cheminTelevers = ""
    for j in range(0, len(resultchemin)):
        cheminTelevers = resultchemin[j]
    print cheminTelevers # Titre des donnees eligibles  
	
# #---------------------- GESTION DES ERREURS ----------------------# #
except psycopg2.DatabaseError, e:
    print 'Error %s' % e
    sys.exit(1)


finally:
    
    if con:
        con.close()

#os.system("fme.exe C:\\Users\\ndrissi\\Downloads\\dynamic_format_list.fmw --FORMATS_LIST " + formatList + " --SRS_LIST " + srsList + " --POSTGIS_TABLE " + mdataList + " --DEST C:\\Users\\ndrissi\\Downloads --DestDataset_SHAPE C:\\Users\\ndrissi\\Downloads")
for p in range (0, len(resultData)):
	print resultData[p]
#os.system("fme.exe C:\\Users\\ndrissi\\Downloads\\workspaceFME_testv1.fmw --FORMATS_LIST " + formatList + " --SRS_LIST " + srsList + " --POSTGIS_TABLE " + resultData[p] + " --DEST " + cheminTelevers + " --LOG C:\\Users\\ndrissi\\Downloads\\log-" + str(p) + "")
os.system("fme.exe C:\\Users\\ndrissi\\Downloads\\workspaceFME_testv2.fmw --FORMATS_LIST " + formatList + " --SRS_LIST " + srsList + " --POSTGIS_TABLE " + resultData[p] + " --DEST " + cheminTelevers + " --LOG " + cheminTelevers + "\\log-" + str(p) + "")