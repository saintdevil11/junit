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