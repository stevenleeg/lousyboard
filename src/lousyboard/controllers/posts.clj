(ns lousyboard.controllers.posts
  (:require [ring.util.response :refer [redirect]]
            [korma.core :refer [select where]]
            [lousyboard.views.posts :as views]
            [lousyboard.models.post :refer :all]))

(def items-per-page 15)

(defn index [req]
  (let [; Fetch the current page
        page-str (get-in req [:params :page])
        current-page  (-> page-str
                          (if page-str "0")
                          (Integer/parseInt)
                          (max 1)) 

        ; Determine if we have next/prev pages
        next-page (has-next-page current-page items-per-page)
        prev-page (not (= current-page 1))

        ; Fetch the posts
        query-posts (paginate-posts base-posts current-page items-per-page)
        posts (select query-posts)]

    (views/index {:posts posts
                  :current-page current-page
                  :has-next-page next-page
                  :has-prev-page prev-page})))

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

