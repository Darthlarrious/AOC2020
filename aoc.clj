(ns aoc
  (:require [days.day1 :as d1]
            [days.day2 :as d2]))

(defn runDays []
  (print "Day 1 Part 1:" (d1/day1Part1 2020 d1/day1Input) "\n")
  (print "Day 1 Part 2:" (d1/day1Part2 2020 d1/day1Input) "\n")
  (print "------\n")
  (print "Day 2 Part 1:" (d2/day2Part1) "\n")
  (print "Day 2 Part 2:" (d2/day2Part2) "\n")
  )

(defn -main [] (runDays))