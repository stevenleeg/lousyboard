(ns lousyboard.models.posts-test
  (:require [clojure.test :refer :all]
            [lousyboard.models.post :refer :all]))

(deftest test-parse-post
  (testing "it properly escapes html strings"
    (let [original "<p>Hello world</p>"
          parsed (parse-post original)]
      (is (= parsed "&lt;p&gt;Hello world&lt;/p&gt;"))))

  (testing "it properly links @ replies"
    (let [original "Yo @14 what's up?"
          parsed (parse-post original)]
      (is (= parsed "Yo <a href='/posts/14'>@14</a> what's up?"))))
  
  (testing "it properly links http URLs with markdown syntax"
    (let [original "[This](http://example.com) is a link"
          parsed (parse-post original)]
      (is (= parsed "<a target='_blank' href='http://example.com'>This</a> is a link"))))

  (testing "it properly links https URLs with markdown syntax"
    (let [original "[This](https://example.com) is a link"
          parsed (parse-post original)]
      (is (= parsed "<a target='_blank' href='https://example.com'>This</a> is a link"))))

  (testing "markdown linking does not allow malformed URLs"
    (let [original "[This](javascript:alert('wat');) is not a link"
          parsed (parse-post original)]
      (is (= parsed original)))))
