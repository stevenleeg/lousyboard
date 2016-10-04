(ns lousyboard.views.shared
  (:require [hiccup.core :refer :all]
            [hiccup.element :refer [link-to]]
            [hiccup.page :refer :all]))

(def footer
  [:div {:class "footer"}
   "powered by " 
   (link-to "https://github.com/stevenleeg/lousyboard" "lousyboard")])

(defn layout 
  "Defines the default layout for views in the application"
  [ctx content]
  (html [:head
         [:title "lousyboard"]
         (include-css "/dist/application.css")
         (include-js "/app.js")]
        [:body
         [:div {:class "container"} 
          content
          footer]]))
