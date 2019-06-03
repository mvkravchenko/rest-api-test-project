**Добавление новых тестов**

Основные этапы добавления тестов:
* Добавить/изменить имя API в `com.example.testapiproject.entities.common.ApiEndpoints`
* Добавить/изменить объекты для тела ответа и запроса в пакет `com.example.testapiproject.entities` аналогично `CalculateRequest` и `CalculateSuccessResponse`
* Добавить/изменить  базовый тестовый класс со сценариями выполнения запросов в пакет `com.example.testapiproject.base`:
* Добавить/изменить провайдер тестовых данных для сценариев:
 >* CSV-файлы в `/resources` тестового проекта
 >* Классы-провайдеры в `com.example.testapiproject.cases`

 **Конфигурация**

 Знчения параметров конфигурации можно задать:
 * в файлах `config[stand].properties`, где `[stand]` - значение VM-опции `stand`
 * через VM-опции
 
При добавлении новых параметров конфигурации нужно добавить их обработку в пакете `com.example.testapiproject.init`

**Отчеты**

При запуске тестов` mvn test` генерятся исходники для allure-отчета.
Чтобы собрать отчет, нужно установить `allure` и выполнить команду `allure:serve`
