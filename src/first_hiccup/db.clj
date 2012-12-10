(ns first-hiccup.db
  (:require [clojure.java.jdbc :as sql]))

(def db (System/getenv "DATABASE_URL"))

(defn all []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from shouts order by id desc"]
      (into [] results))))

(defn create [shout]
  (sql/with-connection db
    (sql/insert-values :shouts [:body] [shout])))
