# adapter-client-lib

Вспомогательные библиотеки для адаптеров


### Разработчики

- [Anatoly Cherkasov](https://github.com/avcherkasov)


### Описание:


##### Bender

1. [bender-client](bender-client/README.md)


##### CDS

1. [cds-client-storage](cds-client-storage/README.md)
1. [cds-client-identity-document-storage](cds-client-identity-document-storage/README.md)


##### Hellgate

1. [hellgate-adapter-client](hellgate-adapter-client/README.md)


##### Fistful

1. [fistful-client](fistful-client/README.md)


### Выпуск новой версии

Версии _adapter-client-lib-pom_ и всех его модулей должны совпадать, для этого перед началом работы над новой версией библиотеки нужно увеличить версию _adapter-client-lib-pom_ и в корневой директории проекта выполнить команду:
```
mvn versions:update-child-modules -DgenerateBackupPoms=false
```

Параметр `generateBackupPoms` можно опустить, если нужны резервные копии изменяемых файлов.
