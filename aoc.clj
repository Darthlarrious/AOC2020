(ns aoc
  (:require [days.day1 :as d1]
            [days.day2 :as d2]
            [days.day3 :as d3]
            [days.day4 :as d4]
            [days.day5 :as d5]
            [days.day6 :as d6]
            [days.day7 :as d7]
            [days.day8 :as d8]
            [days.day9 :as d9]))

(defn runDays []
  (print "Day 1 Part 1:" (d1/day1Part1 2020 d1/day1Input) "\n")
  (print "Day 1 Part 2:" (d1/day1Part2 2020 d1/day1Input) "\n")
  (print "------\n")
  (print "Day 2 Part 1:" (d2/day2Part1) "\n")
  (print "Day 2 Part 2:" (d2/day2Part2) "\n")
  (print "------\n")
  (print "Day 3 Part 1:" (d3/day3Part1) "\n")
  (print "Day 3 Part 2:" (d3/day3Part2) "\n")
  (print "------\n")
  (print "Day 4 Part 1:" (d4/day4Part1) "\n")
  (print "Day 4 Part 2:" (d4/day4Part2) "\n")
  (print "------\n")
  (print "Day 5 Part 1:" (d5/day5Part1) "\n")
  (print "Day 5 Part 2:" (d5/day5Part2) "\n")
  (print "------\n")
  (print "Day 6 Part 1:" (d6/day6Part1) "\n")
  (print "Day 6 Part 2:" (d6/day6Part2) "\n")
  (print "------\n")
  (print "Day 7 Part 1:" (d7/day7Part1) "\n")
  (print "Day 7 Part 2:" (d7/day7Part2) "\n")
  (print "------\n")
  (print "Day 8 Part 1:" (d8/day8Part1) "\n")
  (print "Day 8 Part 2:" (d8/day8Part2) "\n")
  (print "------\n")
  (print "Day 9 Part 1:" (d9/day9Part1) "\n")
  (print "Day 9 Part 2:" (d9/day9Part2) "\n")
  )

(defn -main [] (runDays))