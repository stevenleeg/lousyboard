(ns lousyboard.views.posts
  (:require [hiccup.core :refer [html]]
            [hiccup.form :refer :all]
            [lousyboard.views.shared :refer :all]))

(defview index layout
  [ctx]
  [:div {:class "post-box"}
   [:form {:method "POST" :action "/posts/new"}
    (text-field {:placeholder "just say it, already"
                 :class "post-input"} :content)]])
