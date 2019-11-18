<img src="https://stormchasersdigital.com/wp-content/uploads/2017/07/Are-You-In-The-Google-Index.jpg" height="64px"/>
<br><br/>
Приложение для добавления и отображени списка книг и их авторов.

# Как использовать
Для данного приложения будет использоваться БД PostgreSQL. Проект написан на Java 8.
## Установка и настройка БД в ОС Windows 10.
Установите PostgreSQL. Для этого перейдите на страницу [загрузки](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads). 
Скачайте версию 11.5, для Windows.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/iBFihQeT.png)
<br><br/>
Запустите скачаный файл. После этого откроется окно приветствия, можете ничего не менять, пока не дойдете до окна с 
вводом пароля.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/6iufKE8f.png)
<br><br/>
Задайте пароль для пользователя postgres. Пароль root введите в 2 поля. Нажмите «Next».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/DVca9s9T.png)
<br><br/>
Оставьте по умолчанию порт на котором будет работать PostgreSQL 5432. Нажимайте «Next» пока не пойдет процесс установки.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/WWPoiHQb.png)
<br><br/>
Подождите окончания установки.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/4bip9L3T.png)
<br><br/>
Установка завершена. Снимите галочку. Нажмите «Finish».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/CVAokXtE.png)
<br><br/>
Запустите pgAdmin. Нажмите «Меню Пуск — > PostgreSQL 11 -> pgAdmin 4».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/QZOLjHxG.png)
<br><br/>
Во всплывающем окне введите root. Нажмите «OK».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/amQnsBRV.png)
<br><br/>
В левой части открывшегося окна нажмите по стрелке слева от иконки «Servers». Если появится всплывающее окно, то введите 
пароль root.
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/Ga3sCCzf.png)
<br><br/>
Создайте базу данных. Для этого в развернутом списке серверов нажмите правой кнопкой по пункту PostgreSQL 11 и выберите 
пункт «Create -> Database...».
<br><br/>
![alt text](http://skrinshoter.ru/i/221019/GHkDu31P.png)
<br><br/>
В открывшемся окне, в поле Database введите books_catalogue. Нажмите кнопку «Save».
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/IBHHMYFW.png)
<br><br/>
## Сборка и старт проекта
<br><br/>
Перейдите в папку куда склонировали проект. Зажмите «Shift» и кликните правой кнопкой мыши на пустом месте. В выпадающем 
меню выберите «Открыть окно команд». 
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/NiWCKS9I.png)
<br><br/>
Выполните команду `mvn -Dflyway.configFiles=flywayConfig.conf flyway:migrate` Накатятся миграции БД.
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/9wbrtkVR.png)
<br><br/>
Затем выполните команду `mvn tomcat7:run -f pom.xml` - запуск проекта.
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/ah9C831f.png)
<br><br/>
## Как пользоваться
В браузере перейдите по URL [http://localhost:8080/index](http://localhost:8080/index)
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/XPrYZbgX.png)
<br><br/>
Нажмите на ссылку "Добавление книги".
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/otm5PsVw.png)
<br><br/>
Заполните все поля и нажмите на кнопку "Добавить".
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/65vWiW8t.png)
<br><br/>
Произойдет перенаправление на страницу со списком книг
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/YY0bsRUo.png)
<br><br/>
На главной странице отображается список 10 последних добавленных книг
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/HvUU09Ki.png)
<br><br/>
На странице авторы отображается список авторов по алфавиту
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/Nc13gTsZ.png)
<br><br/>
Так же реализован поиск по фамилии автора без учета регистра по частичному совпадению
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/PKnVQMlL.png)
<br><br/>
При нажатии на автора выдается список книг данного автора
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/v5g9QYM2.png)
<br><br/>
На странице книг - список книг отображается по алфавиту
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/JMQv19RQ.png)
<br><br/>
Так же реализован поиск по наименованию книги без учета регистра по частичному совпадению
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/U1Rfg3Ro.png)
<br><br/>
При нажатии на книгу открывается страница с информацией о книге (название, автор/авторы)
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/cRXk56pq.png)
<br><br/>
При нажатии на автора реализован переход на страницу данного автора
<br><br/>
![alt text](http://skrinshoter.ru/i/181119/OmdpDKHD.png)
<br><br/>
