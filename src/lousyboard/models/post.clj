(ns lousyboard.models.post
  (:require [korma.core :refer :all]
            [clj-time.core :refer [now]]
            [clj-time.coerce :refer [to-sql-time]]))

; A post consists of:
;  - id          integer
;  - created_at  timestamp
;  - updated_at  timestamp
;  - content     text

(defentity posts)

(defn create-post
  "creates a post, returning either a directly insertable map or a list of
   validation errors"
  [content]
  {:created_at (to-sql-time (now)),
   :updated_at (to-sql-time (now)),
   :content content})

(defn validate-post
  [post]
  (not (= 0 (.length (post :content)))))

(defn save-post
  "Saves a post in the database"
  [post]
  (insert posts (values post)))

(def base-posts (-> (select* "posts")
                    (order :id :DESC)))
