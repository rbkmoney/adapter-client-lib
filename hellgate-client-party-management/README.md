Hellgate

Обработка основного хранилища

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney.proxy-libs</groupId>
    <artifactId>hellgate-client-party-management</artifactId>
    <version>1.99-557f549</version>
</dependency>
```

и в `application.yml`

```
rbkmoney:
  hellgate:
    client:
      url:
        party_management: http://127.0.0.1:8022/v1/processing/partymgmt
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить


### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить

```
@Autowired
HellgateClientPartyManagement client;
```
