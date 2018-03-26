# my-slack-bot

Un Bot para Slack en Clojure que responde a groserías con su definición.

## Diccionario
El dicccionario es muy sencillo y lo descargué de este [blog](http://esclavoseternos.blogspot.mx/2010/06/diccionario-de-groserias-mexicanas.html)

## Compilar
```
git clone https://github.com/danielef/my-slack-bot.git
cd my-slack-bot
lein uberjar
```

## Ejecutar
```
java -jar target/my-slack-bot-0.1.0-standalone.jar -t bot-user-oauth-access-token
```

## Licencia

Copyright © 2018 danielef

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
