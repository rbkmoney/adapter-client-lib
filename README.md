# proxy-libs

Вспомогательные библиотеки для проксиков


### Разработчики

- [Anatoly Cherkasov](https://github.com/avcherkasov)


### Описание:


##### CDS

1. [cds-client-keyring](cds-client-keyring/README.md)
1. [cds-client-storage](cds-client-storage/README.md)
1. [cds-client-identity-document-storage](cds-client-identity-document-storage/README.md)


##### Hellgate

1. [hellgate-client-proxy-host-provider](hellgate-client-proxy-host-provider/README.md)


##### Wrapper Damsel

1. [wrapper-damsel](wrapper-damsel/README.md)


### Выпуск новой версии
В качестве версии используется номер версии `damsel`, что позволяет точно понимать под какую версию `damsel` внесены изменения для вспомогательных библиотек.

Версии _proxy_libs-pom_ и всех его модулей должны совпадать, для этого перед началом работы над новой версией библиотеки нужно увеличить версию _proxy_libs-pom_ и в корневой директории проекта выполнить команду:
```
mvn versions:update-child-modules -DgenerateBackupPoms=false
```

Параметр `generateBackupPoms` можно опустить, если нужны резервные копии изменяемых файлов.
