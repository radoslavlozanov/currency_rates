# Requirements
Заданието цели създаване на application използвайки Spring/Spring Boot, който да събира данни за валутни курсове от доставчик,  да ги съхранява в локална база от данни и да акумулира репорти за дадени периоди върху данните.

  1. Да се изгради локална база данни MySQL или Postgres и да се направи data model за запазване на информация, която ще се вземе от fixer.io

  2. Да се изгради Currency Rest API с методи:<br>
   /currencies  -  return list with all currencies<br>
   /rates/historic/{base} - return all exchange rates from 1999 with {base} <br>
   /rates/historic/{base}/{date}  -   return all exchange rates from {date} with {base}<br>
   /rates/latest/{base} -  return all exchange rates for current day with {base}<br>
   /report/{currency}/{date} -  return all rates for {currency} with series per day with base = EUR<br>
   <br>
   Насоки:
   За извличане на данните трябва да се използва API на fixer.io. 
   За методите /currecies и /rates application-a може да бъде използван като proxy на fixer.io
   За методите /report application-a трябва да обработи запазените данни и да покаже акумулирания резултат.
   
# How to run
### Start local persist docker driver
There is configuration in docker-compose.yml. In order to work initially local persist driver is needed.
It is not native on windows and mac, so start relevant file - start_local_persist_mac.sh or start_local_persist_windows.cmd.
### Start database from docker-compose.yml 
To do that start following command in main project folder:<br> 
docker-compose -f docker-compose.yml up -d mysql
## Start app
Use spring boot maven plugin. Execute following command:<br>
mvn spring-boot:run
 
