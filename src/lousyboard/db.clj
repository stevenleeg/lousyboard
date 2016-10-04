(ns lousyboard.db
  (:require [korma.db :refer :all]
            [ragtime.jdbc :as jdbc]
            [environ.core :refer [env]]))

(def db-config (postgres {:host (env :db-host)
                          :db (env :db-name)
                          :user (env :db-user)
                          :password (env :db-password)}))

(def migration-config
  {:datastore (jdbc/sql-database db-config)
   :migrations (jdbc/load-resources "migrations")})

(defdb db db-config)
