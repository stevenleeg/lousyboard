(ns lousyboard.db
  (:require [korma.db :refer :all]
            [ragtime.jdbc :as jdbc]))

(def db-config (postgres {:db "lousy"
                          :user "lousy"}))

(def migration-config
  {:datastore (jdbc/sql-database db-config)
   :migrations (jdbc/load-resources "migrations")})

(defdb db db-config)
