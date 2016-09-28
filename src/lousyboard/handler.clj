(ns lousyboard.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [lousyboard.db]
            [lousyboard.controllers.posts :as posts]))

(defroutes app-routes
  (GET "/" [:as req] (posts/index req))
  (POST "/posts/new" [:as req] (posts/create req))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
