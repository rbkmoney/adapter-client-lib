Hellgate

Сервис получения истории событий сразу всех машин.

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>hellgate-client-eventsink</artifactId>
    <version>1.99-557f549</version>
</dependency>
```

и в `application.yml`

```
rbkmoney:
  hellgate:
    client:
      url:
        eventsink: http://127.0.0.1:8022/v1/processing/eventsink
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить


### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить `@Autowired`

```
@Autowired
HellgateClientEventSink client;
```
