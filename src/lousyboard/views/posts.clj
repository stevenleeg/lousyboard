(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer :all]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [lousyboard.views.shared :refer :all]))

(defn render-post [post]
  [:div {:class "post"} 
   [:div {:class "post-meta"} (str "#" (:id post))]
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
