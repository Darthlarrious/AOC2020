(ns days.day2
  (:require [clojure.string :as s]))

(def day2Input (s/split-lines (slurp "inputs/day2.txt")))

(defn buildRegex [string-input]
  (let [pieces (s/split string-input #" ")
        string (last pieces)
        min (first (s/split (first pieces) #"\-"))
        max (last (s/split (first pieces) #"\-"))]
    (re-pattern (str '"^([^" string '"]*" string '"){" min '"," max '"}[^" string '"]*$"))  ))

(defn validPassword? [item]
  (let [pieces (s/split item #":")]
    (not (nil? (re-matches (buildRegex (first pieces)) (last pieces)))) ))

(defn parseCharacters [string-input password]
  (let [pieces (s/split string-input #" ")
        character (last pieces)
        firstIndex (first (s/split (first pieces) #"\-"))
        secondIndex (last (s/split (first pieces) #"\-"))]
    character))

(defn day2Part1 [] (count (filter validPassword? day2Input)))
(defn day2Part2 [] nil)