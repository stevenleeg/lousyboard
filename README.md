# lousyboard
A lousy message board.

## Running
Firstly, create a `profiles.clj` file with an environment similar to the
following:

    {:dev {:env {:db-host "127.0.0.1"
                 :db-name "lousy"
                 :db-user "lousy"
                 :db-password "lousy"}}}

Next up we'll fire off database migrations by running `lein migrate`. Once the
migrations have been applied you can start the web server with:

    lein start

You will also want to compile/watch sass files, otherwise things will look 
pretty ugly:

    lein watch-sass

