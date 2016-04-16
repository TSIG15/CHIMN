#!/usr/bin/python
# -*- coding: utf-8 -*-

import psycopg2
import psycopg2.extras
import os
import subprocess
import sys
sys.path.append("C:\\Program Files\\FME\\fmeobjects\\python27")
import fmeobjects

# ##### VARIABLES



# ###---------------------- RECUPERATION DES FORMATS ----------------------####

con = None

try:
    
    con = psycopg2.connect("dbname='chimn' user='postgres' port='5433'")
    
    cur = con.cursor()
    cur.execute("SELECT * FROM preferenceformat WHERE activated = 'True'")
    
    rows = cur.fetchmany(7)
    resultFormat=[]
    for row in rows:
        resultFormat.append(row[1])
		
    formatList = ""
    for i in range(0, len(resultFormat)):
        formatList = formatList + " " + resultFormat[i]
    #print formatList # parametre publie format

####---------------------- RECUPERATION DES SRS ----------------------####

    cur = con.cursor()
    cur.execute("SELECT code FROM preferencesrs WHERE preferencesrs.activate='True'")
    
    rows = cur.fetchmany(3)
    resultSRS=[]    
    for row in rows:
        resultSRS.append(row[0])
		
    srsList = ""
    for y in range(0, len(resultSRS)):
        srsList = srsList + " " + resultSRS[y]
    #print srsList # parametre publie SRS

#---------------------- RECUPERATION DES DONNEES ELIGIBLES ----------------------#

    cur = con.cursor()
    cur.execute("SELECT title FROM metadata WHERE metadata.changed ='True' AND metadata.asked = 'True'")
    
    rows = cur.fetchmany(3) #limite à revoir
    resultData=[]
    for row in rows:
        resultData.append(row[0])
	#print resultData
	
    # mdataList = ""
    # for z in range(0, len(resultData)):
        # mdataList = mdataList + " " + resultData[z]
    # print mdataList # Titre des donnees eligibles      

#---------------------- GESTION DES ERREURS ----------------------#
except psycopg2.DatabaseError, e:
    print 'Error %s' % e
    sys.exit(1)


finally:
    
    if con:
        con.close()

#---------------------- LANCEMENT DE FME AVEC PARAMETRES ----------------------#

#os.system("fme.exe C:\\Users\\ndrissi\\Downloads\\dynamic_format_list.fmw --FORMATS_LIST " + formatList + " --SRS_LIST " + srsList + " --POSTGIS_TABLE " + mdataList + " --DEST C:\\Users\\ndrissi\\Downloads --DestDataset_SHAPE C:\\Users\\ndrissi\\Downloads")
for p in range (0, len(resultData)-1):
	print resultData[p]
os.system("fme.exe C:\\Users\\ndrissi\\Downloads\\workspaceFME_testv1.fmw --FORMATS_LIST " + formatList + " --SRS_LIST " + srsList + " --POSTGIS_TABLE " + resultData[p] + " --DEST C:\\Users\\ndrissi\\Downloads\\test --LOG C:\\Users\\ndrissi\\Downloads\\log-" + str(p) + "")
