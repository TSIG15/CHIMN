#!/Library/FME/2016.0/fmeobjects/python27
#!/Library/FME/2016.0/plugins
#!/Library/FME/2016.0/python
#!/Library/FME/2016.0/python/python27
# -*- coding: utf-8 -*-

# TUTORIEL : http://zetcode.com/db/postgresqlpythontutorial/ #
# COMMANDES PSQL : http://docs.postgresql.fr/8.0/app-psql.html #

import psycopg2
import psycopg2.extras
import os
import sys
#sys.path.append(":/Library/FME/2016.0/fmeobjects/python27")
#import fmeobjects
import subprocess
#import MonitorClass
#logger = pyfme.FMELogFile()


####---------------------- RECUPERATION DES FORMATS ----------------------####

con = None

try:
    
    con = psycopg2.connect("dbname='chimn' user='postgres'")
    
    cur = con.cursor()
    cur.execute("SELECT * FROM preferenceformat WHERE activated = 'True'")
    
    rows = cur.fetchmany(7)

    for row in rows:
        resultFormat = row[1]

        print resultFormat # parametre publie format

####---------------------- RECUPERATION DES SRS ----------------------####

    cur = con.cursor()
    cur.execute("SELECT code FROM preferencesrs WHERE preferencesrs.activate='True'")
    
    rows = cur.fetchmany(3)
    
    for row in rows:
        resultSRS = row[0]
        
        print resultSRS # parametre publie : SRS


#---------------------- RECUPERATION DES DONNEES ELIGIBLES ----------------------#

    cur = con.cursor()
    cur.execute("SELECT title FROM metadata WHERE metadata.changed ='True' AND metadata.asked = 'True'")
    
    rows = cur.fetchmany(3)
    
    for row in rows:
        resultData = row[0]
        
        print resultData # Titre des donnees eligibles

#---------------------- GESTION DES ERREURS ----------------------#
except psycopg2.DatabaseError, e:
    print 'Error %s' % e
    sys.exit(1)


finally:
    
    if con:
        con.close()

#---------------------- LANCEMENT DE FME AVEC PARAMETRES ----------------------#
#os.system('exec ./postgis2esri.fmw --FORMATS_LIST dwgshp --SRS_LIST Lambert93WGS84 --POSTGIS_TABLE route.aire_triage --DEST /Users/nisrinedrissi/Documents/Programmation/projectTSIG/projectTSIG/test --DestDataset_SHAPE Users/nisrinedrissi/Documents/

#VOIR fmetest.py

