(ns lousyboard.controllers.posts
  (:require [lousyboard.views.posts :as views]))

(defn index [req]
  (views/index {:test (:uri req)}))

(defn create [req]
  "hello world!")

