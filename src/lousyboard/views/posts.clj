(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer :all]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [lousyboard.views.shared :refer :all]))

(defview index layout
  [ctx]
  [:div {:class "post-box"}
   [:form {:method "POST" :action "/posts/new"}
    (anti-forgery-field)
    (text-field {:placeholder "just say it, already"
                 :class "post-input"} :content)]])
