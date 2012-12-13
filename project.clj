(defproject first-hiccup "0.1.0-SNAPSHOT"
  :description "a trivial clojure web app, base on compojure, hiccup and postgresql"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3" :exclusions [ring/ring-core]]
                 [hiccup "1.0.2"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [ring/ring-devel "1.1.6"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql/postgresql "9.1-901.jdbc4"]]
  :plugins [[lein-ring "0.7.5"]
            ;[lein-pedantic "0.0.5"]
            ]
  :min-lein-version "2.0.0"
;  :ring {:handler first-compojure.handler/app}
;  :profiles
;  {:dev {:dependencies [[ring-mock "0.1.3"]
;                        [ring-serve "0.1.2"
             ;                           :exclusions [ring/ring-jetty-adapter]
                                        ;                         ]]}}
  )

