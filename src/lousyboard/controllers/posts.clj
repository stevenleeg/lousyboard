(ns lousyboard.controllers.posts
  (:require [ring.util.response :refer [redirect]]
            [lousyboard.views.posts :as views]
            [lousyboard.models.post :refer :all]))

(defn index [req]
  (views/index {:test (:uri req)}))

(defn create [req]
  (let [post (create-post (get-in req [:params :content]))
        post-valid? (validate-post post)]
    (if (not post-valid?)
      "Invalid post!"
      (do
        (save-post post)
        (redirect "/")))))

