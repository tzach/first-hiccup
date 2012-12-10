(ns first-hiccup.db-test
  (:use clojure.test
        first-hiccup.db)
  (:require [clojure.java.jdbc :as sql]))

(deftest test-db
  (testing (sql/with-connection db
      (is (sql/create-table :testing [:data :text]))
      (is (sql/insert-record :testing {:data "Hello World"}))
      (is db (sql/insert-records :testing
                                            {:data "Hello Clojure"}
                                            {:data "Bye Clojure"}))
      (is (sql/with-query-results results
            ["select * from testing"]
            (into [] results)))
      (is (sql/drop-table :testing)))))

