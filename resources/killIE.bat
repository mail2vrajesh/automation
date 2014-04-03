echo off
tasklist /fi "imagename eq IEDriverServer64.exe" |find ":" > nul
if errorlevel 1 taskkill /f /im "IEDriverServer64.exe"

tasklist /fi "imagename eq IEDriverServer32.exe" |find ":" > nul
if errorlevel 1 taskkill /f /im "IEDriverServer32.exe"

exit