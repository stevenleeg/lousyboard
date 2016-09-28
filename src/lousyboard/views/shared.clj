(ns lousyboard.views.shared
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn layout 
  "Defines the default layout for views in the application"
  [ctx content]
  (html [:head
         [:title "lousyboard"]
         (include-css "/dist/application.css")]
        [:body
         [:div {:class "container"} content]]))
