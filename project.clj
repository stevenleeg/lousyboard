(defproject lousyboard "0.1.0-SNAPSHOT"
  :description "A lousy bulletin board"
  :url "https://lousy.teif.xyz"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-mock "0.3.0"]
                 [korma "0.4.3"]
                 [org.postgresql/postgresql "9.4.1211"]
                 [ragtime "0.6.0"]
                 [clj-time "0.12.0"]
                 [environ "1.1.0"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.1.0"]
            [lein-shell "0.5.0"]
            [lein-exec "0.3.6"]]
  :aliases {"watch-sass" ["shell" "sass" "--watch" "resources/sass/main.sass:resources/public/dist/application.css"]
            "migrate" ["exec" "-ep" "(use 'ragtime.repl) (use 'lousyboard.db) (migrate migration-config)"]
            "start" ["ring" "server-headless"]}
  :ring {:handler lousyboard.handler/app
         :nrepl {:start? true
                 :port 9998}}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
