(ns days.day4
  (:require [clojure.string :as s]))

(def day4Input (slurp "inputs/day4.txt"))

(def passports (map #(clojure.string/replace % #"\r\n" '" ") (clojure.string/split days.day4/day4Input #"\r\n\r\n")))

(def passportKeys ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn getKey [field]
  (first (s/split field #":")))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn isValidPassport? [passport keys]
  (let [fields (s/split passport #" ")]
    (not
      (>
        (count keys)
        (count
          (filter
            #(in? keys (getKey %))
            fields)))))
  )


(defn day4Part1 [] (count (filter #(isValidPassport? % passportKeys) passports)))
(defn day4Part2 [] nil)