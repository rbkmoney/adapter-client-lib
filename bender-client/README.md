Bender Client

Библиотека взаимодействия клиента с сервисом Bender

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney.adapter-client-lib</groupId>
    <artifactId>bender-client</artifactId>
    <version>${bender-client.version}</version>
</dependency>
```

В зависимостях также должны быть указаны
```
<dependency>
    <groupId>com.rbkmoney.woody</groupId>
    <artifactId>woody-thrift</artifactId>
    <version>${woody-thrift.version}</version>
</dependency>
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>damsel</artifactId>
    <version>${damsel.version}</version>
</dependency>
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>bender-proto</artifactId>
    <version>${bender.version}</version>
    <scope>provided</scope>
</dependency>
```

и в `application.yml`

```
bender:
  client:
    url: http://127.0.0.1:8022/v1/bender
    networkTimeout: 5000
  namespace: test
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить

### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить

```
@Autowired
BenderClient client;

GetInternalIDResult result = client.getInternalID(externalID);
```
