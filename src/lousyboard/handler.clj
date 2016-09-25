(ns lousyboard.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [lousyboard.controllers.posts :as posts]))

(defroutes app-routes
  (GET "/" [] (posts/index))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
