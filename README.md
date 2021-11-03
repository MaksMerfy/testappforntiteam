# Test app for company Nti.team

### Задача :
Поддержать методы API:
- Добавить нового Повелителя
- Добавить новую Планету
- Назначить Повелителя управлять Планетой
- Уничтожить Планету
- Найти всех Повелителей бездельников, которые прохлаждаются и не управляют никакими Планетами
- Отобразить ТОП 10 самых молодых Повелителей

### Решение :
Приложение построено с использованием Spring Boot, Hibernate и PostgreSQL.
Адрес сервера базы данных 194.58.101.68.
На сервере используется PostgreSQL 13.4.
Основная база данных приложения "forntiteam".
Основная база данных приложения для тестов "forntiteamtest".

Основные HTTP :
* Get
    * /overlord
    * /overlord/{name}
    * /loafers
    * /planet/{name}
* Post
    * /overlord
    * /planet
    * /planet/setoverlord
    * /planet/destroy

## Описание эндпоинтов :

### Get "/overlord"
####Создан для получения 10 самых молодых правителей.
Возвращает json {"youngOverlords": [{"name": "","age": 0}, ...] }. Если данных нет, то вернет {"youngOverlords": [] }
### Get "/overlord/{name}"
####Создан для получения информации по конкретному правителю. Так же возвращает список планет за которые от отвечает
Возвращает json {"name": "","age": 0,"planets": ["", ...]}
### Get "/loafers"
####Создан для отображения всех бездельников
Возвращает json {"listLoafers": ["", ...]}
### Get "/planet/{name}"
####Создан для отображения информации по планете
Возвращает json {"name": "","overlord": ""}

### Post "/overlord"
####Создан для добавления нового правителя
* На вход принимаются json в формате :
    * {"name": "ИмяПравителя","age": ВозрастПравителя}
* В ответ получаем json :
    * {"status": "HttpStatus.name","errorMessages": ["", ...]}
    * Пример ответа, если все успешно : {"status": "OK","errorMessages": []}
    * Привет ответа, если есть ошибки : {"status": "BAD_REQUEST","errorMessages": ["Overlord is exist in base","age must be above 0"]}
### Post "/planet"
####Создан для добавления новой планеты
* На вход принимаются json в формате :
    * {"name": "ИмяПланеты","overlord": "ИмяПравителя"}
    * Правитель не обязателен для заполнения, но если он заполнен и есть в базе то он будет сразу назначен правителем планеты
* В ответ получаем json :
    * {"status": "HttpStatus.name","errorMessages": ["", ...]}
    * Пример ответа, если все успешно : {"status": "OK","errorMessages": []}
    * Привет ответа, если есть ошибки : {"status": "BAD_REQUEST","errorMessages": ["Planet is exist in base","Can't find overlord"]}
### Post "/planet/setoverlord"
####Создан для назначения правителя на планету
* На вход принимаются json в формате :
    * {"name": "ИмяПланеты","overlord": "ИмяПравителя"}
* В ответ получаем json :
    * {"status": "HttpStatus.name","errorMessages": ["", ...]}
    * Пример ответа, если все успешно : {"status": "OK","errorMessages": []}
    * Привет ответа, если есть ошибки : {"status": "BAD_REQUEST","errorMessages": ["Planet is exist in base","Can't find overlord"]}
### Post "/planet/destroy"
####Создан для назначения правителя на планету
* На вход принимаются json в формате :
    * {"name": "ИмяПланеты"}
* В ответ получаем json :
    * {"status": "HttpStatus.name","errorMessages": ["", ...]}
    * Пример ответа, если все успешно : {"status": "OK","errorMessages": []}
    * Привет ответа, если есть ошибки : {"status": "BAD_REQUEST","errorMessages": ["name not valid","Can't find planet"]}

## Запуск приложения

* Запуск возможен через idea.
* Протестировать приложение можно по адресу https://testappforntiteam.jelastic.regruhosting.ru/