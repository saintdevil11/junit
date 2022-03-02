Проект пример

Сборка: java8, junit5, selenide, rest assured, allure
Сборка выполнена на указании входных констант через props которые берутся из test.properties

#Запуск - 
mvn clean test -Dgroups=1api

#Построение отчета -
mvn allure:serve

#Пареллельный запуск - 
в pom файле меняется значение у parallelCount
или в строке запуска -DparallelCount=3

#Для запуска на selenoid
локально: в проперти разкомментировать строку remote.url
или в строке запуска -Dremote.url=http://*******

#запуски без указанных переменных в переменных средах винды
set path=%path%;C:\apache-maven-3.6.3\bin
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_241