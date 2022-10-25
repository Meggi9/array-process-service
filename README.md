# RESTfull API service для обработки последовательности чисел

#### Документация Swagger: `http://localhost:8080/swagger-ui/index.html#/`
#### Ссылка на видео с результатом работы: https://drive.google.com/file/d/1ktXcU4dqW1NkF5Rvv3XyfrZufO_h4AhB/view?usp=sharing
## Результаты выполнения REST-запросов
### GET-запросы с передачей типа операции в URL:

Request `"filePath": "D:/Projects/JavaDev/test_data/10m.txt"`

Операция                                |      URL                                           |         Response (JSON)
--------------------------------------- | -------------------------------------------------- | --------------------
Максимум                                | `http://localhost:8080/find/get_max_value`         | `"result": "49999978"`
Минимум                                 | `http://localhost:8080/find/get_min_value`         | `"result": "-49999996"`
Медиана                                 | `http://localhost:8080/find/get_median_value`      | `"result": "25216"`
Ср. арифметическое                      | `http://localhost:8080/find/get_avg_value`         | `"result": "7364.418442641844"`
Увеличивающиеся подпоследовательность   | `http://localhost:8080/find/get_ascsubseq_value`   | `"result": "[[-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185]]"`
Уменьщающиеся подпоследовательность     | `http://localhost:8080/find/get_descsubseq_value`  | `"result": "[[47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]]"`

### GET-запросы с передачей типа операции в JSON-формате:
#### URL: `http://localhost:8080/find/`

Операция                                |      Request (JSON)                                           |         Response (JSON)
--------------------------------------- | -------------------------------------------------- | --------------------
Максимум                                | `"operation": "get_max_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"`         | `"result": "49999978"`
Минимум                                 | ``"operation": "get_min_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"``         | `"result": "-49999996"`
Медиана                                 | `"operation": "get_median_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"`      | `"result": "25216"`
Ср. арифметическое                      | `"operation": "get_avg_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"`         | `"result": "7364.418442641844"`
Увеличивающиеся подпоследовательность   | `"operation": "get_ascsubseq_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"`   | `"result": "[[-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185]]"`
Уменьщающиеся подпоследовательность     | `"operation": "get_descsubseq_value", "filePath": "D:/Projects/JavaDev/test_data/10m.txt"`  | `"result": "[[47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]]"`

## Выполнение дополнительных реализаций
#### Headers HTTP-Request:

* Добавление заголовок в контроллере POST-запроса, и передача в качестве аргумента HTTP-ответа ResponseEntity<>
![image](https://user-images.githubusercontent.com/75883965/197835756-6bd6bc4a-de39-436c-949b-e30ec292afa5.png)

#### Передача файла с числами в HTTP POST-запросе в бинарном виде вместо передачи пути к файлу:
![image](https://user-images.githubusercontent.com/75883965/197836748-ef932857-1aed-48c4-a0ff-42e9ec294bec.png)
![image](https://user-images.githubusercontent.com/75883965/197836611-d44bfd03-0351-4e46-9bd8-46ab66a81e9e.png)

#### Кеширование результата с помощью Spring Cache/Redis
* Ключ имеет 2 значения (1 - хеш-сумма файла, 2 - тип операции)
* Для GET-запроса реализован след. образом: `@Cacheable(value = "calculates", key = "{@arrayService.CheckSumFile(#requestDTO.getFilePath()), #requestDTO.getOperation(), #operation}")`
* Результат работы кэширования:
1. Выполнение запроса в первый раз, после запсука сервера:

![image](https://user-images.githubusercontent.com/75883965/197837665-84761572-ca5b-4367-b391-ee43f06590ad.png)

2. Выполнение этого же запроса во 2-ой раз:

![image](https://user-images.githubusercontent.com/75883965/197837839-682d757f-457c-4f24-84c0-0e633466ac0e.png)

#### Настройка документации Swagger

![image](https://user-images.githubusercontent.com/75883965/197838370-9a612a3d-9294-4228-af71-e7eb89b48918.png)
