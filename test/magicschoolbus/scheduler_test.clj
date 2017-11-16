(ns magicschoolbus.scheduler-test
  (:require [clojure.test :refer :all]
            [magicschoolbus.scheduler :as scheduler]))

(defn setup [f]
  (f)
  (reset! scheduler/started false))

(use-fixtures :each setup)

(deftest schedule-starts-scheduler
  (testing "Schedule changes started atom to true"
    (scheduler/schedule 500 println "test")
    (is (= @scheduler/started true))))

(deftest stop-stops-scheduler
  (testing "stop stops the scheduler."
    (reset! scheduler/started true)
    (scheduler/stop)
    (is (= @scheduler/started false))))
