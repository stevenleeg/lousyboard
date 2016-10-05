(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer [text-field]]
            [hiccup.element :refer [link-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [clj-time.coerce :as c]
            [lousyboard.views.shared :refer [layout]]
            [lousyboard.utils :refer [defview fuzzy-time parse-post]]))

(defn render-post [post]
  [:div {:class "post"} 
   [:div {:class "post-meta"} 
    (link-to {:class "left"} (str "/posts/" (:id post))
     (fuzzy-time (c/from-sql-time (:created_at post))))
    [:div {:class "right"} 
     [:button {:data-post-id (:id post)
               :class "post-id"}
      (str "#" (:id post))]]]
   [:div {:class "post-content"} (parse-post (:content post))]])

(def noposts
  [:div {:class "noposts"}
   "No posts found"])

(defn render-post-input []
  [:div {:class "post-box"}
   [:form {:method "POST" :action "/posts/new"}
    (anti-forgery-field)
    (text-field {:placeholder "just say it, already"
                 :class "post-input"
                 :id "post-input"} :content)]])

(defn render-pagination 
  [{:keys [current-page has-next-page has-prev-page]}]
  (let [next-page (+ current-page 1)
        prev-page (- current-page 1)]
    [:div {:class "pagination-container"}
     (when has-prev-page
       (link-to {:class "pagination pagination-prev"} 
                (str "/?page=" prev-page) 
                "&laquo; Prev page"))

     [:div {:class "pagination-separator"}]

     (when has-next-page 
       (link-to {:class "pagination pagination-next"}
                (str "/?page=" next-page) 
                "Next page &raquo;"))]))


(defview index layout
  [ctx]
  [:div
   (render-post-input)
   (if (= 0 (count (:posts ctx)))
     noposts
     (map render-post (:posts ctx)))
   (render-pagination ctx)])

(defview show layout
  [ctx]
  [:div {:class "single-post"}
   [:a {:href "/" :class "btn"} "&laquo; back to feed"]
   (render-post (:post ctx))])
