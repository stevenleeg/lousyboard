(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer [text-field]]
            [hiccup.element :refer [link-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [clj-time.coerce :as c]
            [lousyboard.views.shared :refer [layout]]
            [lousyboard.utils :refer [defview fuzzy-time]]))

(defn render-post [{post :post}]
  [:div {:class "post"} 
   [:div {:class "post-meta"} 
    (link-to {:class "left"} (str "/posts/" (:id post))
     (fuzzy-time (c/from-sql-time (:created_at post))))
    [:div {:class "right"} 
     (str "#" (:id post))]]
   [:div {:class "post-content"} (:content post)]])

(defview index layout
  [ctx]
  [:div
   [:div {:class "post-box"}
    [:form {:method "POST" :action "/posts/new"}
     (anti-forgery-field)
     (text-field {:placeholder "just say it, already"
                  :class "post-input"} :content)]]
   (map render-post {:posts (:posts ctx)})])

(defview show layout
  [ctx]
  [:div
   [:div "wat"]
   (render-post (merge ctx :class "post-single"))])
