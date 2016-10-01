(ns lousyboard.controllers.posts
  (:require [ring.util.response :refer [redirect]]
            [korma.core :refer [select where]]
            [lousyboard.views.posts :as views]
            [lousyboard.models.post :refer :all]))

(defn index [req]
  (views/index {:posts (select base-posts)}))

(defn show [{params :params :as req}]
  (let [post (-> base-posts 
                 (where {:id (Integer. (:id params))})
                 (select))]
    (if (= 0 (count post)) 
      "not found"
      (views/show {:post (first post)}))))

(defn create [req]
  (let [post (create-post (get-in req [:params :content]))
        post-valid? (validate-post post)]
    (if (not post-valid?)
      "Invalid post!"
      (do
        (save-post post)
        (redirect "/")))))

