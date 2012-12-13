(ns first-hiccup.core
    (:use compojure.core
        hiccup.core
        hiccup.form
        hiccup.page
        hiccup.element
        )
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [first-hiccup.db :as db]
            [clojure.string :as str]
            [ring.util.response :as ring-res]
            [ring.adapter.jetty :as ring-adpt]
            ))

(defn index [req] 
  (html5 [:body [:p "Wellcome" [:br] [:h1 "all"]]]))

(defn shout-form []
  (html5
   (form-to [:post "/shout"]
            [:p (label "shout" "What do you want to SHOUT?") ]
            [:p (text-area "shout")]
            [:p (submit-button "SHOUT!") ]
             )))

(defn create [params]
  (println params)
  (let [shout (:shout params)]
    (when-not (str/blank? shout)
      (db/create shout)))
  (ring-res/redirect "/all"))

(defn all [req]
  (html5
   [:body [:h2 "Shouts:"]
    (unordered-list (map :body (db/all)))
    [:p (link-to "/shout" "back")]
    ]
   ))
     
(defroutes app-routes
  (GET "/" [] index)
  (GET "/shout" [] (shout-form))
  (POST "/shout" {params :params} (create params))
  (GET "/all" [] all)
  (GET "/clear" []
       (do (db/clear)
           (ring-res/redirect "/shout")))
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn start [port]
  (ring-adpt/run-jetty #'app {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))
