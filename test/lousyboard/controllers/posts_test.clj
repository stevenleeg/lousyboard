(ns lousyboard.controllers.posts-test
  (:require [clojure.test :refer :all]
            [lousyboard.test-utils :refer [mock-request]]
            [lousyboard.controllers.posts :refer :all]))

(deftest test-index
  (testing "index returns a 200"
    (let [resp (mock-request :get "/")]
      (is (= 200 (:status resp))))))

(deftest test-create
  (testing "it creates a post" (is true)))

