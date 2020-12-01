(ns aoc
  (:require [days.day1 :as d1]))

(defn runDays []
  (print "Day 1 Part 1:" (d1/day1Part1 2020 d1/day1Input) "\n")
  (print "Day 1 Part 2:" (d1/day1Part2 2020 d1/day1Input) "\n")
  )

(defn -main [] (runDays))