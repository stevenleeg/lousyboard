(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer :all]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [clj-time.coerce :as c]
            [lousyboard.views.shared :refer [layout]]
            [lousyboard.utils :refer [defview fuzzy-time]]))

(defn render-post [post]
  [:div {:class "post"} 
   [:div {:class "post-meta"} 
    [:div {:class "left"} 
     (fuzzy-time (c/from-sql-time (:created_at post)))]
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
   (map render-post (:posts ctx))])
