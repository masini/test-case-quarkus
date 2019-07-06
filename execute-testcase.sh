#!/usr/bin/env bash

curl -v -X POST -H "Content-Type: application/json" -d '{
  "descrizione": "Description",
  "filiale": "1",
  "id": "K-999",
  "numero": 999,
  "sigla": "SIG",
  "societa": "K",
  "zona": 999
}' http://localhost:8080/layout/bar

echo ''

curl -v -X POST -H "Content-Type: application/json" -d '{
    "bar": {
      "descrizione": "Description",
      "filiale": "1",
      "id": "K-999",
      "numero": 999,
      "sigla": "SIG",
      "societa": "K",
      "zona": 999
    },
    "flusso": "FRONT",
    "hasApp": false,
    "hasMonitorVassoio": false,
    "hasPdaBevande": false,
    "maxComandeVassoio": 8,
    "orarioAperturaBar": "09:00",
    "orarioAperturaCucina": "11:30",
    "orarioChiusuraCucina": "15:00",
    "tolleranza": 24,
    "version": 1
}' http://localhost:8080/layout

echo ''

curl -v -X POST -H "Content-Type: application/json" -d '{
    "bar": {
      "descrizione": "Description",
      "filiale": "1",
      "id": "K-999",
      "numero": 999,
      "sigla": "SIG",
      "societa": "K",
      "zona": 999
    },
    "flusso": "FRONT",
    "hasApp": false,
    "hasMonitorVassoio": false,
    "hasPdaBevande": false,
    "maxComandeVassoio": 8,
    "orarioAperturaBar": "09:00",
    "orarioAperturaCucina": "11:30",
    "orarioChiusuraCucina": "15:00",
    "tolleranza": 24,
    "version": 1
}' http://localhost:8080/layout

echo ''
echo 'this should fail !!'

curl -v -X POST -H "Content-Type: application/json" -d '{
    "bar": {
      "descrizione": "Description",
      "filiale": "1",
      "id": "K-999",
      "numero": 999,
      "sigla": "SIG",
      "societa": "K",
      "zona": 999
    },
    "flusso": "FRONT",
    "hasApp": false,
    "hasMonitorVassoio": false,
    "hasPdaBevande": false,
    "maxComandeVassoio": 8,
    "orarioAperturaBar": "09:00",
    "orarioAperturaCucina": "11:30",
    "orarioChiusuraCucina": "15:00",
    "tolleranza": 24,
    "version": 1
}' http://localhost:8080/layout

echo ''
echo 'now comment JsonbContextResolver line 26 and it will fail with 409'

