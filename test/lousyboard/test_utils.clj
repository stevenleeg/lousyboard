(ns lousyboard.test-utils
  (:require [clojure.test :refer :all]
            [lousyboard.handler :refer [app]]))

(defn reset-db [run-tests]
  (println "wat"))

(use-fixtures :each reset-db)

(defn mock-request 
  "Mocks an HTTP request being made to the application"
  [method resource & params]
  (app {:request-method method 
        :uri resource 
        :params (first params)}))

