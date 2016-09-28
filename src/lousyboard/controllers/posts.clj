(ns lousyboard.controllers.posts
  (:require [ring.util.response :refer [redirect]]
            [korma.core :refer [select]]
            [lousyboard.macros :refer [defcontroller]]
            [lousyboard.views.posts :as views]
            [lousyboard.models.post :refer :all]))

(defcontroller-with-view index views/index [req]
  {:posts (select base-posts)})

(defn create [req]
  (let [post (create-post (get-in req [:params :content]))
        post-valid? (validate-post post)]
    (if (not post-valid?)
      "Invalid post!"
      (do
        (save-post post)
        (redirect "/")))))

