CDS Client Storage

Библиотека взаимодействия клиента с CDS сервисом для работы с приложениями

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney.proxy-libs</groupId>
    <artifactId>cds-client-storage</artifactId>
    <version>1.249-4529702</version>
</dependency>
```

и в `application.yml`

```
cds:
  client:
    storage:
      url: http://127.0.0.1:8022/v1/storage
      timeout: 5000
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить

### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить

```
@Autowired
CdsClientStorage cdsStorage;

CardData cardData = cdsStorage.getCardData(context);
SessionData sessionData = cdsStorage.getSessionData(context);
```
