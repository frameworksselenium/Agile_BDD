cd\
cd E:\Selenium\WorkSpace\Agile_BDD\src\test\resources\drivers
java -Dwebdriver.chrome.driver="E:\Selenium\WorkSpace\Agile_BDD\src\test\resources\drivers\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node  -hub http://localhost:4444/grid/register -port 4455 -browser browserName=chrome,maxInstances=2 -maxSession 2 -timeout 20000
pause