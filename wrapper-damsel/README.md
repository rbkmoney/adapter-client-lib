Damsel

Обертка для моделей Damsel

### Настройки

Добавить в `pom.xml` в зависимости

```
<dependency>
    <groupId>com.rbkmoney</groupId>
    <artifactId>wrapper-damsel</artifactId>
    <version>${wrapper-damsel.version}</version>
</dependency>
```

### Использование

Несколько примеров различных структур:

неудачное завершения платежа
```
ProxyProviderWrapper.makeProxyResultFailure(failure);
```

перенаправления пользователя на ACS
```
Integer timer = 600;
Intent intent = ProxyWrapper.makeIntentWithSuspendIntent(
    tag, timer, makePostUserInteraction(url, params)
);

ProxyProviderWrapper.makePaymentProxyResult(intent, state);
```
