(ns lousyboard.views.shared
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defmacro defview
  "A shorthand for defining views that will be placed within a layout"
  [view-name layout args body]
  `(def ~view-name (fn ~args
                     (~layout ~(first args) ~body))))

(defn layout 
  "Defines the default layout for views in the application"
  [ctx content]
  (html [:head
         [:title "lousyboard"]
         (include-css "/dist/application.css")]
        [:body
         [:div {:class "container"} content]]))
