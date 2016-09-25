(ns lousyboard.controllers.posts
  (:require [lousyboard.views.posts :as views]))

(defn index []
  (views/index {:test "wat"}))

