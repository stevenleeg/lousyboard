(ns lousyboard.utils
  (:require [clj-time.core :as t]
            [clj-time.format :as f]))

(defn fuzzy-time
  "Converts a timestamp to a fuzzy English version of itself"
  [timestamp]
  (let [now (t/now)
        interval (t/interval timestamp now)
        minutes (t/in-minutes interval)
        hours (t/in-hours interval)
        days (t/in-days interval)]
    (cond 
      (< minutes 1) "just now"
      (< minutes 60) (str minutes " minutes go")
      (= hours 1) "an hour ago"
      (< hours 24) (str hours " hours ago")
      (= days 1) "a day ago"
      (< days 6) (str days " days ago")
      :else (f/parse (f/formatters :basic-date-time) timestamp))))

(defn parse-post
  [text]
  (-> text
      (clojure.string/escape {\< "&lt;", \> "&gt;", \& "&amp;"})
      (clojure.string/replace #"\@([0-9]*)" "<a href='/posts/$1'>@$1</a>")))

(defmacro defview
  "A shorthand for defining views that will be placed within a layout"
  [view-name layout args body]
  `(def ~view-name 
     (fn ~args (~layout ~(first args) ~body))))
