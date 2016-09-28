(ns lousyboard.macros)

(defmacro defview
  "A shorthand for defining views that will be placed within a layout"
  [view-name layout args body]
  `(def ~view-name 
     (fn ~args (~layout ~(first args) ~body))))

(defmacro defcontroller-with-view
  [controller-name view-fn args body]
  `(do (def ~controller-name 
         (fn ~args ~body))
       (def ~(symbol (str controller-name "-controller"))
         (fn ~args (~view-fn ~body)))))
