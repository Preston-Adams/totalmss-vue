# MSS
MIREA Schedule System

## Codex
* Не использовать ветку `master` (у нас **НЕ** `main`)
* Перед коммитом и пушем проверяем ветку
* **Не заливаем** конфиги, храним их *локально*. Заливаем только **образцы** конфигов, а в коде делаем проверку на конфиг (сравниваем с локальным глобальным), и/или подменяем во время CI/CD, и/или как угодно. Но не Secrets – плохо быть завязанным на архитектуру какого-либо сервиса.

## Branches
* [Ветка](./tree/master) `master` – пустая
* [Ветка](./tree/scrapper) `scrapper` – движок сборщика расписания, фетчит и распихивает в БД
* [Ветка](./tree/telegram) `telegram` – Telegram-бот
* [Ветка](./tree/backend) `backend` – Back-end для [mss.serguun42.ru](https://mss.serguun42.ru)
* [Ветка](./tree/frontend) `frontend` – Frond-end для [mss.serguun42.ru](https://mss.serguun42.ru)
* [Ветка](./tree/app) `app` – Android-приложение
