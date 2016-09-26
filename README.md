# lousyboard
A lousy message board.

## Running
You'll want to start off by running migrations. This can be done by opening a
repl (with `lein repl`) and running the following:

    (require '[ragtime.repl :as repl])
    (require '[lousyboard.db :refer [migration-config])

    (repl/migrate migration-config)

Once the migrations have been applied you can start the web server with:

    lein ring server-headless

