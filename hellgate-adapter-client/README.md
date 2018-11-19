Hellgate Client

Обработка основного хранилища

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney.proxy-libs</groupId>
    <artifactId>hellgate-adapter-client</artifactId>
    <version>1.268-45c8524</version>
</dependency>
```

В зависимостях также должны быть указаны
```
<dependency>
    <groupId>com.rbkmoney.woody</groupId>
    <artifactId>woody-thrift</artifactId>
    <version>1.1.15</version>
</dependency>
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>damsel</artifactId>
    <version>1.268-45c8524</version>
</dependency>
```

и в `application.yml`

```
hellgate:
  client:
    url: http://127.0.0.1:8022/v1/proxyhost/provider
    timeout: 30000
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить


### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить

```
@Autowired
HellgateAdapterClient client;
```
