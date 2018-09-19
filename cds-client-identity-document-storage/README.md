CDS Client Idendtity Document Storage

Библиотека взаимодействия клиента с CDS сервисом для работы с приложениями

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney.proxy-libs</groupId>
    <artifactId>cds-client-identity-document-storage</artifactId>
    <version>1.249-4529702</version>
</dependency>
```

и в `application.yml`

```
cds:
  client:
    identity-document-storage:
      url: http://127.0.0.1:8022/v1/keyring
      timeout: 5000
```

При подключенной зависимости без указания настроек в `application.yml` и запуске приложения - оно выдаст ошибку, что не был указан URL и как это исправить

### Использование

Для того, чтобы начать пользоваться библиотекой после подключения, необходимо просто добавить

```
@Autowired
CdsClientIDStorage cdsIDStorage;

RussianDomesticPassport passport = new RussianDomesticPassport();
passport.setSeries("series");
passport.setNumber("number");
passport.setIssuer("issuer");
passport.setIssuerCode("issuer_code");
passport.setIssuedAt("2016-03-22T06:12:27Z");
passport.setFamilyName("Петров");
passport.setFirstName("Николай");
passport.setBirthDate("2016-03-22T06:12:27Z");
passport.setBirthPlace("2016-03-22T06:12:27Z");

IdentityDocument document = new IdentityDocument();
document.setRussianDomesticPassport(passport);

cdsIDStorage.put(document);
```
