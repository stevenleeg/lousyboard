(ns lousyboard.controllers.posts
  (:require [ring.util.response :refer [redirect]]
            [korma.core :refer [select]]
            [lousyboard.views.posts :as views]
            [lousyboard.models.post :refer :all]))

(defn index [req]
  (views/index {:posts (select base-posts)}))

(defn create [req]
  (let [post (create-post (get-in req [:params :content]))
        post-valid? (validate-post post)]
    (if (not post-valid?)
      "Invalid post!"
      (do
        (save-post post)
        (redirect "/")))))

