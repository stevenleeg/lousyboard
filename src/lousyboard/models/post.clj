(ns lousyboard.models.post
  (:require [korma.core :as korma]
            [clj-time.core :refer [now]]
            [clj-time.coerce :refer [to-sql-time]]))

; A post consists of:
;  - id          integer
;  - created_at  timestamp
;  - updated_at  timestamp
;  - content     text

(korma/defentity posts)

;
; Post Creation
;
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
  (korma/insert posts (korma/values post)))

;
; Post querying
;
(def base-posts (-> (korma/select* :posts)
                    (korma/order :id :DESC)))

(defn paginate-posts
  "Accepts a base query, page number, a number of items per page, and returns a
  query that has been limit/offseted"
  [query page-number items-per-page]
  (-> query
      (korma/offset (* items-per-page (- page-number 1)))
      (korma/limit items-per-page)))

(defn has-next-page
  "Returns a boolean based on whether or not the current query has a next page
  given the current page and the number of items per page"
  [page-number items-per-page]
  (let [result (korma/select (-> (korma/select* "posts")
                                 (korma/aggregate (count :*) :count)))
        total (:count (first result))
        cursor (* (- page-number 1) items-per-page)]
    (< items-per-page (- total cursor))))

;
; Manipulating existing posts
;
(defn parse-post
  [text]
  (-> text
      (clojure.string/escape {\< "&lt;", \> "&gt;", \& "&amp;"})
      (clojure.string/replace 
        #"\[(.*)\]\((http(s)?\:\/\/.*)\)" 
        "<a target='_blank' href='$2'>$1</a>")
      (clojure.string/replace #"\@([0-9]*)" "<a href='/posts/$1'>@$1</a>")))

